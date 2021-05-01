package com.example.lifetrack;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AlarmRepository {
    private AlarmDao alarmDao;
    private LiveData<List<Observation>> alarmsLiveData;

    public AlarmRepository(Application application) {
        AlarmDatabase db = AlarmDatabase.getDatabase(application);
        alarmDao = db.alarmDao();
        alarmsLiveData = alarmDao.getAlarms();
    }

    public void insert(Observation observation) {
        AlarmDatabase.databaseWriteExecutor.execute(() -> {
            alarmDao.insert(observation);
        });
    }

    public void update(Observation observation) {
        AlarmDatabase.databaseWriteExecutor.execute(() -> {
            alarmDao.update(observation);
        });
    }

    public LiveData<List<Observation>> getAlarmsLiveData() {
        return alarmsLiveData;
    }
}