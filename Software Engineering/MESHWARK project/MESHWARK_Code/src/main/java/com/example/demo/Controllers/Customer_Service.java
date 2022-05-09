package com.example.demo.Controllers;

import com.example.demo.View.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Customer_Service {
    private Customer Customer;
    private  Wallet wallet;
    private int Trip_Number;
    private boolean enter;
    private Driver_Service Driver_Ride;
    private Trip trip;
    private ArrayList<Driver_Service> Drivers_Available=new ArrayList<>();

    public void setCustomer(Customer user){
        this.Customer=user;
        this.wallet= new Wallet();
    }
    public Customer getCustomer() { return Customer; }

    public void setEnter(boolean enter) {this.enter = enter;}

    public boolean getEnter() {return enter;}

    public boolean setMoney(double money) {return wallet.setMoney(money);}
    public double getMoney(){return wallet.getMoney();}


    public void setDrivers_Available(ArrayList<Driver_Service> drivers){
        Drivers_Available.addAll(drivers);
    }
    @JsonProperty("All_Drivers")public ArrayList<Driver_Service>getAll_Drivers(){return Drivers_Available;}

    public boolean setTrip(Trip trip) { this.trip = trip;   return true;}
    @JsonProperty("Trip_Details")public Trip getTrip() { return trip; }

    public  void setDriver_Ride(Driver_Service driver_Ride) {Driver_Ride = driver_Ride;}
    @JsonProperty("Driver_Ride")public Driver_Service getDriver_Ride(){return Driver_Ride;}

    public void setTrip_Number(int trip_Number) { Trip_Number = trip_Number; }
    @JsonProperty("Trip_Number") public int getTrip_Number() { return Trip_Number; }

    public void rateDriver(Driver_Service dr, double rating){ dr.setRate(rating); }

    public boolean Check_Have_Money(double price) {
        return price<=wallet.getMoney();
    }

    public void Update_Money(double price) {wallet.Update_Money(price);}
}
