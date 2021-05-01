package com.example.lifetrack;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

public class ObsViewHolder extends RecyclerView.ViewHolder {
    private final TextView obsName;
    SwitchCompat alarmStarted;

    public ObsViewHolder(@NonNull View view) {
        super(view);

        obsName = view.findViewById(R.id.item_obs_name);
        alarmStarted = view.findViewById(R.id.item_alarm_started);
    }

    public void bind(Observation observation, OnToggleAlarmListener listener) {
        obsName.setText(observation.getName());

        alarmStarted.setChecked(observation.isStarted());
        alarmStarted.setOnCheckedChangeListener((buttonView, isChecked) -> listener.onToggle(observation));
    }
}
