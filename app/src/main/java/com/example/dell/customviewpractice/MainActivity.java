package com.example.dell.customviewpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ValueSelector counterValueSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterValueSelector = (ValueSelector) findViewById(R.id.quentityCounter);
        counterValueSelector.setMaxValue(100);
        counterValueSelector.setMinValue(0);
    }
}
