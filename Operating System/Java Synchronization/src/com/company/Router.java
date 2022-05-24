package com.company;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Router {
    List<Integer> connOrder = new ArrayList<>();
    Semaphore semaphore ;

    Router(int size)
    {
        semaphore = new Semaphore(size);
        for(int i=1;i<=size;i++)
        {
            connOrder.add(i);
        }

    }

    public synchronized int getConnOrder() {
        int x= connOrder.get(0);
        connOrder.remove(0);
        return x;
    }

    public synchronized void setConnOrder(int x) {
        connOrder.add(x);
    }


    public void occupy(Device device) throws IOException{
        FileWriter myFile = new FileWriter("network.txt",true);
        semaphore.P(device);
        myFile.write( device.getDeviceName() + " Occupied\n");
        myFile.close();
    }

    public void performActivities(Device device) throws IOException{
        FileWriter myFile = new FileWriter("network.txt",true);
        device.setId(getConnOrder());
        Random random = new Random();
        int rand= random.nextInt(5000);
        myFile.write(device.getDeviceName()+ " Perform online activities\n");
        myFile.close();

        try {
            Thread.sleep(rand);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void release(Device device) throws IOException{
        FileWriter myFile = new FileWriter("network.txt",true);
        setConnOrder(device.getDeviceId());
        myFile.write( device.getDeviceName() + " Logged out\n");
        myFile.close();
        semaphore.V();
    }










}
