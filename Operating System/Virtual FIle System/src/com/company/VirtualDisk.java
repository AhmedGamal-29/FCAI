package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class VirtualDisk
{
    private final int disk_size;
    private final int[] blocks;
    private int free_space;
    private final ArrayList<ArrayList<Integer>> indexed_allocations;
    private ArrayList<Integer> random_free_blocks;
    private final ArrayList<SpaceManager> spaces;

    public VirtualDisk(int N)
    {
        this.disk_size =N;
        this.free_space=N;
        blocks = new int[N];
        spaces = new ArrayList<>();
        indexed_allocations = new ArrayList<>(N);

        //Random used in linked allocation
        random_free_blocks = new ArrayList<>(N);
        for(int i =0;i<disk_size;i+=2)
        {
            random_free_blocks.add(i);
        }
        for(int i =1;i<disk_size;i+=2)
        {
            random_free_blocks.add(i);
        }
//        System.out.println(random_free_blocks);
//        System.out.println(random_free_blocks.size());


        //initialize all spaces is de-allocated
        for(int i=0;i<disk_size;i++)
            blocks[i]=0;
    }

    public int getFileIndex(ArrayList<Integer> arrayList)
    {
        return indexed_allocations.indexOf(arrayList);
    }

    //index of last allocated in the blocks
    public int getBestIndex( )
    {
        int x = -1;
        for(int i=0;i< blocks.length;i++)
        {
            if(blocks[i]==1)
            {
                x= i;
            }
        }

        return x;
    }

    //Contiguous [start, size of file]
    public void contiguous_allocate(ArrayList<Integer> alloc)
    {
        //To allocate in space or in the last free index of blocks
        boolean flag=true;

        int new_space_start =0 , new_space_end=0;

        for(SpaceManager space : spaces)
        {
            if(alloc.get(1)<=space.size && space.state)
            {
                for (int i=space.start;i< space.start + alloc.get(1);i++)
                {
                    blocks[i] = 1;
                }

                //Get the start, end remaining of the space after allocate blocks in it
                new_space_start= space.start + alloc.get(1);
                new_space_end = space.end;

                space.state=false;
                flag=false;
                break;
            }
        }

        //Adding remainder of the space into new space can be used
        SpaceManager spaceManager = new SpaceManager(new_space_start, new_space_end, true);
        spaces.add(spaceManager);

        //there is no space available
        if(flag)
        {
            for(int i=alloc.get(0);i<alloc.get(0)+alloc.get(1);i++)
                blocks[i]=1;
        }

        //Remove size of file from free space
        free_space-=alloc.get(1);
    }

    //Contiguous [start, size of file]
    public void contiguous_deallocate(int start, int size)
    {
        //Create space with same size of file to can be allocated in the disk
        SpaceManager spaceManager = new SpaceManager(start, start+size, true);
        spaces.add(spaceManager);

        //deallocate blocks
        for(int i=start;i<start+size;i++)
            blocks[i]=0;

        //Add size of file to free space
        free_space+=size;
    }

    //Contiguous best fit to get best space
    public int best_fit_index(int file_size)
    {
        int min = 0 , space_index = 0;
        for(SpaceManager s : spaces)
        {
            if(s.size>=file_size)
            {
                min = s.size - file_size;
                space_index=s.start;
                break;
            }
        }

        for(SpaceManager s : spaces)
        {
            if(s.size>=file_size && s.size-file_size<min)
            {
                min = s.size - file_size;
                space_index=s.start;
            }
        }

        return space_index;

    }

    //Indexed -->  index [blocks]
    public void indexed_allocate(ArrayList<Integer>allocationInd)
    {
        //To allocate in space or in the last of blocks
        boolean flag=true;

        int new_space_start =0 , new_space_end=0;

        //store allocation blocks of file
        indexed_allocations.add(allocationInd);

        for(SpaceManager space : spaces)
        {
            if(allocationInd.size()<=space.size && space.state)
            {
                for (int i=space.start;i< space.start + allocationInd.size();i++)
                {
                    blocks[i] = 1;
                }

                //Get the start, end remaining of the space after allocate blocks in it
                new_space_start= space.start + allocationInd.size();
                new_space_end = space.end;

                space.state=false;
                flag=false;
                break;
            }
        }

        //Adding remainder of the space into new space can be used
        SpaceManager spaceManager = new SpaceManager(new_space_start, new_space_end, true);
        spaces.add(spaceManager);

        //If there is no enough space in the first disk
        if(flag)
        {
            for (Integer integer : allocationInd) blocks[integer] = 1;
        }

        free_space-=allocationInd.size();
    }

    //Indexed -->  index [blocks]
    public void indexed_deallocate(ArrayList<Integer> alloc)
    {
        //deallocate files by its allocated blocks
        indexed_allocations.set(getFileIndex(alloc), null);

        //Create space with same size of file to can be allocated in the disk
        SpaceManager spaceManager = new SpaceManager(alloc.get(0), alloc.get(alloc.size()-1), true);
        spaces.add(spaceManager);
        for (Integer integer : alloc)
        {
            blocks[integer] = 0;
        }
        free_space+=alloc.size();

    }

    //Linked [start,end] linked blocks
    public void linked_allocate(ArrayList<Integer>alloc)
    {
        for (Integer integer: alloc)
        {
            blocks[integer]=1;
        }


        free_space-=alloc.size();

    }

    //Linked [start,end] linked blocks
    public void linked_deallocate(MyLinkedList alloc)
    {
        Node current = alloc.head;
        int size=0;
        while (current!=null)
        {
            //free blocks in the disk
            blocks[current.block]=0;
            random_free_blocks.add(current.block);

            current=current.next;
            size++;
        }

        free_space+=size;

    }

    //To allocate available blocks in linked allocation technique
    public ArrayList<Integer> getRandomFreeBlocks(int size)
    {
        ArrayList<Integer> result = new ArrayList<>();
        for(int i=0;i<size;i++)
        {
            result.add(random_free_blocks.get(0));
            random_free_blocks.remove(random_free_blocks.get(0));
        }

        return result;
    }

    public void diskStatus()
    {
        System.out.println("Empty Space in disk = " + free_space);
        System.out.println("Allocated Space in disk = " + (disk_size - free_space));

        System.out.println("---------Allocated Blocks---------");
        for(int i=0;i<blocks.length;i++)
        {
            if(blocks[i]==0)
                System.out.println("Blocks[" + i+ "]" + " is Empty ,");
            else
                System.out.println("Blocks[" + i+ "]" + " is Allocated ,");
        }

    }


    public int getDisk_size()
    {
        return disk_size;
    }

    public int getFree_space()
    {
        return free_space;
    }

    //use it to display disk status
    public int[] getBlocks()
    {
        return blocks;
    }

    //Use in Indexed allocation to get first index in suitable space
    public ArrayList<SpaceManager> getSpaces() {
        return spaces;
    }


}
