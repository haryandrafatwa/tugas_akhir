package org.d3ifcool.service.helpers;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import org.d3ifcool.service.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MethodHelper {

    // Requirement Random Karakter -----------------------------------------------------------------
    private char[] chars = "1234567890".toCharArray();
    private StringBuilder stringBuilder = new StringBuilder();
    private Random random = new Random();
    private String randomChar;
    // ---------------------------------------------------------------------------------------------


    public MethodHelper() {
    }

    // Method Random Character ---------------------------------------------------------------------
    public String getRandomChar() {
        for (int lenght = 0; lenght < 5; lenght++) {
            Character character = chars[random.nextInt(chars.length)];
            stringBuilder.append(character);
        }
        randomChar = stringBuilder.toString();
        stringBuilder.delete(0, 5);
        return randomChar;
    }
    // ---------------------------------------------------------------------------------------------

    public String getDateToday(){
        String FORMAT_DATE = "yyyy-MM-dd";
        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
        Date date = new Date();
        return dateFormat.format(date);
    }

    private String setTwoNumber(int number) {
        return number <=9 ?"0" + number : String.valueOf(number);
    }

    public void setDatePicker(final Context context, final TextView textView){

        final DatePickerDialog.OnDateSetListener datePickerDialog;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        final String[] date = new String[1];

        month = month + 1;
        date[0] = year+ "-"+ setTwoNumber(month)+ "-" + setTwoNumber(day) ;
        textView.setText(date[0]);
        datePickerDialog = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                date[0] = year + "-"+ setTwoNumber(month)+ "-" + setTwoNumber(dayOfMonth) ;
                textView.setText(date[0]);
            }
        };

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(context,
                        R.style.Theme_AppCompat_DayNight_Dialog_MinWidth,
                        datePickerDialog,year,month,day);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH, day);
                cal.set(Calendar.YEAR,year, -2);
                dialog.getDatePicker().setMinDate(cal.getTimeInMillis());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }
        });
    }

}