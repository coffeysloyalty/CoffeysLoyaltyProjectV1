package com.alicearmstrong.coffeysloyaltyprojectv1;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT,this,year,month,day);

        //User must be 18 years old to register. Subtract 18 years from current date
        calendar.add(Calendar.YEAR, -18);
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        return  datePickerDialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        //Displaying the date
        TextView dateDisplay = (TextView) getActivity().findViewById(R.id.etDisplayDate);

        //Create a Date variable/object with user chosen date
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Date chosenDate = calendar.getTime();

        //Format the date using style and location
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        String formattedDate = dateFormat.format(chosenDate);

        dateDisplay.setText(formattedDate);
    }
}
