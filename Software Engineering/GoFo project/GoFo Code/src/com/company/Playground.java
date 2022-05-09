package com.company;
public class Playground
{
    protected String pName, pLocation, pDescription;
    protected PlaygroundOwner pOwner;
    protected double pPrice;
    protected int numOfPlayers;


    /**
     * Default Constructor for the {@code Playground} object
     */
    public Playground(){}
    /**
     * * A constructor for the {@code Playground} object
     * @param owner
     * @param name
     * @param loc
     * @param des
     * @param players
     * @param price
     */
    public Playground(PlaygroundOwner owner, String name,String loc, String des,int players, int price)
    {
        pOwner=owner;
        pName=name;
        pLocation=loc;
        pDescription=des;
        numOfPlayers=players;
        pPrice=price;
    }

    /**
     * Sets the name of the playground
     * @param pName
     */
    public void setpName(String pName)
    {
        this.pName = pName;
    }

    /**
     * Gets the name of the playground
     * @return the name of the playground
     */
    public String getpName()
    {
        return pName;
    }

    /**
     * Sets the location of the playground
     * @param loc
     */
    public void setpLocation(String loc)
    {
        pLocation =loc;
    }

    /**
     * Gets the location of the playground
     * @return the location of the playground
     */
    public String getpLocation()
    {
        return pLocation;
    }

    /**
     * Sets the description of the playground
     * @param des
     */
    public void setpDescription(String des)
    {
        pDescription =des;
    }

    /**
     * Gets the description of the playground
     * @return the description of the playground
     */
    public String getpDescription()
    {
        return pDescription;
    }

    /**
     * Sets the playground owner of the playground
     * @param pOwner
     */
    public void setpOwner(PlaygroundOwner pOwner)
    {
        this.pOwner = pOwner;
    }

    /**
     * Gets the playground owner of the playground
     * @return the play ground owner of the playground
     */
    public PlaygroundOwner getpOwner()
    {
        return pOwner;
    }

    /**
     * Sets the price of the playground per hour
     * @param pr
     */
    public void setpPrice(double pr)
    {
        pPrice =pr;
    }

    /**
     * Gets the price of the playground
     * @return the price of the playground
     */
    public double getpPrice()
    {
        return pPrice;
    }

    /**
     * Sets the maximum number of the players in the playground
     * @param num
     */
    public void setNumOfPlayers(int num)
    {
        this.numOfPlayers=num;
    }

    /**
     * Gets the maximum number of the players in the playground
     * @return
     */
    public int getNumOfPlayers()
    {
        return numOfPlayers;
    }

    /**
     * Stores the information of the playground
     * @return the information of the playground
     */
    public String toString()
    {
        return "The Name of playground is " + this.getpName() + ". The Location is "+this.getpLocation()+". The description of the playground is "+this.getpDescription()+". The number of player is "+this.getNumOfPlayers()+". The Price per hour is "+this.getpPrice()+". "+this.getpOwner();
    }
}