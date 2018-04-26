package com.mytollcalculator.mytollcalculator.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import com.mytollcalculator.mytollcalculator.model.Place;
import com.mytollcalculator.mytollcalculator.model.Route;
import com.mytollcalculator.mytollcalculator.model.Toll;
import com.mytollcalculator.mytollcalculator.model.TollBetweenPlaces;
import com.mytollcalculator.mytollcalculator.model.TollDetail;
import com.mytollcalculator.mytollcalculator.util.AssetReader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashish on 4/26/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "toll_db";

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.context = context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Toll.CREATE_TABLE);
        db.execSQL(Place.CREATE_TABLE);
        db.execSQL(Route.CREATE_TABLE);
        db.execSQL(TollBetweenPlaces.CREATE_TABLE);

        // Adding places from json file to database
        List<Place> places = AssetReader.getPlaces(context);
        for(Place place: places) {
            insertPlace(db, place);
        }

        // Adding tolls from json file to database
        List<Toll> tolls = AssetReader.getTolls(context);
        for(Toll toll: tolls) {
            insertToll(db, toll);
        }

        // Adding routes from json file to database
        List<Route> routes = AssetReader.getRoutes(context);
        for(Route route: routes) {
            insertRoute(db, route);
        }

        // Adding tollBetweenPlaces from json file to database
        List<TollBetweenPlaces> tollBetweenPlacesList = AssetReader.getTollBetweenPlaces(context);
        for(TollBetweenPlaces tollBetweenPlaces: tollBetweenPlacesList) {
            insertTollBetweenPlaces(db, tollBetweenPlaces);
        }
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Toll.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Place.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Route.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TollBetweenPlaces.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public void insertPlace(SQLiteDatabase db, Place place) {
        ContentValues values = new ContentValues();
        values.put(Place.COLUMN_ID, place.getId());
        values.put(Place.COLUMN_NAME, place.getName());
        db.insert(Place.TABLE_NAME, null, values);
    }

    public void insertToll(SQLiteDatabase db, Toll toll) {
        ContentValues values = new ContentValues();
        values.put(Toll.COLUMN_ID, toll.getId());
        values.put(Toll.COLUMN_NAME, toll.getName());
        db.insert(Toll.TABLE_NAME, null, values);
    }

    public void insertRoute(SQLiteDatabase db, Route route) {
        ContentValues values = new ContentValues();
        values.put(Route.COLUMN_ID, route.getId());
        values.put(Route.COLUMN_SOURCE_ID, route.getSourceId());
        values.put(Route.COLUMN_DESTINATION_ID, route.getDestinationId());
        db.insert(Route.TABLE_NAME, null, values);
    }

    public void insertTollBetweenPlaces(SQLiteDatabase db, TollBetweenPlaces tollBetweenPlaces) {
        ContentValues values = new ContentValues();
        values.put(TollBetweenPlaces.COLUMN_TOLL_ID, tollBetweenPlaces.getTollId());
        values.put(TollBetweenPlaces.COLUMN_ROUTE_ID, tollBetweenPlaces.getRouteId());
        values.put(TollBetweenPlaces.COLUMN_DISTANCE, tollBetweenPlaces.getDistance());
        values.put(TollBetweenPlaces.COLUMN_SINGLE_COST, tollBetweenPlaces.getSingleCost());
        values.put(TollBetweenPlaces.COLUMN_RETURN_COST, tollBetweenPlaces.getReturnCost());
        db.insert(TollBetweenPlaces.TABLE_NAME, null, values);
    }

    public List<TollDetail> getTollDetails(String source, String destination) {
        List<TollDetail> tollDetails = new ArrayList<>();

        String selectQuery = "SELECT t.name, tbp.distance, tbp.singleCost, tbp.returnCost"
                + " FROM " + TollBetweenPlaces.TABLE_NAME + " tbp"
                + " INNER JOIN " + Toll.TABLE_NAME + " t ON tbp.tollId = t.id"
                + " WHERE tbp.routeId = ("
                    + " SELECT r.id FROM " + Route.TABLE_NAME + " r"
                    + " WHERE r.sourceId = ("
                        + " SELECT s.id FROM " + Place.TABLE_NAME + " s"
                        + " WHERE lower(s.name) = '" + source.toLowerCase() + "'"
                        + ") and ("
                        + " SELECT d.id FROM " + Place.TABLE_NAME + " d"
                        + " WHERE lower(d.name) = '" + destination.toLowerCase() + "'"
                    + ")"
                + ")";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TollDetail tollDetail = new TollDetail();
                tollDetail.setTollName(cursor.getString(cursor.getColumnIndex(Toll.COLUMN_NAME)));
                tollDetail.setDistance(cursor.getDouble(cursor.getColumnIndex(TollBetweenPlaces.COLUMN_DISTANCE)));
                tollDetail.setSingleCost(cursor.getInt(cursor.getColumnIndex(TollBetweenPlaces.COLUMN_SINGLE_COST)));
                tollDetail.setReturnCost(cursor.getInt(cursor.getColumnIndex(TollBetweenPlaces.COLUMN_RETURN_COST)));

                tollDetails.add(tollDetail);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        return tollDetails;
    }
}
