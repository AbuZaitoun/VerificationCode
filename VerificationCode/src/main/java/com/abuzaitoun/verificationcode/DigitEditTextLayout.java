package com.abuzaitoun.verificationcode;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;

public class DigitEditTextLayout extends TextInputLayout {

    private DigitEditText digitEditText;

    public DigitEditTextLayout(@NonNull Context context) {
        super(context);
        initComponents(context);
    }

    public DigitEditTextLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initComponents(context);

    }

    public DigitEditTextLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initComponents(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.single_digit_layout, this);
        digitEditText = view.findViewById(R.id.digit);
    }
    DigitEditText getDigitEditText(){
        return this.digitEditText;
    }
}
