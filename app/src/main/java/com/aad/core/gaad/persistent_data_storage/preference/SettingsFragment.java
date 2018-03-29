package com.aad.core.gaad.persistent_data_storage.preference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

import com.aad.core.gaad.R;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_settings);

        SharedPreferences sharedPreferences  =getPreferenceScreen().getSharedPreferences();

        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int count = preferenceScreen.getPreferenceCount();

        for (int i = 0; i<count;i++){
            Preference preference = preferenceScreen.getPreference(i);

            if(preference instanceof ListPreference){
                String value = sharedPreferences.getString(preference.getKey(),"");
                sharedPreferenceSummary(preference,value);
            }
        }
    }

    private void sharedPreferenceSummary(Preference preferences,String value){

        if(preferences instanceof ListPreference){
            ListPreference listPreference = (ListPreference) preferences;

            int index = listPreference.findIndexOfValue(value);
            if(index>=0){
                listPreference.setSummary(listPreference.getEntries()[index]);
            }
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Preference preference = findPreference(s);
        if(preference!=null){
            if(preference instanceof ListPreference){
                String value = sharedPreferences.getString(preference.getKey(),"");
                sharedPreferenceSummary(preference,value);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
