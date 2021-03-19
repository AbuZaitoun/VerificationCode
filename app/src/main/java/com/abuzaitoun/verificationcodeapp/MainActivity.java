package com.abuzaitoun.verificationcodeapp;

import android.content.ClipboardManager;
import android.content.Context;
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
        ClipboardManager clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        digitLayout.setClipboardManager(clipboardManager);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            String code = digitLayout.getCode();
            button.setText(code);
        });
    }
}