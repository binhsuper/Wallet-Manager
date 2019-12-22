package com.bootcamp.walletmanager.CustomView;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.bootcamp.walletmanager.Application.LoggedAccount;
import com.bootcamp.walletmanager.Datamodel.Records;
import com.bootcamp.walletmanager.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.WeakHashMap;

public class YearPickerDialog {
    public Dialog mYearPicker;
    OnYearPickerListener mListener;

    public YearPickerDialog(final Activity activity, OnYearPickerListener listener) {

        mListener = listener;
        mYearPicker = new Dialog(activity);
        mYearPicker.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mYearPicker.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mYearPicker.setContentView(R.layout.year_picker_dialog);

        WheelPicker wheelPicker = mYearPicker.findViewById(R.id.yearPicker);

        wheelPicker.setData(getRecordsYear());

        wheelPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                mListener.onYearSelected(picker, data, position);
            }
        });
    }

    private ArrayList<Integer> getRecordsYear() {
        ArrayList<Integer> years = new ArrayList<Integer>();

        for (int i = 0; i < LoggedAccount.getCurrentLogin().getUserRecords().size(); i++) {
            Records record = LoggedAccount.getCurrentLogin().getUserRecords().get(i);
            Date date = record.getDate();
            int year  = Integer.parseInt((String) DateFormat.format("yyyy", date));
            years.add(year);
        }
        HashSet<Integer> hashSet = new HashSet<Integer>();
        hashSet.addAll(years);
        years.clear();
        years.addAll(hashSet);
        Collections.sort(years, Collections.reverseOrder());
        Log.d("getallyears", "getRecordsMonth: " + years);
        return years;
    }

    public void hide() {
        if (mYearPicker.isShowing())
            mYearPicker.cancel();
    }

    public void show() {
        if (!mYearPicker.isShowing())
            mYearPicker.show();
    }

    public interface OnYearPickerListener {
        void onYearSelected(WheelPicker picker, Object data, int position);
    }

    public boolean isShowing() {
        return mYearPicker.isShowing();
    }
}

