package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {

        Directory root = new Directory("root");
        VirtualDisk disk = new VirtualDisk(80); //Assume size of virtual disk
        Contiguous contiguous = new Contiguous(disk);
        Indexed indexed = new Indexed(disk);
        Linked linked = new Linked(disk);

        Scanner in = new Scanner(System.in);
        boolean flag = true;
        while (flag)
        {
            System.out.print(">>");
            String command = in.nextLine();
            String[] str = command.trim().split(" ");
            switch (str[0]) {
                case "CreateFile" -> {
                    boolean fileCheck = linked.createFile(str[1], Integer.parseInt(str[2]));
                    String message = fileCheck ? "Done" : "Already exist or No enough space or Invalid path";
                    System.out.println(message);
                }
                case "CreateFolder" -> {
                    boolean folderCheck = linked.createFolder(str[1]);
                    String message1 = folderCheck ? "Done" : "Already exist or No enough space or Invalid path";
                    System.out.println(message1);
                }
                case "DeleteFolder" -> {
                    boolean folderDelCheck = linked.deleteFolder(str[1]);
                    String message2 = folderDelCheck ? "Done" : "Already exist or No enough space or Invalid path";
                    System.out.println(message2);
                }
                case "DeleteFile" -> {
                    boolean fileDelCheck = linked.deleteFile(str[1]);
                    String message3 = fileDelCheck ? "Done" : "Already exist or No enough space or Invalid path";
                    System.out.println(message3);
                }
                case "DisplayDiskStatus" -> disk.diskStatus();
                case "DisplayDiskStructure" -> {
                    linked.diskStructure();
                    linked.displayFiles();
                }
                case "exit" -> {
                    linked.write("Disk.vfs");
                    System.out.println("Data saved to file 'Disk.vfs'");
                    flag = false;
                }
            }







            ///// INDEXED ALLOCATION TECHNIQUE TEST
            /*
           switch (str[0]) {
                case "CreateFile" -> {
                    boolean fileCheck = indexed.createFile(str[1], Integer.parseInt(str[2]));
                    String message = fileCheck ? "Done" : "Already exist or something wrong";
                    System.out.println(message);
                }
                case "CreateFolder" -> {
                    boolean folderCheck = indexed.createFolder(str[1]);
                    String message1 = folderCheck ? "Done" : "Already exist or something wrong";
                    System.out.println(message1);
                }
                case "DeleteFolder" -> {
                    boolean folderDelCheck = indexed.deleteFolder(str[1]);
                    String message2 = folderDelCheck ? "Done" : "No exist or something wrong";
                    System.out.println(message2);
                }
                case "DeleteFile" -> {
                    boolean fileDelCheck = indexed.deleteFile(str[1]);
                    String message3 = fileDelCheck ? "Done" : "No exist or something wrong";
                    System.out.println(message3);
                }
                case "DisplayDiskStatus" -> indexed.diskStatus();
                case "DisplayDiskStructure" -> {
                    indexed.diskStructure();
                    indexed.displayFiles();
                }
                case "exit" -> {
                       flag = false;
                       indexed.write("Disk.vfs);
                }

            }

             */


            ///CONTIGUOUS ALLOCATION TEST MAIN
            /*

           switch (str[0]) {
                case "CreateFile" -> {
                    boolean fileCheck = contiguous.createFile(str[1], Integer.parseInt(str[2]));
                    String message = fileCheck ? "Done" : "Already exist or something wrong";
                    System.out.println(message);
                }
                case "CreateFolder" -> {
                    boolean folderCheck = contiguous.createFolder(str[1]);
                    String message1 = folderCheck ? "Done" : "Already exist or something wrong";
                    System.out.println(message1);
                }
                case "DeleteFolder" -> {
                    boolean folderDelCheck = contiguous.deleteFolder(str[1]);
                    String message2 = folderDelCheck ? "Done" : "No exist or something wrong";
                    System.out.println(message2);
                }
                case "DeleteFile" -> {
                    boolean fileDelCheck = contiguous.deleteFile(str[1]);
                    String message3 = fileDelCheck ? "Done" : "No exist or something wrong";
                    System.out.println(message3);
                }
                case "DisplayDiskStatus" -> disk.diskStatus();
                case "DisplayDiskStructure" -> {
                    contiguous.diskStructure();
                    contiguous.displayFiles();
                }
                case "exit" -> {
                    contiguous.write("Disk.vfs");
                    flag = false;
                }
            }

             */


            //LINKED ALLOCATION TEST MAIN
            /*

           switch (str[0]) {
                case "CreateFile" -> {
                    boolean fileCheck = linked.createFile(str[1], Integer.parseInt(str[2]));
                    String message = fileCheck ? "Done" : "Already exist or something wrong";
                    System.out.println(message);
                    linked.displayFiles();
                }
                case "CreateFolder" -> {
                    boolean folderCheck = linked.createFolder(str[1]);
                    String message1 = folderCheck ? "Done" : "Already exist or something wrong";
                    System.out.println(message1);
                }
                case "DeleteFolder" -> {
                    boolean folderDelCheck = linked.deleteFolder(str[1]);
                    String message2 = folderDelCheck ? "Done" : "No exist or something wrong";
                    System.out.println(message2);
                }
                case "DeleteFile" -> {
                    boolean fileDelCheck = linked.deleteFile(str[1]);
                    String message3 = fileDelCheck ? "Done" : "No exist or something wrong";
                    System.out.println(message3);
                }
                case "DisplayDiskStatus" -> disk.diskStatus();
                case "DisplayDiskStructure" -> {
                    linked.diskStructure();
                    linked.displayFiles();
                }
                case "exit" -> {
                            linked.write("Disk.vfs");
                            flag = false;
                }
            }


             */






        }








    }
}
