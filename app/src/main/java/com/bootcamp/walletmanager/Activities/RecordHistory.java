package com.bootcamp.walletmanager.Activities;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.Toast;

import com.aigestudio.wheelpicker.WheelPicker;
import com.bootcamp.walletmanager.Adapter.HistoryRecordAdapter;
import com.bootcamp.walletmanager.Adapter.RecordAdapter;
import com.bootcamp.walletmanager.CustomView.YearPickerDialog;
import com.bootcamp.walletmanager.R;

import java.util.Calendar;
import java.util.Date;


public class RecordHistory extends CustomActivity implements YearPickerDialog.OnYearPickerListener, RecordAdapter.OnClickRecord {

    YearPickerDialog yearPickerDialog;
    Button yearPicker;
    RecyclerView yearRecord;
    HistoryRecordAdapter historyRecordAdapter;
    Date currentDate = Calendar.getInstance().getTime();
    int year  = Integer.parseInt((String) DateFormat.format("yyyy", currentDate));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_history);

        setUpToolBar();
        setUpRecyclerView();
        yearPicker();
    }

    private void setUpRecyclerView() {
        yearRecord = findViewById(R.id.records_recycler_view);
        yearRecord.setHasFixedSize(true);
        LinearLayoutManager recordLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        yearRecord.setLayoutManager(recordLayout);


        historyRecordAdapter = new HistoryRecordAdapter(this, year);
        yearRecord.setAdapter(historyRecordAdapter);
    }


    private void yearPicker() {
        yearPicker = (Button) findViewById(R.id.openPicker);
        yearPicker.setText(Integer.toString(year));
        yearPickerDialog = new YearPickerDialog(this, this);
        yearPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yearPickerDialog.show();
            }
        });
    }

    @Override
    public void onYearSelected(WheelPicker picker, Object data, int position) {
        Log.d("yearSelected", "onYearSelected: " + data.toString());
        yearPicker.setText(data.toString());
        yearPickerDialog.hide();
        historyRecordAdapter = new HistoryRecordAdapter(this, Integer.parseInt(data.toString()));
        yearRecord.setAdapter(historyRecordAdapter);
    }

    @Override
    public void onRecordSelected(String id) {

    }

    private void setUpToolBar() {
        Button returnBtn = (Button) findViewById(R.id.historyReturn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}