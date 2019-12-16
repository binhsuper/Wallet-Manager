package com.bootcamp.walletmanager.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.bootcamp.walletmanager.Adapter.DealTypeAdapter;
import com.bootcamp.walletmanager.Adapter.WalletAdapter;
import com.bootcamp.walletmanager.Datamodel.DealType;
import com.bootcamp.walletmanager.Datamodel.DealTypeData;
import com.bootcamp.walletmanager.R;

import java.nio.file.attribute.GroupPrincipal;

public class DealTypes extends CustomActivity implements DealTypeAdapter.GroupSelected {
    DealTypeData mDealTypeData;
    RecyclerView walletsView;
    DealTypeAdapter dealTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_types);

        configureToolbar();
        configureRecyclerView();
        configureSpinner();
    }

    private void configureToolbar() {
        Button closeBtn = (Button) findViewById(R.id.dealTypesCloseBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void configureRecyclerView() {
        walletsView = findViewById(R.id.types);
        walletsView.setHasFixedSize(true);
        LinearLayoutManager typeLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        walletsView.setLayoutManager(typeLayout);
    }

    private void configureSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.groupType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.group_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        final DealTypeAdapter.GroupSelected groupSelected = this;

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDealTypeData = new DealTypeData(parent.getSelectedItemPosition());
                dealTypeAdapter = new DealTypeAdapter(mDealTypeData.getTypes(), groupSelected);
                walletsView.setAdapter(dealTypeAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onGroupSelected(DealType type) {
        Log.d("dog", "onGroupSelected: " + type.getTypeName());
        Intent intent = new Intent();
        intent.putExtra("groupName", type.getTypeName());
        intent.putExtra("groupKind", type.getTypeKind());
        intent.putExtra("groupImg", type.getTypeImg());

        setResult(RESULT_OK, intent);
        finish();
    }
}
