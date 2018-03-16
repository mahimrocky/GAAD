package com.aad.core.gaad.application_components.job_schedule;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import static android.content.Context.JOB_SCHEDULER_SERVICE;

/**
 * Created by w3E17 on 3/16/2018.
 */

public class JobUtil {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void startJob(Context context){
        ComponentName componentName = new ComponentName(context,JobScheduleTestService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0,componentName);

        builder.setMinimumLatency(1*1000);
        builder.setOverrideDeadline(10*1000);

        builder.setRequiresCharging(true);

        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JOB_SCHEDULER_SERVICE);

        jobScheduler.schedule(builder.build());

    }

}
