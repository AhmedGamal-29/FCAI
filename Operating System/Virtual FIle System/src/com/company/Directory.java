package com.company;

import java.util.ArrayList;

public class Directory {
    private final String directoryPath;

    private final String directoryName;
    private ArrayList<VirtualFile> files;
    private ArrayList<Directory> subDirectory;
    boolean deleted = false;

    public Directory(String path)
    {
        this.directoryPath = path;
        files = new ArrayList<>();
        subDirectory = new ArrayList<>();

        int index = directoryPath.lastIndexOf("/");
        this.directoryName = directoryPath.substring(index + 1);
    }

    public Directory()
    {
        this.directoryName="null";
        this.directoryPath="null";
        subDirectory=new ArrayList<>();
    }

    public void addFile(VirtualFile f)
    {
        this.files.add(f);
    }


    public Directory getSpecificDirectory(String file_path)
    {
        String[] str = file_path.split("/");
        int len = str.length;
        if(len<=2)
            return this;
        Directory d =new Directory();
        String dir_name=str[len-2];
        for (Directory directory : this.subDirectory)
        {
            if (directory.directoryName.equals(dir_name))
                d = directory;
        }

        return d;


    }

    public void addDirectory(Directory d)
    {
        this.subDirectory.add(d);
    }

    public void setFiles(ArrayList<VirtualFile> files)
    {
        this.files = files;
    }

    public ArrayList<VirtualFile> getFiles()
    {
        return files;
    }

    public ArrayList<Directory> getSubDirectory()
    {
        return subDirectory;
    }

    public void setSubDirectory(ArrayList<Directory> subDirectory) {
        this.subDirectory = subDirectory;
    }

    public String getDirectoryPath()
    {
        return directoryPath;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public boolean isFoundFile(String file_path)
    {

        for (VirtualFile file : files)
        {
            if(file.getFilePath().equals(file_path))
                return true;
        }

        return false;
    }
    public boolean isFoundDir(String dir_path)
    {

        for (Directory directory : subDirectory)
        {
            if(directory.directoryPath.equals(dir_path))
                return true;
            directory.isFoundDir(dir_path);
        }

        return false;
    }

    public void printDirectoryStructure(int level)
    {
        //Spaces to be readable in the structure
        for (int i = 0; i < level; i++)
        {
            System.out.print(" ");
        }

        if (!this.deleted)
        {
            System.out.print("<" + directoryName + ">");
            System.out.println();
            for (VirtualFile file : files)
            {
                for (int i = 0; i < level + 5; i++)
                {
                    System.out.print(" ");
                }
                System.out.println(file.getFileName() + (file.getDeleted() ? " is deleted" : ""));
            }
            for (Directory directory : subDirectory) {
                directory.printDirectoryStructure(level + 6);
            }
        }
        else
            System.out.println("<" + directoryName + "> is deleted");
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
