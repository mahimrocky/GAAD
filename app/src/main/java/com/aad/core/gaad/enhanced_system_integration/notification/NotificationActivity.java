package com.aad.core.gaad.enhanced_system_integration.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aad.core.gaad.R;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private NotificationUtility notificationUtil;
    Button standard, standardHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        standard = (Button) findViewById(R.id.standard);
        standardHead = (Button) findViewById(R.id.standard_head);


        standard.setOnClickListener(this);
        standardHead.setOnClickListener(this);

        notificationUtil = new NotificationUtility();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.standard:
                notificationUtil.showStandardNotification(this);
                break;
            case R.id.standard_head:
                notificationUtil.showStandardHeadsUpNotification(this);
                break;
        }
    }
}
