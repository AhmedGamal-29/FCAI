package com.example.demo.Controllers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Create_Trip {
    public String DestinationArea;
    public double Price;
    Create_Trip(@JsonProperty("DestinationArea") String DestinationArea, @JsonProperty("Price") double Price){
        this.DestinationArea=DestinationArea;
        this.Price=Price;
    }

    public void setDestinationArea(String destinationArea) {DestinationArea = destinationArea;}
    public void setPrice(double price) {Price = price;}

    public String getDestinationArea() {return DestinationArea;}
    public double getPrice() {return Price;}
}
