package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class C_Look {
    private final ArrayList<Integer> requests_list;
    private final int head, num_requests;
    private int seekTime = 0, first_index=0;

    public C_Look(ArrayList<Integer> requests_list, int head, int scheduling_limit)
    {
        this.requests_list = requests_list;
        this.requests_list.add(0);
        this.requests_list.add(scheduling_limit-1);
        this.num_requests = requests_list.size() ;
        this.head = head;
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
        // serving requests > head
        for(int i = first_index; i< num_requests-1; i++)
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
        for(int j=0; j<first_index;j++)
        {
            System.out.print(requests_list.get(j) + " --> ");

            if(j==0)
            {
                seekTime += Math.abs( requests_list.get(num_requests-2) - requests_list.get(j) );
            }
            else
            {
                seekTime += Math.abs(requests_list.get(j) - requests_list.get(j-1));
            }

        }
    }


    void backward()
    {
        // serving requests < head
        for(int i=first_index-1;i>0;i--)
        {
            System.out.print(requests_list.get(i)+" --> ");
            if(i==first_index)
                seekTime+= head-requests_list.get(i);
            else
                seekTime+= requests_list.get(i+1) - requests_list.get(i);
        }

        // serving requests > head
        for(int i=num_requests-1;i>=first_index;i--)
        {
            System.out.print(requests_list.get(i)+" --> ");
            if(i==num_requests-1)
                seekTime+= requests_list.get(i) - requests_list.get(1);
            else
                seekTime+= requests_list.get(i+1) - requests_list.get(i);
        }


    }

    void scheduling(int x)
    {
        //serving head
        System.out.print(head + " --> ");
        this.getFirstLargeIndex();

        if(x==1)
        {
            this.forward();
        }
        else
        {
            this.backward();
        }

        System.out.println("Finish");
        System.out.println("Total head movements is "+ seekTime + " Cylinders");

    }
}
