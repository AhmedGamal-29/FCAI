package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

 public static void main(String[] args) {
  int SHED_LIMIT = 200;

  int num_req,head_pos,f_b;
  Scanner input=new Scanner(System.in);

  System.out.println("Enter number of i\\o requests: ");
  num_req=input.nextInt();

  System.out.println("Enter current head position: ");
  head_pos=input.nextInt();

  System.out.println("1-Forward");
  System.out.println("2-Backward");
  f_b = input.nextInt();


  ArrayList<Integer> arr1 = new ArrayList<>();
  int [] arr2 = new int[num_req];

  for(int i=0;i<num_req;i++)
  {
   System.out.println("Enter request number"+  (i+1));
   int l = input.nextInt();
   arr1.add(l);
   arr2[i]=l;
  }


  System.out.println("Choose from the following: ");
  System.out.println("1-FCFS Scheduling");
  System.out.println("2-SSTF Scheduling");
  System.out.println("3-SCAN Scheduling");
  System.out.println("4-C_SCAN Scheduling");
  System.out.println("5-LOOK Scheduling");
  System.out.println("6-C_LOOK Scheduling");
  System.out.println("7-OPTIMIZED Scheduling");


   int choice = input.nextInt();
   switch (choice)
   {
    //FCFS
    case 1:
     FCFS fcfs = new FCFS(arr2, num_req,head_pos);
     fcfs.algorithm();
     break;

    //SSTF
    case 2:
     SSTF sstf = new SSTF(arr1, num_req,head_pos);
     sstf.algorithm();
     break;

    //SCAN
    case 3:
     Scan scan = new Scan(arr1,head_pos,SHED_LIMIT);
     scan.scheduling(f_b);
     break;

    //C_SCAN
    case 4:
     C_Scan c_scan = new C_Scan(arr1,head_pos,SHED_LIMIT);
     c_scan.scheduling(f_b);
     break;

    //LOOK
    case 5:
     Look look = new Look(arr1,head_pos);
     look.scheduling(f_b);
     break;

    //C_LOOK
    case 6:
     C_Look c_look = new C_Look(arr1, head_pos, SHED_LIMIT);
     c_look.scheduling(f_b);
     break;

    //OPTIMIZED
    case 7:
     Optimized optimized = new Optimized(arr2,num_req);
     optimized.algorithm();
     break;

    default:
     System.out.println("Wrong choice");
     break;


   }




/*

  //Lecture Example

  int [] arr = {98,183,37,122,14,124,65,67};
  ArrayList<Integer> arr1 =new ArrayList<>();
  arr1.add(98);
  arr1.add(183);
  arr1.add(37);
  arr1.add(122);
  arr1.add(14);
  arr1.add(124);
  arr1.add(65);
  arr1.add(67);

  FCFS f = new FCFS(arr,8,53);
  f.algorithm();

 Scan x = new Scan(arr1,53,SHED_LIMIT);
 x.scheduling(1);
 */



 }

}