package com.aad.core.gaad.application_components.shedule_task_by_alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by w3E17 on 3/20/2018.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("alarm_test", "Alarm working");
        Toast.makeText(context, "Alarm, Alarm, Alarm!", Toast.LENGTH_LONG).show();
    }
}
