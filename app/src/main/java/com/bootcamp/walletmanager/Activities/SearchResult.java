package com.bootcamp.walletmanager.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bootcamp.walletmanager.Adapter.RecordAdapter;
import com.bootcamp.walletmanager.Application.LoggedAccount;
import com.bootcamp.walletmanager.Datamodel.Records;
import com.bootcamp.walletmanager.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchResult extends CustomActivity implements RecordAdapter.OnClickRecord {

    Button backBtn;
    ArrayList<Records> allRecords;
    int amountType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        getAllRecords();
        implementToolbar();
        implementMoneySearch();
        implementWalletSearch();
        implementNoteSearch();
        implementKindSearch();
        implementRecyclerView();

    }

    private void removeUnequalifiedResults(ArrayList<String> removeList) {
        for (String id : removeList) {
            for (int i = 0; i < allRecords.size(); i++) {
                Records record = allRecords.get(i);
                if (record.getRecordID().equals(id)) {
                    allRecords.remove(record);
                }
            }
        }
    }

    private void implementMoneySearch() {
        amountType = getIntent().getIntExtra("amountType", 0);
        ArrayList<String> removeList = new ArrayList<>();

        if (amountType == Search.RangeType.LOWER.getInt()) {
            String minAmount = getIntent().getStringExtra("minAmount");
            Log.d("moneysearch", "implementMoneySearch: " + minAmount);
            for (int i = 0; i < allRecords.size(); i++) {
                Records record = allRecords.get(i);
                if (Integer.parseInt(record.getAmount()) >= Integer.parseInt(minAmount)) {
                    removeList.add(record.getRecordID());
                }
            }
        }
        else if (amountType == Search.RangeType.HIGHER.getInt()) {
            String minAmount = getIntent().getStringExtra("minAmount");
            for (int i = 0; i < allRecords.size(); i++) {
                Records record = allRecords.get(i);
                if (Integer.parseInt(record.getAmount()) <= Integer.parseInt(minAmount)) {
                    removeList.add(record.getRecordID());
                }
            }
        }
        else if (amountType == Search.RangeType.EQUALS.getInt()) {
            String minAmount = getIntent().getStringExtra("minAmount");
            for (int i = 0; i < allRecords.size(); i++) {
                Records record = allRecords.get(i);
                if (Integer.parseInt(record.getAmount()) != Integer.parseInt(minAmount)) {
                    removeList.add(record.getRecordID());
                }
            }
        }
        else if (amountType == Search.RangeType.RANGES.getInt()) {
            int minAmount = Integer.parseInt(getIntent().getStringExtra("minAmount"));
            int maxAmount = Integer.parseInt(getIntent().getStringExtra("maxAmount"));

            for (int i = 0; i < allRecords.size(); i++) {
                Records record = allRecords.get(i);
                int recordAmount = Integer.parseInt(record.getAmount());
                if (recordAmount < minAmount || recordAmount > maxAmount) {
                    removeList.add(record.getRecordID());
                }
            }
        }
        removeUnequalifiedResults(removeList);
    }

    private void implementWalletSearch() {
        ArrayList<String> removeList = new ArrayList<>();
        String walletName = getIntent().getStringExtra("wallet");
        Log.d("walletsearch", "implementWalletSearch: " + walletName);
        if (!walletName.equals("Tất cả các ví")) {
            for (Records record : allRecords) {
                if (!record.getFromWallet().equals(walletName)) {
                    removeList.add(record.getRecordID());
                }
            }
            removeUnequalifiedResults(removeList);
        }
    }

    private void implementNoteSearch() {
        ArrayList<String> removeList = new ArrayList<>();
        String note = getIntent().getStringExtra("note");
        if (note != null) {
            for (Records record : allRecords) {
                if (!record.getNotes().contains(note)) {
                    removeList.add(record.getRecordID());
                }
            }
            removeUnequalifiedResults(removeList);
        }
    }

    private void implementKindSearch() {
        ArrayList<String> removeList = new ArrayList<>();
        int kind = getIntent().getIntExtra("kind", 0);
        if (kind != Search.Kinds.ALL.getInt()) {
            String group = getIntent().getStringExtra("group");
            if (!group.equals("Tất cả")) {
                for (Records record : allRecords) {
                    if (!record.getType().equals(group)) {
                        removeList.add(record.getRecordID());
                    }
                }
                removeUnequalifiedResults(removeList);
            }
            else {
                if (kind == Search.Kinds.INCOME.getInt()) {
                    for (Records record : allRecords) {
                        if (!record.getKind().equals("income")) {
                            removeList.add(record.getRecordID());
                        }
                    }
                    removeUnequalifiedResults(removeList);
                }
                if (kind == Search.Kinds.SPENDING.getInt()) {
                    for (Records record : allRecords) {
                        if (!record.getKind().equals("spending")) {
                            removeList.add(record.getRecordID());
                        }
                    }
                    removeUnequalifiedResults(removeList);
                }
            }
        }
    }

    private void implementToolbar() {
        backBtn = (Button) findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void implementRecyclerView() {
        RecyclerView records = (RecyclerView) findViewById(R.id.records);
        records.setHasFixedSize(true);
        LinearLayoutManager recordLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        records.setLayoutManager(recordLayout);
        RecordAdapter recordAdapter = new RecordAdapter(allRecords, this);
        records.setAdapter(recordAdapter);

        LinearLayout emptyLayout = findViewById(R.id.noResultLayout);
        if (allRecords.size() == 0) {
            emptyLayout.setVisibility(View.VISIBLE);
        }
    }

    public void getAllRecords() {
        allRecords = new ArrayList<>();
        for (int i = 0; i < LoggedAccount.getCurrentLogin().getUserRecords().size(); i++) {
            Records record = LoggedAccount.getCurrentLogin().getUserRecords().get(i);
            allRecords.add(record);
        }

        Collections.sort(allRecords, new Comparator<Records>(){
            public int compare(Records o1, Records o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });
    }

    @Override
    public void onRecordSelected(String id) {
        Intent intent = new Intent(this, CreateDeal.class);
        intent.putExtra("ViewState", "VIEW");
        intent.putExtra("RecordId", id);
        startActivity(intent);
    }
}
