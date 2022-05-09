package com.example.demo.View;
import com.example.demo.Model.DataBase;

public class Admin {
    private final String username;
    private final String password;
    private static Admin Unique_Instance;

    private Admin(){
        this.username = "Admin";
        this.password = "Admin";
    }
    public static Admin getIns(){
        if (Unique_Instance==null)Unique_Instance=new Admin();
        return Unique_Instance;
    }

    public String getPassword() {return password;}
    public String getUsername() {return username;}
}
