package com.company;

import java.util.ArrayList;



public class VirtualFile {

    private final String fileName;
    private final String filePath;
    private final ArrayList<Integer> allocatedBlocks;

    private MyLinkedList linkedBlocks;
    private boolean deleted;

    public VirtualFile( String path, ArrayList<Integer> allocatedBlocks)
    {
        this.filePath = path;
        this.allocatedBlocks = allocatedBlocks;
        deleted=false;
        linkedBlocks=new MyLinkedList();

        //get file name from the full path
        int index = filePath.lastIndexOf("/");
        this.fileName = filePath.substring(index + 1);
    }

    public String getFilePath() {
        return filePath;
    }
    public ArrayList<Integer> getAllocatedBlocks() {
        return allocatedBlocks;
    }
    public boolean getDeleted()
    {
        return deleted;
    }
    public void setDeleted(boolean deleted)

    {
        this.deleted = deleted;
    }
    public String getFileName() {
        return fileName;
    }


    public MyLinkedList getLinkedBlocks()
    {
        return linkedBlocks;
    }

    public void setLinkedBlocks(MyLinkedList linkedBlocks) {
        this.linkedBlocks = linkedBlocks;
    }


}
