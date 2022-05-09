package com.company;
/**
 * A {@code Booking} class is an extension of the {@code AppData} class, adding methods of making the Bookings
 */
public class Booking
{
    protected int day,month,start,end;
    protected boolean booked;
    protected String bookedTo,bookedFor,bookedGround;


    /**
     * Default Constructor for the {@code Booking} object
     */
    public Booking()
    {
        this.day = 1;
        this.month = 1;
        this.start = 0;
        this.end = 0;
        booked = false;
    }

    /**
     * Constructor for the {@code Booking} object
     * @param day
     * @param month
     * @param start
     * @param end
     */
    Booking(int day,int month, int start, int end)
    {
        this.day=day;
        this.month=month;
        this.start=start;
        this.end=end;

        booked =false;
    }

    /**
     * Sets the day of the booking
     * @param day
     * @return the day is valid or not
     */
    public boolean setDay(int day)
    {
        if (day<1 || day>31) return false;
        this.day=day;
        return true;
    }

    /**
     * Gets the day of the booking
     * @return the valid day of the booking
     */
    public int getDay()
    {
        return day;
    }

    /**
     * Sets the month of the booking
     * @param month
     * @return the month is valid or not
     */
    public boolean setMonth(int month)
    {
        if (month<1 || month>12) return false;
        this.month=month;
        return true;
    }

    /**
     * Gets the month of the booking
     * @return the valid month of the booking
     */
    public int getMonth()
    {
        return month;
    }

    /**
     * Sets the starting hour of the booking
     * @param start
     * @return the starting hour is valid or not
     */
    public boolean setStart(int start)
    {
        if (start<0 || start>24) return false;
        this.start=start;
        return true;
    }

    /**
     * Gets the starting hour of the booking
     * @return the valid starting hour of the booking
     */
    public int getStart()
    {
        return start;
    }

    /**
     * Sets the ending hour of the booking
     * @param end
     * @return the ending hour is valid or not
     */
    public boolean setEnd(int end)
    {
        if (end<0 || day>24) return false;
        this.end=end;
        return true;
    }

    /**
     * Gets the ending hour of the booking
     * @return the valid ending hour of the booking
     */
    public int getEnd()
    {
        return end;
    }

    /**
     * Books the selected free time to a specific player and stores his username
     * @param playerName
     */
    public void book(String playerName,String pgOwnerName,String playName)
    {
        booked =true;
        bookedTo=playerName;
        bookedFor=pgOwnerName;
        bookedGround=playName;
    }

    /**
     * Checks whether the selected time is booked or not
     * @return booked or not
     */
    public boolean ifBooked()
    {
        return booked;
    }


    /**
     * Stores the information of the bookings
     * @return the information of the bookings
     */
    public String toString()
    {
        String bookedIn ="Details: "+ "Day: "+ day + "/" + month + " Starting at: "+start+" Ending at: "+ end ;
        if (ifBooked())
        {
            bookedIn+= " Booked To: "+bookedTo +". Playground Name: "+bookedGround+ ". Playground Owner: "+bookedFor;

        }
        else
        {
            bookedIn += "NOT BOOKED YET!";
        }
        return bookedIn;
    }
}