package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book_Trip {
    private String Source;
    private String Destination;
    private int Passenger_Number;

    public Book_Trip(@JsonProperty("Source") String Source,
                     @JsonProperty("Destination") String Destination,
                     @JsonProperty("Passenger_Number") int Passenger_Number){
        this.Source=Source;
        this.Destination=Destination;
        this.Passenger_Number=Passenger_Number;
    }

    public void setSource(String source) {Source = source;}
    public void setDestination(String destination) {Destination = destination;}
    public void setPassenger_Number(int passenger_Number) {Passenger_Number = passenger_Number;}

    public String getSource() {return Source;}
    public String getDestination() {return Destination;}
    public int getPassenger_Number() {return Passenger_Number;}
}
