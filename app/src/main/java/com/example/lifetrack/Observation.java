package com.example.lifetrack;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;

import static com.example.lifetrack.AlarmBroadcastReceiver.FRIDAY;
import static com.example.lifetrack.AlarmBroadcastReceiver.MONDAY;
import static com.example.lifetrack.AlarmBroadcastReceiver.RECURRING;
import static com.example.lifetrack.AlarmBroadcastReceiver.SATURDAY;
import static com.example.lifetrack.AlarmBroadcastReceiver.SUNDAY;
import static com.example.lifetrack.AlarmBroadcastReceiver.THURSDAY;
import static com.example.lifetrack.AlarmBroadcastReceiver.TITLE;
import static com.example.lifetrack.AlarmBroadcastReceiver.TUESDAY;
import static com.example.lifetrack.AlarmBroadcastReceiver.WEDNESDAY;

@Entity(tableName = "obs_table")
public class Observation {
    @PrimaryKey
    @NonNull
    private int ObsId;

    private String name;
    private int datatype;

    private int hour, minute;
    private boolean started;
    private boolean monday, tuesday, wednesday, thursday, friday, saturday, sunday;

    private long created;

    public Observation(int ObsId, int hour, int minute, String name, long created,
                       boolean started, boolean monday, boolean tuesday,
                       boolean wednesday, boolean thursday, boolean friday, boolean saturday, boolean sunday, int datatype) {
        this.ObsId = ObsId;
        this.hour = hour;
        this.minute = minute;
        this.started = started;

        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;

        this.name = name;
        this.datatype = datatype;

        this.created = created;

    }

    public int getHour() {
        return hour;
    }
    public int getMinute() {
        return minute;
    }
    public int getDatatype() { return datatype; }
    public boolean isStarted() {
        return started;
    }
    public int getObsId() {
        return ObsId;
    }

    public boolean isMonday() {
        return monday;
    }
    public boolean isTuesday() {
        return tuesday;
    }
    public boolean isWednesday() {
        return wednesday;
    }
    public boolean isThursday() {
        return thursday;
    }
    public boolean isFriday() {
        return friday;
    }
    public boolean isSaturday() {
        return saturday;
    }
    public boolean isSunday() {
        return sunday;
    }

    public void setObsId(int obsId) {
        this.ObsId = obsId;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void schedule(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
        intent.putExtra(MONDAY, monday);
        intent.putExtra(TUESDAY, tuesday);
        intent.putExtra(WEDNESDAY, wednesday);
        intent.putExtra(THURSDAY, thursday);
        intent.putExtra(FRIDAY, friday);
        intent.putExtra(SATURDAY, saturday);
        intent.putExtra(SUNDAY, sunday);

        intent.putExtra(TITLE, name);

        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, ObsId, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // if alarm time has already passed, increment day by 1
        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        }


        String toastText = String.format("Recurring Alarm %s scheduled for %s at %02d:%02d", name, getRecurringDaysText(), hour, minute, ObsId);
        Toast.makeText(context, toastText, Toast.LENGTH_LONG).show();

        final long RUN_DAILY = 24 * 60 * 60 * 1000;
        alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                RUN_DAILY,
                alarmPendingIntent);


        this.started = true;
    }

    public void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, ObsId, intent, 0);
        alarmManager.cancel(alarmPendingIntent);
        this.started = false;

        String toastText = String.format("Alarm cancelled for %02d:%02d with id %d", hour, minute, ObsId);
        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
        Log.i("cancel", toastText);
    }

    public String getRecurringDaysText() {
        String days = "";
        if (monday) {
            days += "Mo ";
        }
        if (tuesday) {
            days += "Tu ";
        }
        if (wednesday) {
            days += "We ";
        }
        if (thursday) {
            days += "Th ";
        }
        if (friday) {
            days += "Fr ";
        }
        if (saturday) {
            days += "Sa ";
        }
        if (sunday) {
            days += "Su ";
        }

        return days;
    }

    public String getName() {
        return name;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}
