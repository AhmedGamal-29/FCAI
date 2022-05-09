package com.example.demo.View;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.String;


public class Customer implements User{
    private String User_name;
    private String Mobile;
    private String Email;
    private String Password;
    private String Birthday;
    private boolean isSuspended ;
    public Customer(){}
    public Customer(@JsonProperty("UserName") String user_name
            , @JsonProperty("Mobile") String mobile,
                    @JsonProperty("Email")  String email,
                    @JsonProperty("Password")String password,
                    @JsonProperty("Birthday")String birthday) {
        this.User_name=user_name;
        this.Mobile=mobile;
        this.Email=email;
        this.Password=password;
        this.Birthday=birthday;
        isSuspended= false;
    }
    @Override
    public String getUser_name() {
        return User_name;
    }
    @Override
    public void setUser_name(String user_name) {
        this.User_name = user_name;
    }
    @Override
    public String getMobile() {
        return Mobile;
    }
    @Override
    public void setMobile(String mobile) {
        this.Mobile = mobile;
    }
    @Override
    public String getEmail() { return Email; }
    @Override
    public void setEmail(String email) {
        this.Email = email;
    }
    public void setBirthday(String Birthday) { this.Birthday=Birthday; }
    public String getBirthday() { return Birthday; }
    @Override
    public boolean isSuspended() {
        return isSuspended;
    }
    @Override
    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }
    @Override
    public String getPassword() {
        return Password;
    }
    @Override
    public void setPassword(String password) {
        this.Password = password;
    }
}


