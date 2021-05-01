package com.example.lifetrack;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AlarmDao {
    @Insert
    void insert(Observation observation);

    @Query("DELETE FROM obs_table")
    void deleteAll();

    @Query("SELECT * FROM obs_table ORDER BY ObsId ASC")
    LiveData<List<Observation>> getAlarms();

    @Update
    void update(Observation observation);
}
