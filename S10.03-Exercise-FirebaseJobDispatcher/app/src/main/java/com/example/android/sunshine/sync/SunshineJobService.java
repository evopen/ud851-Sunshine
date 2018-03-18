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
package com.example.android.sunshine.sync;

// COMPLETED (2) Make sure you've imported the jobdispatcher.JobService, not job.JobService

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;

// COMPLETED (3) Add a class called SunshineFirebaseJobService that extends jobdispatcher.JobService
public class SunshineJobService extends JobService {
    AsyncTask mFetchWeatherTask;
    @Override
    public boolean onStartJob(final JobParameters params) {
        mFetchWeatherTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                SunshineSyncUtils.startImmediateSync(SunshineJobService.this);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                jobFinished(params, true);
            }
        };
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        if(mFetchWeatherTask != null)
            mFetchWeatherTask.cancel(true);
        return true;
    }
}
//  COMPLETED (4) Declare an ASyncTask field called mFetchWeatherTask

//  COMPLETED (5) Override onStartJob and within it, spawn off a separate ASyncTask to sync weather data
//              COMPLETED (6) Once the weather data is sync'd, call jobFinished with the appropriate arguments

//  COMPLETED (7) Override onStopJob, cancel the ASyncTask if it's not null and return true
