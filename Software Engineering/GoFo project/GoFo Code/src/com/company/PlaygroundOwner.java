package com.company;
/**
 * A {@code PlaygroundOwner} class is an extension of the {@code AppData} class, adding the properties and methods of the owner
 */
public class PlaygroundOwner extends AppData
{
    /**
     * A constructor for the {@code PlaygroundOwner} object
     * @param u username of the player
     * @param e email of the player
     * @param p password of the player
     * @param fN first name of the player
     * @param lN last name of the player
     * @param pho phone of the player
     */
    public PlaygroundOwner(String u,String e,String p,String fN,String lN,String pho)
    {
        userName=u;
        email=e;
        password=p;
        firstName=fN;
        lastName=lN;
        phone=pho;
    }

    /**
     * Stores the information of the playground owners
     * @return the information of the playground owners
     */
    public String toString()
    {
        return " The User Name of Owner is " + this.getUserName() + ". The phone is "+this.getPhone()+". The first name of the Owner is "+this.getFirstName()+". The last name of the Owner is "+this.getLastName()+". The email of the Owner is "+this.getEmail();
    }

}