package com.company;

import java.io.*;
import java.util.ArrayList;

public class Contiguous
{
    Directory rootDirectory = new Directory("root");
    VirtualDisk disk;

    public Contiguous (VirtualDisk disk)
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


        //start index , size of file
        int best_index;
        boolean flag = true;

        //check if there is a space available
        ArrayList<SpaceManager> spaceManagers = disk.getSpaces();
        for (SpaceManager s : spaceManagers)
        {
            if(sizeKB<=s.size && s.state) //file size < space size , space is available
            {
                flag=false;
                break;
            }
        }


        if(flag)
            best_index= disk.getBestIndex() + 1; //first index free to use
        else
            best_index= disk.best_fit_index(sizeKB); //first index of space to apply best fit

        ArrayList<Integer> allocated = new ArrayList<>();
        allocated.add(best_index);
        allocated.add(sizeKB);


        // allocated in the disk
        disk.contiguous_allocate(allocated);

        //create the file and add it to tree
        VirtualFile file = new VirtualFile(file_path,allocated);

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
                d.setDeleted(true);
        }

        return true;
    }

    public boolean deleteFile(String file_path)
    {
        Directory parent = rootDirectory.getSpecificDirectory(file_path);

        //if there is no exist to this path
        if(!parent.isFoundFile(file_path))
            return false;

        //get allocated blocks of the file
        int start=0, size=0;
        for(VirtualFile vf : parent.getFiles())
        {
            if(vf.getFilePath().equals(file_path))
            {
                vf.setDeleted(true);
                start = vf.getAllocatedBlocks().get(0);
                size = vf.getAllocatedBlocks().get(1);
            }
        }


        //de-alocated in the disk from index to the size
        disk.contiguous_deallocate(start, size);

        return true;
    }

    public void diskStructure()
    {
        rootDirectory.printDirectoryStructure(0);
    }

    public void displayFiles()
    {
        //Root Files
        for(VirtualFile vf : rootDirectory.getFiles())
        {
            if(!vf.getDeleted())
            {
                System.out.println(vf.getFilePath() + "        " + vf.getAllocatedBlocks());
            }

        }

        //Files of subdirectories of root
        for(Directory d : rootDirectory.getSubDirectory())
        {
            for(VirtualFile vf : d.getFiles())
            {
                if(!vf.getDeleted())
                {
                    System.out.println(vf.getFilePath() + "        " + vf.getAllocatedBlocks());
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
                    buffer.write(vf.getFilePath() + "        " + vf.getAllocatedBlocks());
                    buffer.newLine();
                    for (int i=vf.getAllocatedBlocks().get(0);i<vf.getAllocatedBlocks().get(0)+vf.getAllocatedBlocks().get(1);i++)
                        buffer.write(i+",");
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
                        buffer.write(vf.getFilePath() +"        " + vf.getAllocatedBlocks());
                        buffer.newLine();
                        for (int i=vf.getAllocatedBlocks().get(0);i<vf.getAllocatedBlocks().get(0)+vf.getAllocatedBlocks().get(1);i++)
                            buffer.write(i+",");
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
