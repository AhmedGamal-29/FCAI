package com.example.demo.Controllers;
import com.example.demo.Model.DataBase;
import com.example.demo.View.Admin;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

public class Admin_Service {
    private  DataBase dataBase;
    private static Admin_Service Unique_Instance;
    private final ArrayList<Trip> All_Trips=new ArrayList<>();
    private final ArrayList<String>Discount_Area=new ArrayList<>();

    private Admin_Service(){}
    public void setAdmin() {Admin.getIns();}
    public static Admin_Service getIns(){
        if (Unique_Instance==null)Unique_Instance=new Admin_Service();
        return Unique_Instance;
    }

    public void setDataBase(DataBase dataBase) {this.dataBase = dataBase;}
    public DataBase getDataBase() {return dataBase;}

    public boolean setDiscount_Area(String area) {
        for (Driver_Service driver :dataBase.getDrivers()){
            for (Trip trip:driver.getTrips())
                if (area.equalsIgnoreCase(trip.Destination)){
                    trip.ModifyDiscount().setAdmin_Discount(true);
                }
        }
        return Discount_Area.add(area);
    }
    public ArrayList<String> getDiscount_Area() { return Discount_Area; }
    public boolean Delete_Discount_Area(String area){
        for (String ar:Discount_Area){
            if (area.equalsIgnoreCase(ar))return Discount_Area.remove(ar);
        }
        return false;
    }
    public void Add_Trip(Trip Trip) { All_Trips.add(Trip); }

    public ArrayList<Trip> getAll_Trips() { return All_Trips; }
    public void Perform_Discount(Trip trip, Customer_Service client){
        trip.ModifyDiscount().setFirst_Trip(client.getTrip_Number()==1);
        trip.ModifyDiscount().setDate(java.time.LocalDate.now().toString(),client.getCustomer().getBirthday());
        trip.ModifyDiscount().setPassenger_Number(client.getTrip().getPassenger_Number());
        trip.Perform_Discount();
    }
    public void Delete_Discount(Trip trip){
        boolean save=trip.ModifyDiscount().isAdmin_Discount();
        trip.setDiscount(new Discount());
        trip.set_new_Price(trip.getDriver_Price());
        trip.ModifyDiscount().setAdmin_Discount(save);
    }
    public double return_New_Price(Trip trip ,Customer_Service customer){
        boolean First_Trip=(customer.getTrip_Number()+1)==1;
        boolean BirthDay=new Discount().setDate(java.time.LocalDate.now().toString(),customer.getCustomer().getBirthday());
        boolean Passenger_Number=customer.getTrip().getPassenger_Number()>=2;
        double OldPrice=trip.getDriver_Price();
        boolean Admin_Discount=trip.ModifyDiscount().isAdmin_Discount();
        boolean check_Holiday=trip.ModifyDiscount().check_Holiday(trip.ModifyDiscount().getDate());
        double Discount_Percentage=0.0;
        if (First_Trip) Discount_Percentage+=0.10;
        if (Admin_Discount) Discount_Percentage+=0.10;
        if (BirthDay) Discount_Percentage+=0.10;
        if (Passenger_Number ) Discount_Percentage+=0.05;
        if (check_Holiday)Discount_Percentage+=0.05;
        return OldPrice-(OldPrice*Discount_Percentage);
    }



    public boolean suspend (String Email,boolean state) {
        for(Customer_Service client : dataBase.getClients())
        {
            if(client.getCustomer().getEmail().equals(Email))
            {
                client.getCustomer().setSuspended(state);
            }

        }
        for(Driver_Service driver1 : dataBase.getDrivers())
        {
            if(driver1.getDriver().getEmail().equals(Email))
            {
                driver1.getDriver().setSuspended(state);
            }
        }
        return state;
    }
    public boolean Verify_ALL_Drivers(){
        for (Driver_Service drivers:dataBase.getDrivers()){drivers.Verify();}
        return true;
    }
    public boolean Verify_One_Driver(String Email){
        for (Driver_Service driver:dataBase.getDrivers()){
            if (driver.getDriver().getEmail().equalsIgnoreCase(Email)){
                driver.Verify();
                return true;
            }
        }
        return false;
    }


}
