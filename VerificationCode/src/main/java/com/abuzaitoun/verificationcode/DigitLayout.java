package com.abuzaitoun.verificationcode;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DigitLayout extends LinearLayout {
    private ArrayList<DigitEditText> editTexts;
    private ClipboardManager clipboardManager;
    private Activity activity;
    private int numberOfDigits;

    public DigitLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.DigitLayout);
        numberOfDigits = arr.getInteger(R.styleable.DigitLayout_digits, 6);
        initComponents(context, numberOfDigits);
        arr.recycle();
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    private void initComponents(Context context, int numberOfDigits) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.digit_layout, this);
        editTexts = new ArrayList<>();

        GridLayout gridLayout = view.findViewById(R.id.container);
        gridLayout.removeAllViews();

        for (int i = 0; i < numberOfDigits; i++){
            DigitEditTextLayout digitEditTextLayout = new DigitEditTextLayout(context);
            gridLayout.addView(digitEditTextLayout);
            editTexts.add(digitEditTextLayout.getDigitEditText());
        }

        for (int index = 0; index < editTexts.size(); index++){
            int finalIndex = index;
            editTexts.get(index).addTextChangedListener(new TextWatcherAdapter(){
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (i2 != 0) {
                        editTexts.get(finalIndex).clearFocus();
                        if (finalIndex + 1 < editTexts.size()) {
                            editTexts.get(finalIndex + 1).requestFocus();
                        }
                        else {
                            hideKeyboard(activity);
                        }
                    }
                }
            });
            editTexts.get(index).setOnCutCopyPasteListener(new DigitEditText.OnCutCopyPasteListener() {
                @Override
                public void onCut() {

                }

                @Override
                public void onCopy() {

                }

                @Override
                public void onPaste() {
                    if (clipboardManager != null && clipboardManager.hasPrimaryClip()){
                        String copiedText = clipboardManager.getPrimaryClip().getItemAt(0).getText().toString();
                        if (copiedText.length() == numberOfDigits){
                            for (int i = 0; i < copiedText.length(); i++){
                                editTexts.get(i).setText(Character.toString(copiedText.charAt(i)));
                            }
                        }else {
                            Toast.makeText(getContext(), "Copied text doesn't fit", Toast.LENGTH_SHORT).show();
                            editTexts.get(finalIndex).setText("");
                        }
                    }
                }
            });
        }
    }

    /**
     * Method to return the verification code concatenated from all input fields
     * @return The verification code as a string
     */
    public String getCode(){
        StringBuilder to_return = new StringBuilder();
        for (EditText et: editTexts){
            to_return.append(et.getText().toString());
        }
        Log.i("INVITATION_CODE", to_return.toString());
        return to_return.toString();
    }

    /**
     * Method to set clipboardManager, when set, onPaste() will be overridden allowing for pasting entire code
     * on fields, which will distribute the characters on fields.
     * @param clipboardManager System's clipboard manager: getSystemService(Context.CLIPBOARD_SERVICE)
     */
    public void setClipboardManager(ClipboardManager clipboardManager) {
        this.clipboardManager = clipboardManager;
    }

    private void hideKeyboard(Activity activity) {
        if (activity == null) return;
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    /**
     * User to set the number of digits for verification code, if numberOfDigits was greater than 6,
     * the layout will shift to a new line
     * @param numberOfDigits Number of digits for verification code
     */
    public void setNumberOfDigits(int numberOfDigits){
        this.numberOfDigits = numberOfDigits;
        initComponents(getContext(), numberOfDigits);
    }
}
