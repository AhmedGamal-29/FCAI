package com.example.demo.Model;
import com.example.demo.Controllers.*;
import com.example.demo.View.*;

import java.util.ArrayList;

public interface DataBase {
    boolean addClient(Customer customer);
    boolean addDriver(Driver driver);
    ArrayList<Customer_Service> getClients ();
    ArrayList<Driver_Service> getDrivers();
}
