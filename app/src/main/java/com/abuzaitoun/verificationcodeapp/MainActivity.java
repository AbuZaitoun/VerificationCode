package com.abuzaitoun.verificationcodeapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.abuzaitoun.verificationcode.DigitLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DigitLayout digitLayout = findViewById(R.id.my_layout);
        digitLayout.setActivity(this);
        digitLayout.setNumberOfDigits(3);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            String code = digitLayout.getCode();
            button.setText(code);
        });
    }
}