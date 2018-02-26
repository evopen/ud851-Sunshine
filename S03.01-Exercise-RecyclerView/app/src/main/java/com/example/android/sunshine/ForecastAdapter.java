package com.example.android.sunshine;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by dhh on 26/02/2018.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private String[] mWeatherData;

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.forecast_list_item, parent, false);
        ForecastViewHolder holder = new ForecastViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        holder.mDataTextView.setText(mWeatherData[position]);
    }

    @Override
    public int getItemCount() {
        if (mWeatherData == null) {
            return 0;
        } else {
            return mWeatherData.length;
        }
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {
        final TextView mDataTextView;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            mDataTextView = itemView.findViewById(R.id.tv_weather_data);
        }
    }

    public void setmWeatherData(String[] mWeatherData) {
        this.mWeatherData = mWeatherData;
        notifyDataSetChanged();
    }
}
