package com.mytollcalculator.mytollcalculator.model;

/**
 * Created by Ashish on 4/26/2018.
 */

public class Route {
    public static final String TABLE_NAME = "route";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SOURCE_ID = "sourceId";
    public static final String COLUMN_DESTINATION_ID = "destinationId";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_SOURCE_ID + " INTEGER,"
            + COLUMN_DESTINATION_ID + " INTEGER,"
            + "FOREIGN KEY(" + COLUMN_SOURCE_ID + ") REFERENCES " + Place.TABLE_NAME + "(" + Place.COLUMN_ID + ")"
            + "FOREIGN KEY(" + COLUMN_DESTINATION_ID + ") REFERENCES " + Place.TABLE_NAME + "(" + Place.COLUMN_ID + ")"
            + ")";

    private int id;
    private int sourceId;
    private int destinationId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }
}
