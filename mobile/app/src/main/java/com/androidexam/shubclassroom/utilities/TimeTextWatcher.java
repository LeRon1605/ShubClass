package com.androidexam.shubclassroom.utilities;

import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputEditText;

public class TimeTextWatcher implements TextWatcher {
    private TextInputEditText txtElement;
    public TimeTextWatcher(TextInputEditText txtElement) {
        this.txtElement = txtElement;
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String intValidNum = txtElement.getText().toString();
        if (intValidNum.length() == 2 && Integer.parseInt(intValidNum) < 24) {
            txtElement.setText(intValidNum + ":");
            return;
        }
        if (intValidNum.length() == 2 && Integer.parseInt(intValidNum) == 24) {
            txtElement.setText("00:");
            return;
        }
        if (intValidNum.length() == 2 && Integer.parseInt(intValidNum) > 24) {
            txtElement.setText("");
            return;
        }

        if (intValidNum.length() == 5 && Integer.parseInt(intValidNum.substring(3, 5)) < 60) {
            txtElement.setText(intValidNum + ":");
            return;
        }
        if (intValidNum.length() == 5 && Integer.parseInt(intValidNum.substring(3, 5)) > 60) {
            txtElement.setText(intValidNum.substring(0, 2) + ":");
            return;
        }
        if (intValidNum.length() == 5 && Integer.parseInt(intValidNum.substring(3, 5)) == 60) {
            txtElement.setText(intValidNum.substring(0, 2) + ":00:");
            return;
        }


        if (intValidNum.length() == 8 && Integer.parseInt(intValidNum.substring(6, 8)) > 60) {
            txtElement.setText(intValidNum.substring(0, 5) + ":");
            return;
        }

        if (intValidNum.length() == 8 && Integer.parseInt(intValidNum.substring(6, 8)) == 60) {
            txtElement.setText(intValidNum.substring(0, 5) + ":00");
            return;
        }

        if (intValidNum.length() > 8) {
            txtElement.setText(intValidNum.substring(0, 8));
            return;
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        txtElement.setSelection(txtElement.getText().length());
    }
}
