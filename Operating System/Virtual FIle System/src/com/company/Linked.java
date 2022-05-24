package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class Linked {
    Directory rootDirectory = new Directory("root");
    VirtualDisk disk;

    public Linked(VirtualDisk disk) {
        this.disk = disk;
    }

    public boolean createFile(String file_path, int sizeKB)
    {
        Directory parent = rootDirectory.getSpecificDirectory(file_path);

        //check if there is already exist
        if (parent.isFoundFile(file_path) || parent.isDeleted())
            return false;

        //No free space to allocate it
        if (sizeKB > disk.getFree_space())
            return false;


        ArrayList<Integer> randomFreeBlocks = disk.getRandomFreeBlocks(sizeKB);

        //adding start index , end index
        ArrayList<Integer> allocatedBlocks = new ArrayList<>();
        allocatedBlocks.add(randomFreeBlocks.get(0));
        allocatedBlocks.add(randomFreeBlocks.get(randomFreeBlocks.size() - 1));

        //linked list of the file
        MyLinkedList linkedList = new MyLinkedList();

        for (int i = 0; i < sizeKB; i++) {
            linkedList.insert(linkedList, randomFreeBlocks.get(i));
        }


        disk.linked_allocate(randomFreeBlocks);

        //create the file and add it to tree
        VirtualFile file = new VirtualFile(file_path, allocatedBlocks);
        file.setLinkedBlocks(linkedList);
        parent.addFile(file);

        return true;

    }

    public boolean deleteFile(String file_path)
    {
        Directory parent = rootDirectory.getSpecificDirectory(file_path);

        //if there is no exist to this path
        if (!parent.isFoundFile(file_path))
            return false;

        for (VirtualFile vf : parent.getFiles())
        {
            if (vf.getFilePath().equals(file_path)) {
                vf.setDeleted(true);

                //de-allocated the blocks of the file in the disk
                disk.linked_deallocate(vf.getLinkedBlocks());
                break;
            }
        }
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

    public void diskStructure()
    {
        rootDirectory.printDirectoryStructure(0);
    }

    public void displayFiles()
    {
        //Files in the root
        for (VirtualFile vf : rootDirectory.getFiles()) {
            if (!vf.getDeleted()) {
                System.out.println(vf.getFilePath() + "        " + vf.getAllocatedBlocks());
                vf.getLinkedBlocks().printList(vf.getLinkedBlocks());
            }

        }

        //Files in every subdirectory in root
        for (Directory d : rootDirectory.getSubDirectory()) {
            for (VirtualFile vf : d.getFiles()) {
                if (!vf.getDeleted()) {
                    System.out.println(vf.getFilePath() + "        " + vf.getAllocatedBlocks());
                    vf.getLinkedBlocks().printList(vf.getLinkedBlocks());
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
                    Node currNode = vf.getLinkedBlocks().head;
                    while (currNode != null)
                    {
                        buffer.write(currNode.block + " --> ");
                        currNode = currNode.next;
                    }
                    buffer.write("null");
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
                        buffer.write(vf.getFilePath() + "        " + vf.getAllocatedBlocks());
                        buffer.newLine();
                        Node currNode = vf.getLinkedBlocks().head;
                        while (currNode != null)
                        {
                            buffer.write(currNode.block + " --> ");
                            currNode = currNode.next;
                        }
                        buffer.write("null");

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

            //All blocks of the disk
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

};
