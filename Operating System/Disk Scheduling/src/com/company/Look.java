package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class Look {
    private final ArrayList<Integer> request_list;
    private int  seekTime = 0, first_index=0;
    private final int head, num_requests;

    public Look(ArrayList<Integer> requests_list, int head) {
        this.request_list = requests_list;
        this.head = head;
        this.num_requests=requests_list.size();
        Collections.sort(requests_list);
    }

    //get index of first request > head
    void getFirstLargeIndex()
    {
        for(int i=0;i<num_requests;i++ )
        {
            if(request_list.get(i)>head)
            {
                first_index=i;
                break;
            }
        }
    }

    //requests larger head then request smaller than head
    void forward()
    {
        // serving requests > head
        for(int i=first_index;i<num_requests;i++)
        {
            System.out.print(request_list.get(i) + " --> ");

            if(i==first_index)
            {
                seekTime += request_list.get(i)-head;
            }
            else
            {
                seekTime += request_list.get(i)-request_list.get(i-1);
            }

        }


        // Process < head
        for(int j=first_index-1;j>=0;j--)
        {
            System.out.print(request_list.get(j) + " --> ");

            if(j==first_index-1)
            {
                seekTime += Math.abs( request_list.get(num_requests-1) - request_list.get(j) );
            }
            else
            {
                seekTime += Math.abs(request_list.get(j + 1) - request_list.get(j));
            }
        }
    }


    //requests smaller head then requests larger than head
    void backward()
    {
        //serving request < head
        for(int i=first_index-1;i>=0;i--)
        {
            System.out.print(request_list.get(i)+ " --> ");
            if(i==first_index-1)
            {
                seekTime+=head-request_list.get(i);
            }
            else
            {
                seekTime+=request_list.get(i+1)-request_list.get(i);
            }
        }

        //serving requests>head
        for(int i=first_index;i<num_requests;i++)
        {
            System.out.print(request_list.get(i)+ " --> ");
            if(i==first_index)
            {
                seekTime+=request_list.get(i)-request_list.get(0);
            }
            else
            {
                seekTime+=request_list.get(i)-request_list.get(i-1);
            }
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
