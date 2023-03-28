package com.androidexam.shubclassroom.utilities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class setDate implements View.OnFocusChangeListener, DatePickerDialog.OnDateSetListener {
    private EditText edt;
    private Calendar calendar;
    private Context context;
    public setDate(EditText edt, Context context) {
        this.edt = edt;
        this.edt.setOnFocusChangeListener(this);
        this.context = context;
        calendar = Calendar.getInstance();

    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String format = "yyyy-MM-dd";
        SimpleDateFormat sdformat = new SimpleDateFormat(format, Locale.US);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        edt.setText(sdformat.format(calendar.getTime()));
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus){
            new DatePickerDialog(context, this, calendar
                    .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }
}
