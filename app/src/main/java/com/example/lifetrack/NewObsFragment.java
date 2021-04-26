package com.example.lifetrack;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import java.util.Random;


public class NewObsFragment extends Fragment {

    TimePicker timePicker;
    EditText title;
    Button scheduleAlarm;
    CheckBox recurring;
    CheckBox mon;
    CheckBox tue;
    CheckBox wed;
    CheckBox thu;
    CheckBox fri;
    CheckBox sat;
    CheckBox sun;
    LinearLayout recurringOptions;

    private CreateAlarmViewModel createAlarmViewModel;

    public NewObsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        timePicker = getView().findViewById(R.id.fragment_createalarm_timePicker);
        title = getView().findViewById(R.id.fragment_createalarm_title);
        scheduleAlarm= getView().findViewById(R.id.fragment_createalarm_scheduleAlarm);
        recurring = getView().findViewById(R.id.fragment_createalarm_recurring);
        mon = getView().findViewById(R.id.fragment_createalarm_checkMon);
        tue = getView().findViewById(R.id.fragment_createalarm_checkTue);
        wed = getView().findViewById(R.id.fragment_createalarm_checkWed);
        thu =getView().findViewById(R.id.fragment_createalarm_checkThu);
        fri=getView().findViewById(R.id.fragment_createalarm_checkFri);
        sat=getView().findViewById(R.id.fragment_createalarm_checkSat);
        sun=getView().findViewById(R.id.fragment_createalarm_checkSun);
        recurringOptions=getView().findViewById(R.id.fragment_createalarm_recurring_options);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_obs, container, false);
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void scheduleAlarm() {
        int alarmId = new Random().nextInt(Integer.MAX_VALUE);

        int getHour, getMinute;
        if(Build.VERSION.SDK_INT < 23){
            getHour = timePicker.getCurrentHour();
            getMinute = timePicker.getCurrentMinute();
        } else{
            getHour = timePicker.getHour();
            getMinute = timePicker.getMinute();
        }

        Alarm alarm = new Alarm(
                alarmId,
                getHour,
                getMinute,
                title.getText().toString(),
                System.currentTimeMillis(),
                true,
                recurring.isChecked(),
                mon.isChecked(),
                tue.isChecked(),
                wed.isChecked(),
                thu.isChecked(),
                fri.isChecked(),
                sat.isChecked(),
                sun.isChecked()
        );

        createAlarmViewModel.insert(alarm);

        alarm.schedule(getContext());
    }


}