package com.aad.core.gaad.persistent_data_storage.preference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.aad.core.gaad.R;

/*
* In this section
* @XML preference
* @JAVA code preference
* */
public class PreferenceTestActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    TextView checkValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_test);

        checkValue = (TextView) findViewById(R.id.text_view_checking_value);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.registerOnSharedPreferenceChangeListener(this);
        setTextInCheckValue(preferences);

    }

    private void setTextInCheckValue(SharedPreferences preferences){

        checkValue.setText("Checking value is "+String.valueOf(preferences.getBoolean("check_item",true)));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pref,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.action_settings){
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if(s.equals("check_item")){
            setTextInCheckValue(sharedPreferences);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}
