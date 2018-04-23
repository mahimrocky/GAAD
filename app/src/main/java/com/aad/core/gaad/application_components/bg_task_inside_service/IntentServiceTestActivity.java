package com.aad.core.gaad.application_components.bg_task_inside_service;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.aad.core.gaad.R;

public class IntentServiceTestActivity extends AppCompatActivity {

    private CashbackReciver cashbackReciver;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service_test);

        tv = (TextView) findViewById(R.id.cb_results);
        registerCashbackReceiver();
    }

    public void startCashbackService(View view){
        EditText et = (EditText) findViewById(R.id.cashback_cat);

        Intent cbIntent =  new Intent(this, IntentServiceTest.class);
        cbIntent.putExtra("cashback_cat", et.getText().toString());
        startService(cbIntent);
    }

    private void registerCashbackReceiver(){
        cashbackReciver = new CashbackReciver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(IntentServiceTest.CASHBACK_INFO);

        registerReceiver(cashbackReciver, intentFilter);
    }

    private class CashbackReciver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String cbinfo = intent.getStringExtra("cashback");
            tv.setText(cbinfo);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(cashbackReciver);
    }
}
