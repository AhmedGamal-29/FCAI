package com.example.demo.Model;
import com.example.demo.Controllers.*;
import com.example.demo.View.Admin;
import com.example.demo.View.Customer;
import com.example.demo.View.Driver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class Array_DataBase implements DataBase{
    private final ArrayList<Customer_Service> Customers = new ArrayList<>();
    private final ArrayList<Driver_Service> drivers = new ArrayList<>();
    public Array_DataBase(){}
    @Override
    public ArrayList<Customer_Service> getClients (){ return Customers; }
    @Override
    public ArrayList<Driver_Service> getDrivers(){ return drivers; }
    @Override
    public boolean addClient(Customer client) {
        Customer_Service customer_service=new Customer_Service();
        customer_service.setCustomer(client);
        return Customers.add(customer_service);
    }
    @Override
    public boolean addDriver(Driver driver) {
        Driver_Service driver_Service=new Driver_Service();
        driver_Service.setDriver(driver);
        return drivers.add(driver_Service);
    }

    public Driver_Service getDriver_byEmail(String Email){
        for (Driver_Service driver:drivers){
            if (Email.equalsIgnoreCase(driver.getDriver().getEmail())){
                return driver;
            }
        }
        return null;
    }
    public Customer_Service getCustomer_byEmail(String Email){
        for (Customer_Service customer:Customers){
            if (Email.equalsIgnoreCase(customer.getCustomer().getEmail())){
                return customer;
            }
        }
        return null;
    }

    public Customer_Service Login_Customer(String Email,String Password){
        for (Customer_Service customer:Customers){
            if (customer.getCustomer().getEmail().equals(Email) &&customer.getCustomer().getPassword().equals(Password)){
                return customer;
            }
        }
        return null;
    }
    public Driver_Service Login_Driver(String Email,String Password){
        for (Driver_Service driver:drivers){
            if (driver.getDriver().getEmail().equals(Email) &&driver.getDriver().getPassword().equals(Password)){
                return driver;
            }
        }
        return null;
    }
    public boolean Login_Admin(String Email,String Password){
        return Email.equalsIgnoreCase(Admin.getIns().getUsername())&&Password.equalsIgnoreCase(Admin.getIns().getPassword());
    }
    public ArrayList<Driver_Service> Request_ride(Customer_Service Customer){
        ArrayList<Driver_Service>Drivers_Available=new ArrayList<>();
        for (Driver_Service driver:drivers){
            for (Trip fav_Trip :driver.getTrips()){
                if (Customer.getTrip().Source_Area.equalsIgnoreCase(driver.getSource_Area()) && Customer.getTrip().Destination.equalsIgnoreCase(fav_Trip.Destination)){
                    if (!driver.getDriver().isSuspended() && Customer.Check_Have_Money(fav_Trip.getPrice())){
                        driver.setTripIndex(driver.getTrips().indexOf(fav_Trip));
                        Drivers_Available.add(driver);
                    }
                }
            }
        }
        return Drivers_Available;
    }
}
