package com.example.demo.web_Main;
import com.example.demo.Model.Array_DataBase;
import com.example.demo.Controllers.*;
import com.example.demo.Model.Book_Trip;
import com.example.demo.View.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/Customer")
public class Customer_Controller {
    final
    Array_DataBase dataBase;
    public Customer_Controller(Array_DataBase dataBase) {
        this.dataBase = dataBase;
    }
    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    Customer_Service customer =new Customer_Service();

    @PostMapping("/Add")
    public boolean addUser(Customer user) {return dataBase.addClient(user);}

    @PostMapping("/Login")
    public boolean Login(@JsonProperty("Email")String Email,@JsonProperty("Password")String Password){
        if (dataBase.Login_Customer(Email,Password)!=null){
            customer =dataBase.Login_Customer(Email,Password);
            return true;
        }
        return false;
    }

    @PutMapping("/Add_MoneyTo_Wallet")
    public boolean Add_MoneyTo_Wallet(@JsonProperty("Money")double Money){
        return customer.setMoney(Money);
    }
    @GetMapping("Knowing_my_money_in_the_wallet")
    public double Knowing_my_money_in_the_wallet(){return customer.getMoney();}

    @PutMapping("/Request_ride")
    public boolean Request_ride(Book_Trip trip){
        Trip trip1=new Trip();
        trip1.Source_Area=trip.getSource();
        trip1.Destination=trip.getDestination();
        trip1.Customer=customer.getCustomer();
        trip1.setPassenger_Number(trip.getPassenger_Number());
        return customer.setTrip(trip1);
    }

    @GetMapping("/Show_Offer")
    public String Show_Offer(){
        String details="";
        customer.setDrivers_Available(dataBase.Request_ride(customer));
        for (Driver_Service driver_service : customer.getAll_Drivers()) {
            Trip Driver = driver_service.getTrips().get(driver_service.getTrip_Index());//Driver Trip
            double Price=Admin_Service.getIns().return_New_Price(Driver,customer);
            details += "Driver Name :" + driver_service.getDriver().getEmail() + "\tRate :" + driver_service.getRate()
                    + "\tPrice:" + Price+"\n";
        }
        if (customer.getAll_Drivers().isEmpty())return "NULL";
        else  return details;
    }
    @PutMapping("/Choose_Best_Trip")
    public boolean Choose_Best_Trip(@JsonProperty("Email")String Email){
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        for (Driver_Service driver:customer.getAll_Drivers()){
            if (Email.equals(driver.getDriver().getEmail())){
                int index=customer.getAll_Drivers().indexOf(driver);
                customer.setDriver_Ride(customer.getAll_Drivers().get(index));//wait
                customer.getAll_Drivers().get(index).newRequest(customer);
                customer.getTrip().User_Accept_Time=formatter.format(LocalDateTime.now());
                //customer.getDriver_Ride().getTrips().get(index).User_Accept_Time=formatter.format(LocalDateTime.now());
                customer.setEnter(true);
                return true;
            }
        }
        return false;
    }
    @PutMapping("/AddDriver_rate/{rate}")
    public boolean AddDriver_rate(@PathVariable double rate){
        if (customer.getEnter() && customer.getDriver_Ride().getDriver().isSuspended()){
            customer.getTrip().Driver_arrived_user_Destination=formatter.format(LocalDateTime.now());
            Admin_Service.getIns().Add_Trip(customer.getTrip());
            customer.rateDriver(customer.getDriver_Ride(),rate);
            Admin_Service.getIns().suspend(customer.getDriver_Ride().getDriver().getEmail(),false);
            customer.setEnter(false);
            customer.setDriver_Ride(new Driver_Service());
            return true;
        }
        return false;
    }

    @GetMapping("Logout")
    public boolean Logout(){  customer=new Customer_Service();   return true;}

}
