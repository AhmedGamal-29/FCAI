package com.company;

import java.util.Arrays;


class Optimized {
    private final int[] request;
    private final int lastreq;
    private int head=0;

    public Optimized(int[] request, int n)
    {
        this.request=request;
        Arrays.sort(request);
        lastreq= request[n-1];
    }

    void algorithm(){
        int i=0;
        for(head=0;head<=lastreq;head++)
        {
            if (request[i]==head)
            {
                System.out.println(request[i] +" is served");
                i++;
            }

        }
        System.out.println("Total head movements is "+ lastreq + " Cylinders");
    }





}