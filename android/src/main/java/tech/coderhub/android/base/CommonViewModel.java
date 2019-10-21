package tech.coderhub.android.base;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Calendar;

import javax.inject.Inject;

public class CommonViewModel extends ViewModel {

    public final MutableLiveData<String> date = new MutableLiveData<>();
    public final MutableLiveData<String> time = new MutableLiveData<>();

    @Inject
    public CommonViewModel(){

    }

    public  void showDatePickerDialog(Context context){
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, (view, year, month, dayOfMonth) ->
                date.setValue(dayOfMonth + "-" + (month + 1) + "-" + year),
                calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public  void showTimePickerDialog(Context context){
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, (view, hourOfDay, minute) ->
                time.setValue(hourOfDay + ":" + minute),calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false);
        timePickerDialog.show();
    }
}
