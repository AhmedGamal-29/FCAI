package com.company;

import java.util.ArrayList;

class Banker {
    public int totalProcesses;
    public int totalResources;
    public static int[][] needMatrix;
    public static int[][] maxMatrix;
    public static int[][] allocationMatrix;
    public static int[] availableArray;
    public static int[] instances;
    ArrayList<String> processes;
    Detect detect;


    public Banker(int totalProcesses, int totalResources)
    {
        this.totalProcesses = totalProcesses;
        this.totalResources = totalResources;
        maxMatrix = new int[totalProcesses][totalResources];
        needMatrix = new int[totalProcesses][totalResources];
        allocationMatrix = new int[totalProcesses][totalResources + 1];
        availableArray = new int[totalResources];
        instances = new int[totalResources];
        processes = new ArrayList<>(totalProcesses);
    }

    void findNeedValue()
    {
        for (int i = 0; i < totalProcesses; i++) {
            for (int j = 0; j < totalResources; j++) {
                needMatrix[i][j] = maxMatrix[i][j] - allocationMatrix[i][j];
            }
        }
    }

    void setAllocationArray(int[][] arr)
    {
        for (int i = 0; i < totalProcesses; i++) {
            for (int j = 0; j < totalResources; j++) {
                allocationMatrix[i][j] = arr[i][j];
            }

        }
        for (int i = 0; i < totalProcesses; i++)
            allocationMatrix[i][totalResources] = 0;



    }

    void getAllocArray()
    {
        for (int i = 0; i < totalProcesses; i++)
        {
            for (int j = 0; j < totalResources + 1; j++)
            {
                System.out.print(allocationMatrix[i][j]);System.out.print(" ");
            }
            System.out.println();
        }
    }

    void updateAvailable(int arr[][], int process)
    {
        for (int i = 0; i < totalResources; i++)
        {
            availableArray[i] += arr[process][i];
        }


    }

    void setAvailableArray(int []arr)
    {
        availableArray=arr;
    }

    void setMaxMatrix(int[][] max_mat)
    {
        maxMatrix = max_mat;
    }

    void printNeedArr()
    {
        System.out.println("----Need Matrix----");
        for(int i=0;i<totalProcesses;i++)
        {
            for(int j=0;j<totalResources;j++)
            {
                System.out.print(needMatrix[i][j] +" ");
            }
            System.out.println();
        }


    }

    void getInstances()
    {
        for(int i=0;i<totalResources;i++)
        {
            int sum=availableArray[i];
            for (int j=0;j<totalProcesses;j++)
            {
                sum+=allocationMatrix[j][i];
            }
            instances[i]=sum;

        }
    }


    boolean isFinished()
    {
        for(int i=0;i<totalResources;i++)
        {
            if(instances[i]!=availableArray[i])
                return false;
        }

        return true;
    }

    void Scheduling()
    {
        this.getInstances();

        while (!isFinished())
        {
            System.out.println("infinite");
            int count;
            for (int i = 0; i < totalProcesses; i++)
            {
                count = 0;
                for (int j = 0; j < totalResources; j++) {
                    if (needMatrix[i][j] <= availableArray[j]) {
                        count++;
                    }

                }
                if (count == totalResources)
                {
                    if (allocationMatrix[i][totalResources] == 0)
                    {
                        allocationMatrix[i][totalResources] = 1;
                        updateAvailable(allocationMatrix, i);
                        processes.add("P"+i);

                    }

                }


            }
        }
            int ctr=0;
            for (int x=0;x<totalProcesses;x++ )
            {
                if (allocationMatrix[x][totalResources]==1)
                {
                    ctr++;
                }

            }
            if (ctr==totalProcesses)
            {
                System.out.println("System is in Safe state");
                System.out.println(processes);
            }
            else
            {
                recovery();
                System.out.println("System is in Unsafe state");
            }

        }
//recovery  function

    void recovery() {
        int RowNum = getMaxProcess();
        for (int i = 0; i < totalResources - 1; i++) {
            int x = allocationMatrix[RowNum][i];
            availableArray[i] += x;
            allocationMatrix[RowNum][i] = 0;
        }
        allocationMatrix[RowNum][totalProcesses] = 1;

        for (int i = 0; i < totalResources - 1; i++) {
            System.out.println("Allocation array");

            System.out.println(allocationMatrix[RowNum][i]);
        }

        for (int i = 0; i < totalResources - 1; i++) {
            System.out.println("Avl array");
            System.out.println(availableArray[i]);
        }

    }

        int  getMaxProcess() {
       // int []max=new int[totalResources];
        int pack=0;
        int tmp=0;
        int row_num=0;
        for (int i =0;i<totalProcesses;i++){

            for(int j=0;j<totalResources;j++){

                tmp+=allocationMatrix[i][j];
                System.out.println(tmp);




            }

            if (tmp>pack){
                pack=tmp;
                System.out.println("pack"+pack);
                row_num=i;


            }
            tmp=0;



        }

return row_num;

        }


    public void setDetect() {
        detect=new Detect(totalProcesses,totalResources)
        ;
    }
}

