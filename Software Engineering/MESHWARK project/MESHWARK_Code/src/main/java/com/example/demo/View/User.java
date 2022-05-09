package com.example.demo.View;

import org.springframework.lang.NonNull;

public interface User {
    void setUser_name(String user_name);
    void setMobile(String mobile);
    void setEmail(@NonNull  String email);
    void setSuspended(boolean suspended);
    void setPassword(String password);
    String getUser_name();
    String getMobile();
    String getEmail();
    String getPassword();
    boolean isSuspended();
}