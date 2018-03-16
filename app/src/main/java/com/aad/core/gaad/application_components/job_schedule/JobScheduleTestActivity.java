package com.aad.core.gaad.application_components.job_schedule;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aad.core.gaad.R;

public class JobScheduleTestActivity extends AppCompatActivity {

    Button startJob,stopJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_schedule_test);

        startJob = (Button) findViewById(R.id.button_start_job);
        stopJob = (Button) findViewById(R.id.button_stop_job);

        startJob.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
               JobUtil.startJob(JobScheduleTestActivity.this);
            }
        });

        stopJob.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                JobScheduler jobScheduler = (JobScheduler) JobScheduleTestActivity.this.getSystemService(JOB_SCHEDULER_SERVICE);
                jobScheduler.cancelAll();
            }
        });

    }



}
