package com.aad.core.gaad.application_components.shedule_task_by_alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.aad.core.gaad.R;

public class AlarmActivity extends AppCompatActivity {
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    long currentTime = System.currentTimeMillis();
    long oneMinute = 5 * 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        Button setAlarmButton = (Button)findViewById(R.id.setAlarmButton);
        Button cancelAlarmButton = (Button)findViewById(R.id.cancelAlarmButton);


        alarmManager = (AlarmManager)this.getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(AlarmActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);


        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarm();
            }
        });
        cancelAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelAlarm();
            }
        });


    }

    private void setAlarm() {
        Log.d("alarm_test", "AlarmReceiver set");
        alarmManager.setRepeating(AlarmManager.RTC, currentTime, oneMinute, pendingIntent);

        // Enable BootReceiver Component
        setBootReceiverEnabled(PackageManager.COMPONENT_ENABLED_STATE_ENABLED);
    }

    private void cancelAlarm() {
        Log.d("alarm_test", "AlarmReceiver cancelled");
        alarmManager.cancel(pendingIntent);

        // Disable BootReceiver Component
        setBootReceiverEnabled(PackageManager.COMPONENT_ENABLED_STATE_DISABLED);
    }

    private void setBootReceiverEnabled(int componentEnabledState) {
        ComponentName componentName = new ComponentName(this, BootReciever.class);
        PackageManager packageManager = getPackageManager();
        packageManager.setComponentEnabledSetting(componentName,
                componentEnabledState,
                PackageManager.DONT_KILL_APP);
    }
}
