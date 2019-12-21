package com.bootcamp.walletmanager.Activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.bootcamp.walletmanager.Application.LoggedAccount;
import com.bootcamp.walletmanager.Datamodel.Records;
import com.bootcamp.walletmanager.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

public class RecordHistory extends CustomActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_history);

        setUpToolBar();
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
