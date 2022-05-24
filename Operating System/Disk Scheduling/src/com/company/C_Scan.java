package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class C_Scan {
    private final  ArrayList<Integer> requests_list;
    private final int head, num_requests;
    private int seekTime=0,first_index=0;

    public C_Scan(ArrayList<Integer> requests_list, int head ,int scheduling_limit)
    {
        this.requests_list = requests_list;
        this.head = head;
        requests_list.add(scheduling_limit-1);
        requests_list.add(0);
        this.num_requests = requests_list.size();
        Collections.sort(requests_list);

    }

    //get index of first request > head
    void getFirstLargeIndex()
    {
        for(int i=0;i<num_requests;i++ )
        {
            if(requests_list.get(i)>head)
            {
                first_index=i;
                break;
            }

        }
    }

    void forward()
    {
        // Process > head
        for(int i = first_index; i< num_requests; i++)
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
        for(int j=0;j<first_index;j++)
        {
            System.out.print(requests_list.get(j) + " --> ");

            if(j==0)
            {
                seekTime += requests_list.get(num_requests-1) ; // Scheduling 0 --> 199
            }
            else
            {
                seekTime += Math.abs(requests_list.get(j) - requests_list.get(j-1));
            }

        }

    }

    void backward()
    {
        //requests < head
        for(int i=first_index-1;i>=0;i--)
        {
            System.out.print(requests_list.get(i)+" --> ");
            if(i==first_index-1)
            {
                seekTime+= head - requests_list.get(i);
            }

            else
            {
                seekTime+= requests_list.get(i+1) - requests_list.get(i);
            }

        }


        //requests > head
        for(int j=num_requests-1;j>=first_index;j--)
        {
            System.out.print(requests_list.get(j)+" --> ");
            if(j==num_requests-1)
            {
                seekTime+= requests_list.get(num_requests-1);
            }
            else
            {
                seekTime+= requests_list.get(j+1) - requests_list.get(j);
            }
        }


    }

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
        System.out.println("Total head movements is " + seekTime + " Cylinders");
    }
}
