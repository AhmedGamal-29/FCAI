package com.company;

import java.util.ArrayList;


class SSTF {

    private final ArrayList<Integer> request;
    private final int  Numofreq;
    private int head,totalMoves = 0;
    private int[] diff;

    public SSTF(ArrayList<Integer> request, int num, int head) {
        this.request = request;
        Numofreq = num;
        this.head = head;
    }

    void difference() {
        diff= new int[request.size()];
        for (int j = 0; j < request.size(); j++) {
            diff[j] = Math.abs(request.get(j) - head);
        }
    }

    int minimum(){
        int temp=Integer.MAX_VALUE;
        int index = 0;
        for (int i=0;i<diff.length;i++){
            if (diff[i]<temp){
                temp=diff[i];
                index=i;
            }
        }
        return index;

    }

    void algorithm() {
        int i;
        for (i = 0; i < Numofreq; i++) {
            difference();
            int x=minimum();
            totalMoves+=diff[x];
            System.out.println(request.get(x) + " is served ");
            head=request.get(x);
            request.remove(x);


        }
        System.out.println("Total head movements is " + totalMoves + " Cylinders");
    }

}

