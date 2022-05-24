package com.company;

import java.util.ArrayList;

public class Detect {



        public int totalProcesses;
        public int totalResources;
        public static int[][] needArray;
        public static int[][] maxArray;
    public static int[][] request;

        public static int[][] allocationMatrix;
        public static int[] availableArray;


        public Detect(int totalProcesses, int totalResources) {
            this.totalProcesses = totalProcesses;
            this.totalResources = totalResources;
            maxArray = new int[totalProcesses][totalResources];
            needArray = new int[totalProcesses][totalResources];
            allocationMatrix = new int[totalProcesses][totalResources + 1];
            availableArray = new int[totalResources];
        }


    public static void setRequest(int[][] request) {
        Detect.request = request;
    }

    void setAllocationMatrix(int[][] arr) {
            for (int i = 0; i < totalProcesses; i++) {
                for (int j = 0; j < totalResources; j++) {
                    allocationMatrix[i][j] = arr[i][j];
                }

            }
            for (int i = 0; i < totalProcesses; i++)
                allocationMatrix[i][totalResources] = 0;
        }

        void getAllocArray() {
            for (int i = 0; i < totalProcesses; i++) {
                for (int j = 0; j < totalResources + 1; j++) {
                    System.out.print(allocationMatrix[i][j]);System.out.print(" ");
                }
                System.out.println();
            }
        }

        void updateAvaiable(int arr[][], int process) {

            for (int i = 0; i < totalResources; i++) {
                availableArray[i] += arr[process][i];


            }


        }


        void setAvailble(int []arr){
            availableArray=arr;



        }





    public static void setNeedArray(int[][] needArray) {
        Detect.needArray = needArray;
    }

    void printNeedArr(){

            for(int i=0;i<totalProcesses;i++)
            {
                for(int j=0;j<totalResources;j++)
                {
                    System.out.print(needArray[i][j]);
                }
                System.out.println();
            }


        }


        void DetectProcess() {
            int count;
            for (int i = 0; i < totalProcesses; i++) {
                count = 0;
                for (int j = 0; j < totalResources; j++) {
                    if (allocationMatrix[i][j] < availableArray[j]) {
                        count++;
                    }

                }
                if (count == totalResources) {
                    if (allocationMatrix[i][totalResources - 1] == 0) {
                        allocationMatrix[i][totalResources - 1] = 1;

                        updateAvaiable(allocationMatrix, i);


                    }


                }


            }
            int ctr=0;
            loop1:for (int x=0;x<totalProcesses;x++ ){

                if (allocationMatrix[x][totalResources-1]!=1){
                    System.out.println(" Unsafe State");
                    //break loop1;



                }
                else {ctr++;}



            }
            if (ctr==totalProcesses)
            {
                System.out.println("System is in Safe state");
            }
            else
            {
                recovery();
                System.out.println("System is in Unsafe state");
            }









        }

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

void detect(){
    ArrayList<Integer> done_Process=new ArrayList<>();

    boolean[]flag =new boolean[totalResources];
    for(int l=0;l<totalResources;l++)flag[l]=true;



for(int k=0;k<totalProcesses;k++){
    if(done_Process.contains(k)){
        k++;
    }
    else{
   int[]p= chooseProcess(k);
   for(int j=0;j<totalResources;j++){



    if(p[j]<=availableArray[j]){
        flag[j]=true;
    }
    else{
        flag[j]=false;

    }

   }

  if(checkP(flag)){
   performPro(k,allocationMatrix,availableArray);
   done_Process.add(k);
      k=0;
  }
  else{



  }


}
    if (done_Process.size()<totalProcesses-1){

        System.out.println("there is dead lock");
        recovery();
        System.out.println("recovery done");

    }

    else{
        System.out.println("no dead lock detected");
    }




}

}




void performPro(int process_num, int[][]alloc,int[]avail){
          for(int i =0;i<totalResources;i++){
              avail[i]+=alloc[process_num][i];


          }




}




boolean checkP(boolean[]flag){
    for(int i=0;i<totalResources;i++){
        if(flag[i]==false){
            return false;

        }
        else{}


    }



 return true;
}

    int[]  chooseProcess(int i) {
        int[] process = new int[totalResources];


        for (int j = 0; j < totalResources; j++) {

            process[j] = allocationMatrix[i][j];


        }

return process;
    }


}
