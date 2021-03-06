package com.abuzaitoun.verificationcode;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
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
    private int iterator = 0;
    private ClipboardManager clipboardManager;
    private Activity activity;

    public DigitLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initComponents(context);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    private void initComponents(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.digit_layout, this);
        DigitEditText et1 = findViewById(R.id.digit1);
        DigitEditText et2 = findViewById(R.id.digit2);
        DigitEditText et3 = findViewById(R.id.digit3);
        DigitEditText et4 = findViewById(R.id.digit4);
        DigitEditText et5 = findViewById(R.id.digit5);
        DigitEditText et6 = findViewById(R.id.digit6);

        editTexts = new ArrayList<>();
        editTexts.add(et1);
        editTexts.add(et2);
        editTexts.add(et3);
        editTexts.add(et4);
        editTexts.add(et5);
        editTexts.add(et6);

        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i2 != 0) {
                    et1.clearFocus();
                    et2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i2!=0) {
                    et2.clearFocus();
                    et3.requestFocus();
                }else {
                    //et2.setText("");
                    et2.clearFocus();
                    et1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i2!=0) {
                    et3.clearFocus();
                    et4.requestFocus();
                }else {
                    // et3.setText("");
                    et3.clearFocus();
                    et2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i2 != 0) {
                    et4.clearFocus();
                    et5.requestFocus();
                }else{
                    //et4.setText("");
                    et4.clearFocus();
                    et3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i2 != 0 ){
                    et5.clearFocus();
                    et6.requestFocus();
                }else{
                    //et5.setText("");
                    et5.clearFocus();
                    et4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i2 != 0){
                    hideKeyboard(activity);
                }else{
                    //et6.setText("");
                    et6.clearFocus();
                    et5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et1.setOnCutCopyPasteListener(new DigitEditText.OnCutCopyPasteListener() {
            @Override
            public void onCut() {

            }

            @Override
            public void onCopy() {

            }

            @Override
            public void onPaste() {
                if (clipboardManager.hasPrimaryClip()){
                    Log.i("CLIP", clipboardManager.getPrimaryClip().getItemAt(0).getText().toString());
                    String copied_text = clipboardManager.getPrimaryClip().getItemAt(0).getText().toString();
                    if (copied_text.length()==6) {
                        et1.setText(Character.toString(copied_text.charAt(0)));
                        et2.setText(Character.toString(copied_text.charAt(1)));
                        et3.setText(Character.toString(copied_text.charAt(2)));
                        et4.setText(Character.toString(copied_text.charAt(3)));
                        et5.setText(Character.toString(copied_text.charAt(4)));
                        et6.setText(Character.toString(copied_text.charAt(5)));
                    }
                }
            }
        });

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
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}
