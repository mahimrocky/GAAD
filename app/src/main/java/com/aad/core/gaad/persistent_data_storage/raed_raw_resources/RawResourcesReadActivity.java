package com.aad.core.gaad.persistent_data_storage.raed_raw_resources;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.aad.core.gaad.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RawResourcesReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);

        ///setContentView(R.layout.activity_raw_resources_read);
        setContentView(textView);

        // for reading file from assets folder

        BufferedReader bufferedReader = null;

        InputStreamReader inputStream = null;

        try {
            inputStream = new InputStreamReader(getAssets().open("bcs_requirement.txt"));

            bufferedReader = new BufferedReader(inputStream);

            String text;

            while((text = bufferedReader.readLine())!=null){
                textView.setText(text);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
