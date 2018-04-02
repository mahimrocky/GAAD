package com.aad.core.gaad.ui_and_ux.layout_in_java;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.aad.core.gaad.R;

/*
 * In this class, I will add simple view by java code, no xml file
 * */

public class LayoutInJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_layout_in_java);

        showJavaLayout();
    }

    private void showJavaLayout() {
        Button button = new Button(this);
        button.setText("Click me");
        button.setBackgroundColor(Color.YELLOW);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setBackgroundColor(Color.BLUE);

        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParams.addRule(RelativeLayout.CENTER_VERTICAL);

        relativeLayout.addView(button, buttonParams);

        setContentView(relativeLayout);

    }
}
