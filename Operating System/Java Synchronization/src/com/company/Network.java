package com.company;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class Network {
    public Device [] devices;
    private final int allDevices;

    Network(int cap , int allDevices)
    {

        this.allDevices=allDevices;
        Router router = new Router(cap);
        this.devices = new Device[allDevices];

        for (int i=0; i<allDevices; i++){
            devices[i] = new Device(router);

            String input =  JOptionPane.showInputDialog("Enter name (space) type of Device " + (i+1));
            devices[i].setDeviceName( input.substring(0,input.indexOf(" ")));
            devices[i].setType(input.substring(input.indexOf(" ")+1));



        }
    }

    public void startNetwork(){
        for (int i=0; i<allDevices; i++){
            devices[i].start();
        }
    }

    public static void main(String[] args) {

        String maxConnections = JOptionPane.showInputDialog("Enter max wi-fi connections: ");


        String numDevices = JOptionPane.showInputDialog("Enter number of devices want to connect: ");

        Network network = new Network(Integer.parseInt(maxConnections),Integer.parseInt(numDevices));
        network.startNetwork();

        // get network details of the devices from the text file to show it in messageDialog
        try
        {
            FileInputStream FIS = new FileInputStream("network.txt");
            Scanner sc=new Scanner(FIS);    //file to be scanned

            while(sc.hasNextLine())
            {
                JOptionPane.showMessageDialog(null, sc.nextLine());      //returns the line that was skipped
            }
            //close the scanner
            sc.close();


        }
        catch(IOException e)
        {
            e.printStackTrace();
        }


     /*
     To clear the text file after finish the execution
     try{

            FileWriter fw = new FileWriter("network.txt", false);

            PrintWriter pw = new PrintWriter(fw, false);

            pw.flush();

            pw.close();

            fw.close();

        }catch(Exception exception){

            System.out.println("Exception have been caught");

        }
        */


    }
}
