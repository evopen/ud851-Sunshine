/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.sunshine.data.WeatherContract.WeatherEntry;

/**
 * Manages a local database for weather data.
 */
// COMPLETED (11) Extend SQLiteOpenHelper from WeatherDbHelper
public class WeatherDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "weather.db";
    private static final int DATABASE_VERSION = 1;

    public WeatherDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE_SQL =
                "CREATE TABLE " + WeatherEntry.TABLE_NAME + "(" +
                        WeatherEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        WeatherEntry.COLUMN_DATE + " DATE NOT NULL," +
                        WeatherEntry.COLUMN_DEGREES + " FLOAT NOT NULL," +
                        WeatherEntry.COLUMN_HUMIDITY + " FLOAT NOT NULL," +
                        WeatherEntry.COLUMN_MAX_TEMP + " FLOAT NOT NULL," +
                        WeatherEntry.COLUMN_MIN_TEMP + " FLOAT NOT NULL," +
                        WeatherEntry.COLUMN_WIND_SPEED + " FLOAT NOT NULL," +
                        WeatherEntry.COLUMN_PRESSURE + " FLOAT NOT NULL," +
                        WeatherEntry.COLUMN_WEATHER_ID + " INTEGER NOT NULL" +
                        ");";

        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}