package com.example.demo.View;

public class Test {
    private String name;
    private String lastname;
    private int id;
    public Test(){}
    public Test(String name, String lastname, int id){
        this.name=name;
        this.lastname=lastname;
        this.id=id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getName() {
        return name;
    }
}

