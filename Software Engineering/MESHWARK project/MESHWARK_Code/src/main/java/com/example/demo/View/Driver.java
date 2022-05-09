package com.example.demo.View;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.String;
public class Driver implements User {
    private String User_name;
    private String Mobile;
    private String Email;
    private String Password;
    private boolean isSuspended ;
    private  String drivingLicense;
    private  String nationalID;


    Driver(@JsonProperty("User Name") String user_name
            , @JsonProperty("Mobile") String mobile,
           @JsonProperty("Email")  String email,
           @JsonProperty("Password")String password,
           @JsonProperty("drivingLicense") String drivingLicense,
           @JsonProperty("nationalID")String nationalID){
        this.User_name=user_name;
        this.Mobile=mobile;
        this.Email=email;
        this.Password=password;
        this.nationalID=nationalID;
        this.drivingLicense=drivingLicense;
    }
    @Override
    public void setUser_name(String user_name) {
        this.User_name = user_name;
    }
    @Override
    public String getUser_name() {
        return User_name;
    }
    @Override
    public void setMobile(String mobile) {
        this.Mobile = mobile;
    }
    @Override
    public String getMobile() {
        return Mobile;
    }
    @Override
    public void setEmail(String email) {
        this.Email = email;
    }
    @Override
    public String getEmail() { return Email; }
    @Override
    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }
    @Override
    public void setPassword(String password) {
        this.Password = password;
    }
    @Override
    public String getPassword() {
        return Password;
    }

    @Override
    public boolean isSuspended() {
        return isSuspended;
    }

    public void setDrivingLicense(String drivingLicense) {this.drivingLicense = drivingLicense;}
    public void setNationalID(String nationalID) {this.nationalID = nationalID;}

    public String getDrivingLicense() { return drivingLicense; }
    public String getNationalID(){ return nationalID; }


}
