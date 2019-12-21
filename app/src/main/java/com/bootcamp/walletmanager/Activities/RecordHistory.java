package com.bootcamp.walletmanager.Activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.bootcamp.walletmanager.Adapter.HistoryRecordAdapter;
import com.bootcamp.walletmanager.R;

import java.util.Calendar;


public class RecordHistory extends CustomActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_history);

        setUpToolBar();

        RecyclerView yearRecord = findViewById(R.id.records_recycler_view);
        yearRecord.setHasFixedSize(true);
        LinearLayoutManager recordLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        yearRecord.setLayoutManager(recordLayout);
        HistoryRecordAdapter historyRecordAdapter = new HistoryRecordAdapter(this);
        yearRecord.setAdapter(historyRecordAdapter);
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
