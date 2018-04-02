package com.aad.core.gaad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aad.core.gaad.application_components.job_schedule.JobScheduleTestActivity;
import com.aad.core.gaad.application_components.loader.LoaderActivity;
import com.aad.core.gaad.persistent_data_storage.content_provider.ContentProviderTestActivity;
import com.aad.core.gaad.persistent_data_storage.preference.PreferenceTestActivity;
import com.aad.core.gaad.persistent_data_storage.raed_raw_resources.RawResourcesReadActivity;
import com.aad.core.gaad.ui_and_ux.layout_in_java.LayoutInJavaActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button jobSchedule, contentProvider, readResources, preference, loader, javaLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jobSchedule = (Button) findViewById(R.id.button_job_schedule);
        contentProvider = (Button) findViewById(R.id.button_content_provider);
        readResources = (Button) findViewById(R.id.button_read_resources);
        preference = (Button) findViewById(R.id.button_preference);
        loader = (Button) findViewById(R.id.button_loader);
        javaLayout = (Button) findViewById(R.id.button_java_layout);


        jobSchedule.setOnClickListener(this);
        contentProvider.setOnClickListener(this);
        readResources.setOnClickListener(this);
        preference.setOnClickListener(this);
        loader.setOnClickListener(this);
        javaLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_job_schedule:
                startActivity(new Intent(MainActivity.this, JobScheduleTestActivity.class));
                break;
            case R.id.button_content_provider:
                startActivity(new Intent(MainActivity.this, ContentProviderTestActivity.class));
                break;
            case R.id.button_read_resources:
                startActivity(new Intent(MainActivity.this, RawResourcesReadActivity.class));
                break;
            case R.id.button_preference:
                startActivity(new Intent(MainActivity.this, PreferenceTestActivity.class));
                break;
            case R.id.button_loader:
                startActivity(new Intent(MainActivity.this, LoaderActivity.class));
                break;
            case R.id.button_java_layout:
                startActivity(new Intent(MainActivity.this, LayoutInJavaActivity.class));
                break;
        }
    }
}
