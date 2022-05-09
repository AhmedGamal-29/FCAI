package com.example.demo.web_Main;

import com.example.demo.Model.Array_DataBase;
import com.example.demo.Controllers.Admin_Service;
import com.example.demo.Controllers.Customer_Service;
import com.example.demo.Controllers.Driver_Service;
import com.example.demo.Controllers.Trip;
import com.example.demo.View.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/Admin")
public class Admin_Controller {
    final
    Array_DataBase dataBase;

    public Admin_Controller(Array_DataBase dataBase) {
        this.dataBase = dataBase;
        Admin_Service.getIns().setDataBase(dataBase);
    }
    @PostMapping("/Login")
    public boolean Login(@JsonProperty("Email")String Email,@JsonProperty("Password")String Password){
        Admin_Service.getIns().setAdmin();
        return dataBase.Login_Admin(Email,Password);
    }
    @GetMapping("/Display_Clients")
    public ArrayList<Customer_Service> Display_Clients(){return Admin_Service.getIns().getDataBase().getClients();}
    @GetMapping("/Display_Drivers")
    public ArrayList<Driver_Service> Display_Drivers(){return Admin_Service.getIns().getDataBase().getDrivers();}
    @GetMapping("/Verify_All_Drivers")
    public boolean Verify_All_Drivers(){return Admin_Service.getIns().Verify_ALL_Drivers();}
    @PostMapping("/Verify_One_Driver")
    public boolean Verify_One_Driver(@JsonProperty("Email")String Email){return Admin_Service.getIns().Verify_One_Driver(Email);}
    @PostMapping("/Suspend")
    public boolean Suspend_User(@JsonProperty("Email") String Email){return Admin_Service.getIns().suspend(Email,true);}
    @PostMapping("/UnSuspend")
    public boolean UnSuspend_User(@JsonProperty("Email") String Email){return Admin_Service.getIns().suspend(Email,false);}
    @GetMapping("/Display_Trips_details")
    public ArrayList<Trip> Display_Trips_details(){return Admin_Service.getIns().getAll_Trips();}
    @PostMapping("/Add_Discount_on_specific_Area")
    public boolean Add_Discount_On_Specific_Area(@JsonProperty("Area") String Area){return Admin_Service.getIns().setDiscount_Area(Area);}
    @GetMapping("/Return_All_Discount_Area")
    public ArrayList<String> Return_All_Discount_Area(){return Admin_Service.getIns().getDiscount_Area();}
    @DeleteMapping("/Remove_Discount_On_Specific_Area")
    public boolean Remove_Discount_On_Specific_Area(@JsonProperty("Area") String Area){return Admin_Service.getIns().Delete_Discount_Area(Area);}
    @GetMapping("/Logout")
    public boolean Logout(){return true;}
}
