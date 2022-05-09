package com.example.demo.Controllers;

public class Wallet {
    private double Money;
    public Wallet(){Money=0;}
    public boolean setMoney(double money) {
        Money += money;
        return true;
    }
    public void Update_Money(double money){ Money-=money; }
    public boolean Check_Have_Money(double money){ return money<=Money; }
    public double getMoney() { return Money; }
}
