package com.company;

import java.io.IOException;

public class Device extends Thread{
    private String name,type;
    private final Router router;
    private int id;

    Device(Router router)
    {
        this.router=router;

    }


    @Override
    public void run() {

        try {
            router.occupy(this);
            router.performActivities(this);
            router.release(this);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public int getDeviceId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceName() {
        return name;
    }

    public void setDeviceName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }




}
