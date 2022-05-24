package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Indexed
{
    Directory rootDirectory = new Directory("root");
    VirtualDisk disk;

    public Indexed(VirtualDisk disk)
    {
        this.disk=disk;
    }


    public boolean createFile(String file_path, int sizeKB)
    {
        Directory parent = rootDirectory.getSpecificDirectory(file_path);

        //check if there is already exist
        if(parent.isFoundFile(file_path) || parent.isDeleted())
            return false;

        //No free space to allocate it
        if(sizeKB>disk.getFree_space())
            return false;

        ArrayList<Integer> allocatedBlocks = new ArrayList<>();

        int best_index = 0;
        boolean flag=true;


        //check Free spaces in the disk
        ArrayList<SpaceManager> spaceManagers = disk.getSpaces();
        for (SpaceManager s : spaceManagers)
        {
            if(sizeKB<=s.size && s.state) //file size < space size , space is available
            {
                best_index=s.start;
                flag=false;
                break;
            }
        }

        //there is no available spaces so, take first free index
        if(flag)
            best_index=disk.getBestIndex()+1;


        //prepare blocks to allocate in disk
        for(int i= best_index;i<best_index+sizeKB;i++)
            allocatedBlocks.add(i);

        disk.indexed_allocate(allocatedBlocks);

        //create the file and add it to tree
        VirtualFile file = new VirtualFile(file_path,allocatedBlocks);
        parent.addFile(file);

        return true;

    }

    public boolean createFolder(String folder_path)
    {
        Directory parent = rootDirectory.getSpecificDirectory(folder_path);

        //check if directory already exist
        if(parent.isFoundDir(folder_path))
            return false;

        Directory directory = new Directory(folder_path);
        parent.addDirectory(directory);

        return true;
    }

    public boolean deleteFolder(String folder_path)
    {
        Directory parent = rootDirectory.getSpecificDirectory(folder_path);

        //if there is no exist to this path
        if(!parent.isFoundDir(folder_path))
            return false;

        for(Directory d : parent.getSubDirectory())
        {
            if(d.getDirectoryPath().equals(folder_path))
            {
                d.setDeleted(true);
                for(VirtualFile f : d.getFiles())
                {
                    f.setDeleted(true);
                    disk.indexed_deallocate(f.getAllocatedBlocks());
                }
            }

        }

        return true;
    }

    public boolean deleteFile(String file_path)
    {
        Directory parent = rootDirectory.getSpecificDirectory(file_path);

        //if there is no exist to this path
        if(!parent.isFoundFile(file_path))
            return false;

        for(VirtualFile vf : parent.getFiles())
        {
            if(vf.getFilePath().equals(file_path))
            {
                vf.setDeleted(true);

                //de-allocated the blocks of the file in the disk
                disk.indexed_deallocate(vf.getAllocatedBlocks());
                break;
            }
        }







        return true;

    }


   public void diskStructure()
    {
        rootDirectory.printDirectoryStructure(0);

    }

    //Check every file with its index in the Indexed Allocation
    public void displayFiles()
    {
        //Files in the root
        for(VirtualFile vf : rootDirectory.getFiles())
        {
            if(!vf.getDeleted())
            {
                System.out.println(vf.getFilePath() + "        " + disk.getFileIndex(vf.getAllocatedBlocks()));
                System.out.println("Blocks is " +  vf.getAllocatedBlocks());
            }

        }

        //Files in every subdirectory in root
        for(Directory d : rootDirectory.getSubDirectory())
        {
            for(VirtualFile vf : d.getFiles())
            {
                if(!vf.getDeleted())
                {
                    System.out.println(vf.getFilePath() + "        " + disk.getFileIndex(vf.getAllocatedBlocks()));
                    System.out.println("Blocks is " +  vf.getAllocatedBlocks());
                }

            }

        }

    }

    public void write(String filePath) {
        try {
            File file_out = new File(filePath);
            FileOutputStream file_stream = new FileOutputStream(file_out);

            BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(file_stream));


            buffer.write("Empty Space in disk = " + disk.getFree_space());
            buffer.newLine();


            buffer.write("Allocated Space in disk = " + (disk.getDisk_size() - disk.getFree_space()) );
            buffer.newLine();


            //Root Files
            for(VirtualFile vf : rootDirectory.getFiles())
            {
                if(!vf.getDeleted())
                {
                    buffer.write(vf.getFilePath() +"  "+ disk.getFileIndex(vf.getAllocatedBlocks()) +"        " + vf.getAllocatedBlocks());
                    buffer.newLine();
                }

            }

            //Files of subdirectories of root
            for(Directory d : rootDirectory.getSubDirectory())
            {
                for(VirtualFile vf : d.getFiles())
                {
                    if(!vf.getDeleted())
                    {
                        buffer.write(vf.getFilePath() +"  "+ disk.getFileIndex(vf.getAllocatedBlocks()) +"        " + vf.getAllocatedBlocks());
                        buffer.newLine();

                    }

                }

            }

            //write root directories
            buffer.write("root folders: ");
            for (Directory dd : rootDirectory.getSubDirectory())
                buffer.write(dd.getDirectoryName() + " ,");

            buffer.newLine();

            //write root files
            buffer.write("root files: ");
            for (VirtualFile dd : rootDirectory.getFiles())
                buffer.write(dd.getFileName() + " ,");
            buffer.newLine();

            //looping each dir in root
            for(Directory d : rootDirectory.getSubDirectory())
            {
                if(!d.deleted)
                {
                    //write subdirectories of dir
                    buffer.write(d.getDirectoryName() + " folders: ");
                    for (Directory dd : d.getSubDirectory())
                        buffer.write(dd.getDirectoryName());
                    buffer.newLine();

                    //write files of dir
                    buffer.write(d.getDirectoryName() + " files: ");
                    for (VirtualFile dd : d.getFiles())
                        buffer.write(dd.getFileName());
                    buffer.newLine();
                }
            }



            //Blocks of the disk
            int [] blocks = disk.getBlocks();
            for(int i=0;i<blocks.length;i++)
            {
                if(blocks[i]==0)
                {
                    buffer.write("Blocks[" + i+ "]" + " is Empty ,");
                }

                else
                {
                    buffer.write("Blocks[" + i+ "]" + " is Allocated ,");
                }
                buffer.newLine();
            }



            buffer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
