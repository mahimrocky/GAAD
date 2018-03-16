package com.aad.core.gaad.application_components.job_schedule;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

/**
 * Created by w3E17 on 3/16/2018.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobScheduleTestService extends JobService{
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Toast.makeText(this,"Called",Toast.LENGTH_SHORT).show();

        // after calling this method will call every specific time schedule.
        JobUtil.startJob(getApplicationContext());
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
