package com.example.demo.web_Main;
import com.example.demo.Model.*;
import com.example.demo.Controllers.*;
import com.example.demo.View.Driver;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/Driver")
public class Driver_Controller {
    final
    Array_DataBase dataBase;
//    final
//    Admin_Service admin;
    Driver_Service driver =new Driver_Service();
    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    public Driver_Controller(Array_DataBase dataBase) {
        this.dataBase = dataBase;
    }
    @PostMapping("/Add")
    public boolean addDriver(Driver user) {return dataBase.addDriver(user);}
    @PostMapping("/Login")
    public boolean Login(@JsonProperty("Email")String Email,@JsonProperty("Password")String Password){
        if (dataBase.Login_Driver(Email,Password)!=null){
            driver =dataBase.Login_Driver(Email,Password);
            return true;
        }
        return false;
    }
    @GetMapping("/Show_User_Rate")
    public double Show_User_Rate(){return driver.getRate();}
    @PutMapping ("Add_Source_Area")
    public boolean Add_SourceArea(@JsonProperty("Source_Area")String Source_Area){return driver.setSource_Area(Source_Area);}
    @PutMapping ("Add_Trips")
    public boolean Add_Trips(Create_Trip createTrip){
        if (createTrip.DestinationArea!=null){
            Trip trip1 =new Trip();
            trip1.Driver=driver.getDriver();
            trip1.Destination= createTrip.getDestinationArea();
            trip1.setPrice(createTrip.Price);
            trip1.price_Time=formatter.format(LocalDateTime.now());
            for (String discount:Admin_Service.getIns().getDiscount_Area()){
                Discount discount1=new Discount();
                discount1.setAdmin_Discount(true);
                trip1.setDiscount(discount1);
            }
            driver.set_Trip(trip1);
        }
        return true;
    }
    @GetMapping("GetAll_User_request")
    public String GetAll_User_request(){
        String details="";
        if (driver.getAll_Request().size()==0){return "No Request";}
        else if (driver.getAll_Request().size()!=0){
            for (int i=0;i<driver.getAll_Request().size();i++){
                details+=i+1+" : User Email :"+driver.getAll_Request().get(i).getCustomer().getEmail()
                        +"\tMobile :"+driver.getAll_Request().get(i).getCustomer().getMobile()
                        +"\tFrom "+driver.getSource_Area()+" To "+driver.getTrips().get(driver.getTrip_Index()).Destination+"\n";
            }
        }
        return details;
    }
    @PutMapping("/Accept_User_request")
    public boolean Accept_User_request(@JsonProperty("Email")String Email){
        for (Customer_Service client:driver.getAll_Request()){
            Trip trip=new Trip();
            if (Email.equals(client.getCustomer().getEmail())){
                client.setTrip_Number(client.getTrip_Number()+1);
                for (Trip trip_driver:driver.getTrips()){
                    if (client.getTrip().Destination.equalsIgnoreCase(trip_driver.Destination)){
                        trip =trip_driver;
                        client.getTrip().Driver=driver.getDriver();
                        client.getTrip().price_Time=trip_driver.price_Time;
                        client.getTrip().Driver_arrived_user_Location=trip.Driver_arrived_user_Location=formatter.format(LocalDateTime.now());
                        break;
                    }
                }
                Admin_Service.getIns().Perform_Discount(trip,client);
                client.getTrip().setPrice(trip.getPrice());
                driver.setMoney(trip.getDriver_Price());
                client.Update_Money(trip.getPrice());
                Admin_Service.getIns().suspend(driver.getDriver().getEmail(),true);
                driver.getAll_Request().remove(client);
                Admin_Service.getIns().Delete_Discount(trip);
                return true;
            }
        }
        return false;
    }
    @GetMapping("Logout")
    public boolean Logout(){  driver=new Driver_Service();   return true;}
}
