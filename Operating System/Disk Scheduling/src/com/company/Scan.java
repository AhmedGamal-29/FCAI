package com.company;
import java.util.ArrayList;
import java.util.Collections;

public class Scan {
    private final int  head, num_request; //0-->200
    private int seekTime=0,first_index=0;
    final private ArrayList<Integer> requests_list;

    public Scan(ArrayList<Integer> requests_list, int head, int scheduling_limit)
    {
        this.requests_list = requests_list;
        this.head = head;
        this.requests_list.add(0);
        this.requests_list.add(scheduling_limit-1);
        Collections.sort(requests_list);
        this.num_request = requests_list.size();
    }


    //get index of first request > head
    void getFirstLargeIndex()
    {
        for(int i=0;i<num_request;i++ )
        {
            if(requests_list.get(i)>head)
            {
                first_index=i;
                break;
            }

        }
    }

    //Serving requests larger than head
    void forward()
    {
        // Process > head
        for(int i = first_index; i< num_request; i++)
        {
            System.out.print(requests_list.get(i) + " --> ");

            if(i==first_index)
            {
                seekTime += requests_list.get(i)-head;
            }
            else
            {
                seekTime += requests_list.get(i)- requests_list.get(i-1);
            }
        }

        // Process < head
        for(int j=first_index-1;j>0;j--)
        {
            System.out.print(requests_list.get(j) + " --> ");

            if(j==first_index-1)
            {
                seekTime += Math.abs( requests_list.get(j) - requests_list.get(num_request-1) ); // Scheduling 0 --> 199
            }
            else
            {
                seekTime += Math.abs(requests_list.get(j+1) - requests_list.get(j));
            }
        }
    }

    //Serving requests less than head
    void backward()
    {
        // Process < head
        for(int j=first_index-1;j>=0;j--)
        {
            System.out.print(requests_list.get(j) + " --> ");

            if(j==first_index-1)
            {
                seekTime += Math.abs( head - requests_list.get(j) ); // Scheduling 0 --> 199
            }
            else
            {
                seekTime += Math.abs(requests_list.get(j+1) - requests_list.get(j));
            }
        }

        for(int i=first_index;i<num_request-1;i++)
        {
            System.out.print(requests_list.get(i) + " --> ");

            if(i==first_index)
            {
                seekTime += requests_list.get(i);
            }
            else
            {
                seekTime += requests_list.get(i) - requests_list.get(i-1);
            }
        }


    }


    //Serving all requests
    void scheduling(int x)
    {

        //serving head
        System.out.print(head + " --> ");
        this.getFirstLargeIndex();

        //Forward Scheduling
        if(x==1)
        {
            this.forward();

        }


        //Backward Scheduling
        else
        {
            this.backward();

        }
        System.out.println("Finish");
        System.out.println("Total head movements is "+ seekTime + " Cylinders");



    }
}
