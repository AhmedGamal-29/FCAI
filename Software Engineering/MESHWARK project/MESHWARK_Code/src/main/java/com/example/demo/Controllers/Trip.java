package com.example.demo.Controllers;

import com.example.demo.View.Customer;
import com.example.demo.View.Driver;

import java.lang.String;
public class Trip {
    public String Source_Area;
    public String Destination;
    private int Passenger_Number;
    private double Driver_Price;
    private double new_price;
    public Driver Driver;
    public Customer Customer;
    public String price_Time;
    public String User_Accept_Time;
    public String Driver_arrived_user_Location;
    public String Driver_arrived_user_Destination;
    private  Discount discount;
    public Trip() {
        Source_Area="";
        Destination ="";
        Driver_Price =0.0;
        new_price=0.0;
        Passenger_Number=0;
        price_Time="";
        User_Accept_Time="";
        Driver_arrived_user_Location="";
        Driver_arrived_user_Destination="";
        discount=new Discount();
    }
    public void setDiscount(Discount discount){ this.discount=discount; }
    public void setPrice(double price){ Driver_Price =price; new_price=price; }

    public void set_new_Price(double new_Price) { this.new_price = new_Price; }


    public double getDriver_Price() { return Driver_Price; }

    public void setPassenger_Number(int passenger_Number) { Passenger_Number = passenger_Number; }

    public int getPassenger_Number() { return Passenger_Number; }

    public Discount ModifyDiscount(){return discount;}
    public void Perform_Discount(){
        discount.setOldPrice(Driver_Price);
        new_price= discount.Perform_Discount();
    }

    public double getPrice() { return new_price;}
}

