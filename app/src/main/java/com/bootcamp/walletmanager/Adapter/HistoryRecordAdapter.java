package com.bootcamp.walletmanager.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bootcamp.walletmanager.Activities.CreateDeal;
import com.bootcamp.walletmanager.Application.LoggedAccount;
import com.bootcamp.walletmanager.Datamodel.Records;
import com.bootcamp.walletmanager.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class HistoryRecordAdapter extends RecyclerView.Adapter<HistoryRecordAdapter.HistoryRecordViewHolder> implements RecordAdapter.OnClickRecord {

    Context mContext;
    int year;

    public HistoryRecordAdapter(Context context, int year) {
        mContext = context;
        this.year = year;
    }

    @NonNull
    @Override
    public HistoryRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.history_record_item, parent, false);
        return new HistoryRecordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryRecordViewHolder holder, int i) {
        holder.monthHeader.setText("Tháng " + getRecordsMonth().get(i).toString());

        holder.mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager recordLayout = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        holder.mRecyclerView.setLayoutManager(recordLayout);

        //TODO: Sort monthRecords by date
        List<Records> monthRecords = getRecordInMonth(getRecordsMonth().get(i));
        Collections.sort(monthRecords, new Comparator<Records>(){
            public int compare(Records o1, Records o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });

        RecordAdapter recordAdapter = new RecordAdapter(monthRecords, this);
        holder.mRecyclerView.setAdapter(recordAdapter);
    }

    @Override
    public void onRecordSelected(String id) {
        Intent intent = new Intent(mContext, CreateDeal.class);
        intent.putExtra("ViewState", "VIEW");
        intent.putExtra("RecordId", id);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return getRecordsMonth().size();
    }

    private List<Records> getRecordInMonth(int value) {
        List<Records> recordsList = new ArrayList<>();

        for (int i = 0; i < LoggedAccount.getCurrentLogin().getUserRecords().size(); i++) {
            Records record = LoggedAccount.getCurrentLogin().getUserRecords().get(i);
            Date date = record.getDate();
            String monthNumber  = (String) DateFormat.format("MM", date);
            String yearNumber  = (String) DateFormat.format("yyyy", date);
            int month = Integer.parseInt(monthNumber);
            int recordYear = Integer.parseInt(yearNumber);
            if (month == value && recordYear == year) {
                recordsList.add(record);
            }
        }
        return recordsList;
    }

    private ArrayList<Integer> getRecordsMonth() {
        ArrayList<Integer> months = new ArrayList<Integer>();

        for (int i = 0; i < LoggedAccount.getCurrentLogin().getUserRecords().size(); i++) {
            Records record = LoggedAccount.getCurrentLogin().getUserRecords().get(i);
            Date date = record.getDate();
            String monthNumber  = (String) DateFormat.format("MM", date);
            int year  = Integer.parseInt((String) DateFormat.format("yyyy", date));
            if (year == this.year) {
                int month = Integer.parseInt(monthNumber);
                months.add(month);
            }
        }
        HashSet<Integer> hashSet = new HashSet<Integer>();
        hashSet.addAll(months);
        months.clear();
        months.addAll(hashSet);
        Collections.sort(months, Collections.reverseOrder());
        Log.d("getallmonths", "getRecordsMonth: " + months);
        return months;
    }

    public class HistoryRecordViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecyclerView;
        TextView monthHeader;

        HistoryRecordViewHolder(View itemView) {
            super(itemView);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.monthRV);
            monthHeader = (TextView) itemView.findViewById(R.id.monthHeader);


        }
    }
}
