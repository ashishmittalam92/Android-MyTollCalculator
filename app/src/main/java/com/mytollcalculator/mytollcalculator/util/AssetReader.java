package com.mytollcalculator.mytollcalculator.util;

import android.content.Context;

import com.mytollcalculator.mytollcalculator.model.Place;
import com.mytollcalculator.mytollcalculator.model.Route;
import com.mytollcalculator.mytollcalculator.model.Toll;
import com.mytollcalculator.mytollcalculator.model.TollBetweenPlaces;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashish on 4/26/2018.
 */

public class AssetReader {

    public static List<Place> getPlaces(Context context) {
        List<Place> places = new ArrayList<>();
        String json = null;

        try {
            InputStream is = context.getAssets().open("place.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray placeArray = obj.getJSONArray("places");

            for (int i = 0; i < placeArray.length(); i++) {
                JSONObject placeObject = placeArray.getJSONObject(i);
                Place place = new Place();
                place.setId((int) placeObject.getInt("id"));
                place.setName(placeObject.getString("name"));

                places.add(place);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return places;
    }

    public static List<Toll> getTolls(Context context) {
        List<Toll> tolls = new ArrayList<>();
        String json = null;

        try {
            InputStream is = context.getAssets().open("toll.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray tollArray = obj.getJSONArray("tolls");

            for (int i = 0; i < tollArray.length(); i++) {
                JSONObject placeObject = tollArray.getJSONObject(i);
                Toll toll = new Toll();
                toll.setId((int) placeObject.getInt("id"));
                toll.setName(placeObject.getString("name"));

                tolls.add(toll);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tolls;
    }

    public static List<Route> getRoutes(Context context) {
        List<Route> routes = new ArrayList<>();
        String json = null;

        try {
            InputStream is = context.getAssets().open("route.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray routeArray = obj.getJSONArray("routes");

            for (int i = 0; i < routeArray.length(); i++) {
                JSONObject routeObject = routeArray.getJSONObject(i);
                Route route = new Route();
                route.setId(routeObject.getInt("id"));
                route.setSourceId(routeObject.getInt("sourceId"));
                route.setDestinationId(routeObject.getInt("destinationId"));

                routes.add(route);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return routes;
    }

    public static List<TollBetweenPlaces> getTollBetweenPlaces(Context context) {
        List<TollBetweenPlaces> tollBetweenPlacesList = new ArrayList<>();
        String json = null;

        try {
            InputStream is = context.getAssets().open("tollbetweenplaces.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray tollBetweenPlacesArray = obj.getJSONArray("tollbetweenplaces");

            for (int i = 0; i < tollBetweenPlacesArray.length(); i++) {
                JSONObject tollBetweenPlacesObject = tollBetweenPlacesArray.getJSONObject(i);
                TollBetweenPlaces tollBetweenPlaces = new TollBetweenPlaces();
                tollBetweenPlaces.setTollId(tollBetweenPlacesObject.getInt("tollId"));
                tollBetweenPlaces.setRouteId(tollBetweenPlacesObject.getInt("routeId"));
                tollBetweenPlaces.setDistance(tollBetweenPlacesObject.getDouble("distance"));
                tollBetweenPlaces.setSingleCost(tollBetweenPlacesObject.getInt("singleCost"));
                tollBetweenPlaces.setReturnCost(tollBetweenPlacesObject.getInt("returnCost"));

                tollBetweenPlacesList.add(tollBetweenPlaces);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tollBetweenPlacesList;
    }
}
