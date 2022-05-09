package com.company;
import java.util.ArrayList;
/**
 * A {@code AppData} class is a class used to make generalizations for {@code Player} class, {@code PlaygroundOwner} class, {@code Playground} class and {@code Booking} class in the application.
 */
public class AppData
{
    protected String userName,email,password,firstName,lastName,phone;

    /**
     * A list of players to store the players in
     */
    ArrayList<Player> players = new ArrayList <Player>();

    /**
     * A list of playground owners to store the playground owners in
     */
    ArrayList<PlaygroundOwner> playgroundOwners=new ArrayList<PlaygroundOwner>();

    /**
     * A list of playgrounds to store the playgrounds in
     */
    ArrayList<Playground> playgrounds=new ArrayList<Playground>();

    /**
     * A list of bookings to store the bookings in
     */
    ArrayList<Booking> bookings = new ArrayList<Booking>();


    /**
     * Default Constructor for the {@code AppData} object
     */
    public AppData(){};

    /**
     * A constructor for the {@code AppData} object
     * @param u
     * @param e
     * @param p
     * @param fN
     * @param lN
     * @param pho
     */
    public AppData(String u, String e, String p, String fN, String lN, String pho)
    {
        userName=u;
        email=e;
        password=p;
        firstName=fN;
        lastName=lN;
        phone=pho;
    }

    /**
     * Adds a new player to the players list
     * @param newPlayer the playground to be added
     */
    public void addPlayer (Player newPlayer)
    {
        players.add(newPlayer);
    }

    /**
     * Adds a new playground owner to the playground owner's list
     * @param newOwner the playground owner to be added
     */
    public void addPlaygroundOwner (PlaygroundOwner newOwner)
    {
        playgroundOwners.add(newOwner);
    }

    /**
     * Adds a new playground to the playground owner's list
     * @param newPlayground the playground to be added
     */
    public void addPlayground (Playground newPlayground)
    {
        playgrounds.add(newPlayground);
    }

    /**
     * Adds a new booking to the bookings list
     * @param newBooking the playground to be added
     */
    public void addBooking (Booking newBooking)
    {
        bookings.add(newBooking);
    }

    /**
     * Displays the added bookings to the list
     */
    public void displayBookings()
    {
        for(int i =0;i<bookings.size();i++)
        {
            System.out.println(bookings.get(i).toString());

        }
    }

    /**
     * Displays the added players to the list
     */
    public void displayPlayers()
    {
        for(int i =0;i<players.size();i++)
        {
            System.out.println(players.get(i).toString());
        }
    }

    /**
     * Displays the added playground owners to the list
     */
    public void displayPlaygroundOwners()
    {
        for(int i =0;i<playgroundOwners.size();i++)
        {
            System.out.println(playgroundOwners.get(i).toString());
        }
    }

    /**
     * Displays the added playgrounds to the list
     */
    public void displayPlaygrounds()
    {
        for(int i =0;i<playgrounds.size();i++)
        {
            System.out.println(playgrounds.get(i).toString());
        }
    }


    /**
     * Sets the username of the user
     * @param user the new username
     */
    public void setUserName(String user)
    {
        userName=user;
    }

    /**
     * Gets the username of the user
     * @return the new username
     */
    public String getUserName()
    {
        return userName;
    }

    /**
     * Sets the E-mail of the user
     * @param mail the new e-mail
     */
    public void setEmail(String mail)
    {
        email=mail;
    }

    /**
     * Gets the E-mail of the user
     * @return the new E-mail
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Sets the password of the user
     * @param pass the new password
     */
    public void setPassword(String pass)
    {
        password=pass;
    }

    /**
     * Gets the password of the user
     * @return the new password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Sets the first name of the user
     * @param fName the new first name
     */
    public void setFirstName(String fName)
    {
        firstName = fName;
    }

    /**
     * Gets the first name of the user
     * @return the new first name
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Sets the last name of the user
     * @param lName the new last name
     */
    public void setLastName(String lName)
    {
        lastName=lName;
    }

    /**
     * Gets the last name of the user
     * @return the new last name
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Sets the phone number of the user
     * @param ph the new phone number
     */
    public void setPhone(String ph)
    {
        phone=ph;
    }

    /**
     * Gets the phone number of the user
     * @return the new phone number
     */
    public String getPhone()
    {
        return phone;
    }

}