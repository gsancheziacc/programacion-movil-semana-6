package com.example.gabriel_sanchez_s6.db;

public class TicketModel {
    private int id;
    private String carNumber;
    private String inDatetime;
    private String placeNumber;

    public TicketModel(int id, String carNumber, String inDatetime, String placeNumber) {
        this.id = id;
        this.carNumber = carNumber;
        this.inDatetime = inDatetime;
        this.placeNumber = placeNumber;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getInDatetime() {
        return inDatetime;
    }

    public void setInDatetime(String inDatetime) {
        this.inDatetime = inDatetime;
    }

    public String getPlaceNumber() {
        return this.placeNumber;
    }

    public void setPlaceNumber(String placeNumber) {
        this.placeNumber = placeNumber;
    }
}
