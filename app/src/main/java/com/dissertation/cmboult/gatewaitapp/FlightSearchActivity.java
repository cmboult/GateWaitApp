package com.dissertation.cmboult.gatewaitapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Christian on 24/03/2015.
 */
public class FlightSearchActivity extends Activity implements DatePickerDialog.OnDateSetListener {

    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_search_activity);
        getActionBar().setTitle("Search");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        dateView = (TextView) findViewById(R.id.chosenDate);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month, day);
    }

    public void onClickSetDate(View view){
        Bundle b = new Bundle();
        b.putInt(DatePickerFragment.YEAR, year);
        b.putInt(DatePickerFragment.MONTH, month);
        b.putInt(DatePickerFragment.DATE, day);
        DialogFragment picker = new DatePickerFragment();
        picker.setArguments(b);
        picker.show(getFragmentManager(), "fragment_date_picker");
    }

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth)
    {
        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        String s = formatter.format(calendar.getTime());

        dateView.setText(s);
    }

}