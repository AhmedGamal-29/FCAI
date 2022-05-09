package com.example.demo.Controllers;
import com.example.demo.View.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Driver_Service {
    private  Driver Driver;
    private  Wallet wallet;

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
    public Wallet getWallet() { return wallet; }
    public void setMoney(double money) {wallet.setMoney(money);}

    public void setDriver(Driver driver) {
        isVerified=false;
        Driver = driver;
        this.wallet= new Wallet();
    }
    public Driver getDriver() {return Driver;}

    private boolean isVerified;
    private int Trip_Index;
    private String Source_Area;
    private final ArrayList<Customer_Service> All_Request=new ArrayList<>();
    private final ArrayList<Trip> fav_Trips =new ArrayList<>();
    private final ArrayList<Double> ratings =new ArrayList<>();


    public void Verify() { isVerified = true; }
    public boolean setSource_Area(String source_Area) { Source_Area = source_Area;return true;}
    public void set_Trip(Trip trip) {
        trip.Source_Area=getSource_Area();
        this.fav_Trips.add(trip);
    }
    public void setRate(double rate) { ratings.add(rate); }
    public void setTripIndex(int indexOf) { Trip_Index=indexOf; }
    public void newRequest(Customer_Service customerFunction) { All_Request.add(customerFunction); }


    public ArrayList<Trip> getTrips() { return fav_Trips; }
    public String getSource_Area(){return Source_Area;}
    public boolean getVerified() { return isVerified; }
    public double getRate() {
        double average=0.0;
        for (double rate:ratings){ average+=rate; }
        average=average/ratings.size();
        return average;
    }
    public int getTrip_Index() { return Trip_Index; }
    public ArrayList<Customer_Service> getAll_Request() { return All_Request; }

}
