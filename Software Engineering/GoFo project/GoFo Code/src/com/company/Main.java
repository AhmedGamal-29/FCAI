package com.company;
public class Main
{
    public static void main(String[] args)
    {
        /**
         * Create object to collect application data
         */
        AppData data=new AppData();

        /**
         * Adding players to Players list in the app data
         */
        Player p1 = new Player("Ahmed10","ahmed@gmail.com","123456","Ahmed","ahmed","0101010101");
        data.addPlayer(p1);

        Player p2=new Player("Aly","aly@gmail.com","987654","Aly","aly","010101");
        data.addPlayer(p2);

        System.out.println("-----Players info list-----");
        data.displayPlayers();
        System.out.println("-------------------------------------------");

        /**
         * Adding Owners to Owners list in app data
         */
        PlaygroundOwner pgOwner1=new PlaygroundOwner("Mohamed","mohamedplaygrounds@gmail.com","10101010","Mohamed","mohamed","012012012");
        data.addPlaygroundOwner(pgOwner1);

        PlaygroundOwner pgOwner2=new PlaygroundOwner("Ahmedd","ahmedplaygrounds@gmail.com","555555555","Ahmed","ahmed","013013013");
        data.addPlaygroundOwner(pgOwner2);

        System.out.println("-----Playground Owners info list-----");
        data.displayPlaygroundOwners();
        System.out.println("-------------------------------------------");

        /**
         * Adding Playgrounds to Playgrounds list in app data
         */
        Playground play1 =new Playground(pgOwner1,"Santiago","Shoubra","The field is natural grass",10,30);
        data.addPlayground(play1);

        Playground play2 =new Playground(pgOwner2,"Camp Nou","Shoubra","The field is artificial grass",12,50);
        data.addPlayground(play2);

        System.out.println("-----Playgrounds info list-----");
        data.displayPlaygrounds();
        System.out.println("-------------------------------------------");

        /**
         * Book Playgrounds
         */
        Booking b1=new Booking(10,7,22,23);
        b1.book(p1.userName,pgOwner1.userName,play1.pName);
        data.addBooking(b1);

        Booking b2=new Booking(15,6,10,12);
        b2.book(p2.userName,pgOwner2.userName, play2.pName);
        data.addBooking(b2);

        System.out.println("-----Booking list-----");
        data.displayBookings();
        System.out.println("-------------------------------------------");

    }
}