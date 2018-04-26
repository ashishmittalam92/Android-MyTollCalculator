package com.mytollcalculator.mytollcalculator.model;

/**
 * Created by Ashish on 4/26/2018.
 */

public class TollBetweenPlaces {
    public static final String TABLE_NAME = "toll_between_place";

    public static final String COLUMN_TOLL_ID = "tollId";
    public static final String COLUMN_ROUTE_ID = "routeId";
    public static final String COLUMN_DISTANCE = "distance";
    public static final String COLUMN_SINGLE_COST = "singleCost";
    public static final String COLUMN_RETURN_COST = "returnCost";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_TOLL_ID + " INTEGER,"
            + COLUMN_ROUTE_ID + " INTEGER,"
            + COLUMN_DISTANCE + " REAL,"
            + COLUMN_SINGLE_COST + " INTEGER,"
            + COLUMN_RETURN_COST + " INTEGER,"
            + "FOREIGN KEY(" + COLUMN_TOLL_ID + ") REFERENCES " + Toll.TABLE_NAME + "(" + Toll.COLUMN_ID + ")"
            + "FOREIGN KEY(" + COLUMN_ROUTE_ID + ") REFERENCES " + Route.TABLE_NAME + "(" + Route.COLUMN_ID + ")"
            + ")";

    private int tollId;
    private int routeId;
    private double distance;
    private int singleCost;
    private int returnCost;

    public int getTollId() {
        return tollId;
    }

    public void setTollId(int tollId) {
        this.tollId = tollId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
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
}
