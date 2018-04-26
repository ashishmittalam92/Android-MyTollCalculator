package com.mytollcalculator.mytollcalculator.model;

/**
 * Created by Ashish on 4/26/2018.
 */

public class Toll {

    public static final String TABLE_NAME = "toll";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                                                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                                                + COLUMN_NAME + " TEXT"
                                                + ")";

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
