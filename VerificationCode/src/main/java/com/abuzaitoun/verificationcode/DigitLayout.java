package com.abuzaitoun.verificationcode;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DigitLayout extends LinearLayout {
    private ArrayList<DigitEditText> editTexts;
    private ClipboardManager clipboardManager;
    private Activity activity;
    private int index = 0;

    public DigitLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.DigitLayout);
        int number_of_digit = arr.getInteger(R.styleable.DigitLayout_digits, 6);
        initComponents(context, number_of_digit);

        arr.recycle();
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    private void initComponents(Context context, int number_of_digit) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.digit_layout, this);
        LinearLayout linearLayout = view.findViewById(R.id.container);

        ArrayList<DigitEditText> digitEditTexts = new ArrayList<>();

        for (int i = 0; i < number_of_digit; i++){
            DigitEditTextLayout digitEditTextLayout = new DigitEditTextLayout(context);
            linearLayout.addView(digitEditTextLayout);
            digitEditTexts.add(digitEditTextLayout.getDigitEditText());
        }


        for (DigitEditText editText: digitEditTexts){
            editText.addTextChangedListener(new TextWatcherAdapter(){
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (i2 != 0) {
                        if (index < digitEditTexts.size())
                            digitEditTexts.get(index).clearFocus();
                        if (index + 1 < digitEditTexts.size())
                            digitEditTexts.get(index++).requestFocus();
                        else {
                            hideKeyboard(activity);
                        }
                    }
                }
            });
            index++;
        }

//        et1.setOnCutCopyPasteListener(new DigitEditText.OnCutCopyPasteListener() {
//            @Override
//            public void onCut() {
//
//            }
//
//            @Override
//            public void onCopy() {
//
//            }
//
//            @Override
//            public void onPaste() {
//                if (clipboardManager.hasPrimaryClip()){
//                    Log.i("CLIP", clipboardManager.getPrimaryClip().getItemAt(0).getText().toString());
//                    String copied_text = clipboardManager.getPrimaryClip().getItemAt(0).getText().toString();
//                    if (copied_text.length()==6) {
//                        et1.setText(Character.toString(copied_text.charAt(0)));
//                        et2.setText(Character.toString(copied_text.charAt(1)));
//                        et3.setText(Character.toString(copied_text.charAt(2)));
//                        et4.setText(Character.toString(copied_text.charAt(3)));
//                        et5.setText(Character.toString(copied_text.charAt(4)));
//                        et6.setText(Character.toString(copied_text.charAt(5)));
//                    }
//                }
//            }
//        });

    }
    public String getCode(){
        StringBuilder to_return = new StringBuilder();
        for (EditText et: editTexts){
            to_return.append(et.getText().toString());
        }
        Log.i("INVITATION_CODE", to_return.toString());
        return to_return.toString();
    }

    public void setClipboardManager(ClipboardManager clipboardManager) {
        this.clipboardManager = clipboardManager;
    }

    public ArrayList<DigitEditText> getEditTexts() {
        return editTexts;
    }

    public static void hideKeyboard(Activity activity) {
        if (activity == null) return;
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}
