package com.example.demo.Controllers;

public class Discount {
    private double Discount_Percentage;
    private double OldPrice;
    private int Passenger_Number;
    private String Date;
    private boolean First_Trip;
    private boolean BirthDay;
    private boolean Admin_Discount;

    public Discount(){
        Discount_Percentage=0.0;
        OldPrice=0.0;
        Passenger_Number=0;
        Date="";
        First_Trip=false;
        BirthDay=false;
        Admin_Discount=false;
    }

    public void setOldPrice(double oldPrice) { OldPrice = oldPrice; }
    public void setPassenger_Number(int passenger_Number) { Passenger_Number = passenger_Number; }
    public boolean setDate(String date,String birthday) {
        Date = date;
        String[]Birthday=birthday.split("/");
        String[]Date_now=Date.split("-");
        this.BirthDay=Birthday[1].equals(Date_now[1]) && Birthday[2].equals(Date_now[2]);
        return this.BirthDay;
    }

    public String getDate() {return Date;}

    public void setFirst_Trip(boolean first_Trip) { First_Trip = first_Trip; }
    public void setAdmin_Discount(boolean admin_Discount) { Admin_Discount = admin_Discount; }

    public boolean isAdmin_Discount() { return Admin_Discount; }

    private String public_holiday(){
        return "2022/01/07\n" +
                "2022/01/28\n" +
                "2022/4/29\n" +
                "2022/5/1\n" +
                "2022/5/3\n" +
                "2022/5/12\n" +
                "2022/5/13\n" +
                "2022/5/14\n" +
                "2022/5/15\n" +
                "2022/7/01\n" +
                "2022/7/17\n"+
                "2022/7/18\n"+
                "2022/7/19\n"+
                "2022/7/20\n"+
                "2022/7/21\n"+
                "2022/7/22\n"+
                "2022/7/23\n"+
                "2022/7/24\n"+
                "2022/8/12\n"+
                "2022/10/7\n"+
                "2022/10/21\n";
    }
    public boolean check_Holiday(String Date){
        for (String da:public_holiday().split("\n")){
            if (Date.equalsIgnoreCase(da))return true;
        }
        return false;
    }

    public double Perform_Discount(){
        if (First_Trip) Discount_Percentage+=0.10;
        if (Admin_Discount) Discount_Percentage+=0.10;
        if (BirthDay) Discount_Percentage+=0.10;
        if (Passenger_Number>1 ) Discount_Percentage+=0.05;
        if (check_Holiday(Date.split(" ")[0]))Discount_Percentage+=0.05;
        return OldPrice-(OldPrice*Discount_Percentage);
    }
}
