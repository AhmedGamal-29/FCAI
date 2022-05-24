package com.company;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

class Semaphore {
    protected int value = 0 ;
    protected Semaphore() { value = 0 ; }
    protected Semaphore(int initial) { value = initial ; }

    public synchronized void P(Device device) throws IOException {
        FileWriter myFile = new FileWriter("network.txt",true);

        value-- ;
        if (value < 0){

                myFile.write(device.getDeviceName() +" "+ device.getType() + " Arrived and Waiting !!\n");
                myFile.close();

            try {
                wait() ;
            }
            catch( InterruptedException e )
            {
                e.getMessage();
            }
        }
        else
        {
                myFile.write( device.getDeviceName() +" "+ device.getType() + " Arrived \n");
                 myFile.close();

        }

    }
    public synchronized void V() {
        value++ ;
        if (value <= 0) notify() ;
    }

}

