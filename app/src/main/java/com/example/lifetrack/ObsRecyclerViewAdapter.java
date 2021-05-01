package com.example.lifetrack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ObsRecyclerViewAdapter extends RecyclerView.Adapter<ObsViewHolder> {
    private List<Observation> obs;
    private final OnToggleAlarmListener listener;

    public ObsRecyclerViewAdapter(OnToggleAlarmListener listener) {
        this.obs = new ArrayList<Observation>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ObsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_obs, parent, false);
        return new ObsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ObsViewHolder holder, final int position) {
        Observation observation = obs.get(position);
        holder.bind(observation, listener);
    }

    @Override
    public int getItemCount() {
        return obs.size();
    }


    @Override
    public void onViewRecycled(@NonNull ObsViewHolder holder) {
        super.onViewRecycled(holder);
        holder.alarmStarted.setOnCheckedChangeListener(null);
    }

    public void setObs(List<Observation> obs) {
        this.obs = obs;
        notifyDataSetChanged();
    }
}
