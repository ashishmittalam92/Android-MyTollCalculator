package com.mytollcalculator.mytollcalculator.model;

/**
 * Created by Ashish on 4/26/2018.
 */

public class TollDetail {
    private String tollName;
    private int singleCost;
    private int returnCost;
    private double distance;

    public String getTollName() {
        return tollName;
    }

    public void setTollName(String tollName) {
        this.tollName = tollName;
    }

    public int getSingleCost() {
        return singleCost;
    }

    public void setSingleCost(int singleCost) {
        this.singleCost = singleCost;
    }

    public int getReturnCost() {
        return returnCost;
    }

    public void setReturnCost(int returnCost) {
        this.returnCost = returnCost;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
