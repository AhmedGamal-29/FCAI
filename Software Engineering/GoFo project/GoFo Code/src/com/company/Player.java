package com.company;
import java.util.ArrayList;
/**
 * A {@code Player} class is an extension of the {@code AppData} class, adding properties and methods of the player
 */
public class Player extends AppData
{
    /**
     * Default Constructor for the {@code Player} object
     */
    public Player(){}
    /**
     * A constructor for the {@code Player} object
     * @param u username of the player
     * @param e email of the player
     * @param p password of the player
     * @param fN first name of the player
     * @param lN last name of the player
     * @param pho phone of the player
     */
    public Player(String u,String e,String p,String fN,String lN,String pho)
    {
        userName=u;
        email=e;
        password=p;
        firstName=fN;
        lastName=lN;
        phone=pho;
    }

    /**
     * Stores the information of the players
     * @return the information of the players
     */
    public String toString()
    {
        return "The name of player is " + this.getUserName() + ". The phone is "+this.getPhone()+". The first name of the player is "+this.getFirstName()+". The last name of the player is "+this.getLastName()+". The email of the player is "+this.getEmail();
    }
}