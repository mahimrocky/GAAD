package com.aad.core.gaad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aad.core.gaad.application_components.job_schedule.JobScheduleTestActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button jobSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jobSchedule = (Button) findViewById(R.id.button_job_schedule);



        jobSchedule.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_job_schedule:
                startActivity(new Intent(MainActivity.this, JobScheduleTestActivity.class));
                break;
        }
    }
}
