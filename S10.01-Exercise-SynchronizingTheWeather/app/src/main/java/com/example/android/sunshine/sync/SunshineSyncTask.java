package com.example.android.sunshine.sync;

//  COMPLETED (1) Create a class called SunshineSyncTask
//  COMPLETED (2) Within SunshineSyncTask, create a synchronized public static void method called syncWeather
//      COMPLETED (3) Within syncWeather, fetch new weather data
//      COMPLETED (4) If we have valid results, delete the old data and insert the new

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.android.sunshine.data.WeatherContract.WeatherEntry;
import com.example.android.sunshine.data.WeatherProvider;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import java.net.URL;

public class SunshineSyncTask {
    synchronized public static void syncWeather(Context context) {
        URL weatherUrl = NetworkUtils.getUrl(context);
        String jsonResult;
        ContentValues[] weatherContentValues = null;
        try {
            jsonResult = NetworkUtils.getResponseFromHttpUrl(weatherUrl);
            weatherContentValues = OpenWeatherJsonUtils.getWeatherContentValuesFromJson(context, jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ContentResolver contentResolver = context.getContentResolver();
        if (weatherContentValues != null) {
            Cursor cursor = contentResolver.query(WeatherEntry.CONTENT_URI,
                    null,
                    null,
                    null,
                    null);
            if (cursor.getCount() != 0) {
                contentResolver.delete(WeatherEntry.CONTENT_URI, null, null);

            }
            contentResolver.bulkInsert(WeatherEntry.CONTENT_URI, weatherContentValues);
        }
    }
}