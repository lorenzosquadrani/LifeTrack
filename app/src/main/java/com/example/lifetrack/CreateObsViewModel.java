package com.example.lifetrack;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class CreateObsViewModel extends AndroidViewModel {
    private AlarmRepository alarmRepository;

    public CreateObsViewModel(@NonNull Application application) {
        super(application);

        alarmRepository = new AlarmRepository(application);
    }

    public void insert(Observation observation) {
        alarmRepository.insert(observation);
    }
}