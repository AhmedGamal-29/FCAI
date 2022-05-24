package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int numberOfProcesses, numberOfResources;

        //create scanner class object to get input from user
        Scanner sc = new Scanner(System.in);

        // get total number of resources from the user
        System.out.println("Enter total number of processes");
        numberOfProcesses = sc.nextInt();

        // get total number of resources from the user
        System.out.println("Enter total number of resources");
        numberOfResources = sc.nextInt();


        //Banker Object to apply on the processes
        Banker bankers = new Banker(numberOfProcesses, numberOfResources);

        //get initial available resources from user
        int []arr=new int [numberOfResources];
        System.out.println("enter the available resources");
        for(int i=0;i<numberOfResources;i++)
        {
            arr[i]=sc.nextInt();
        }
        bankers.setAvailableArray(arr);

        //get allocation matrix from user
        System.out.println("enter allocation matrix");
        int [][] alloc_mat = new int[numberOfProcesses][numberOfResources];
        for(int i=0;i<numberOfProcesses;i++)
        {
            for(int j=0;j<numberOfResources;j++)
            {
                alloc_mat[i][j]=sc.nextInt();
            }
        }
        bankers.setAllocationArray(alloc_mat);

        //get max matrix from user
        System.out.println("Enter the Max matrix");
        int [][]max_mat=new int[numberOfProcesses][numberOfResources];

        for (int i = 0; i < numberOfProcesses; i++) {

            for (int j = 0; j < numberOfResources; j++) {
                max_mat[i][j]=sc.nextInt();

            }
        }
        bankers.setMaxMatrix(max_mat);


        //compute need matrix
        bankers.findNeedValue();
        bankers.printNeedArr();

        //Check state of the system

bankers.detect.setAllocationMatrix(alloc_mat);

        System.out.println("1- Banker with no detection  ");
        System.out.println("2- Banker with detection ");
        System.out.println("3- Recovery ");
        System.out.println("4- Quit ");

        int input = sc.nextInt();

        switch (input)
        {
            case 1:
                bankers.Scheduling();

                break;
            case 2:
                System.out.println("enter request matrix");
                int [][] req_matr = new int[numberOfProcesses][numberOfResources];
                for(int i=0;i<numberOfProcesses;i++)
                {
                    for(int j=0;j<numberOfResources;j++)
                    {
                        req_matr[i][j]=sc.nextInt();
                    }
                }
                bankers.detect.setRequest(req_matr);
                bankers.getAllocArray();
                System.out.println("------------------------------------");
                bankers.detect.detect();
                bankers.getAllocArray();
                //release
                break;
            case 3:


                //recovery
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Wrong Choice ");
        }

/*
safe test case

Allocation
0 1 0
2 0 0
3 0 2
2 1 1
0 0 2


Max
7 5 3
3 2 2
9 0 2
2 2 2
4 3 3

 0 1 0
 3 0 2
 3 0 2
 2 1 1
 3 3 0

*/


    }





}