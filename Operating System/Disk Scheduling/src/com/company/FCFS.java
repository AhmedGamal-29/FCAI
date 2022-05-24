package com.company;

class FCFS {
    private final int[] request;
    private final int head,Numofreq;
    private int totalMoves=0;

    public FCFS(int[] request, int num, int head)
    {
        this.request=request;
        Numofreq= num;
        this.head=head;
    }

    void algorithm(){
        int i;
        for(i=0;i<Numofreq;i++)
        {
            System.out.println(request[i]+" is served");
            if (i==0){
                totalMoves+=Math.abs(head-request[i]);
            }
            else
                totalMoves+=Math.abs(request[i]-request[i-1]);

        }
        System.out.println("Total head movements is "+ totalMoves + " Cylinders");
    }


}
