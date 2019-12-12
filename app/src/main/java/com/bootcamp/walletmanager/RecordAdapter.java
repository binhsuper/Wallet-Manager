package com.bootcamp.walletmanager;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {
    List<Records> mRecords;
    Context mContext;

    public RecordAdapter( Context context, List<Records> records) {
        mRecords = records;
        mContext = context;
    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardView itemView = (CardView) layoutInflater.inflate(R.layout.records_layout, parent, false);
        return new RecordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        holder.walletName.setText(mRecords.get(position).name);
        holder.money.setText(mRecords.get(position).amount);
        holder.recordType.setText(mRecords.get(position).fromWallet);
        holder.date.setText(mRecords.get(position).date);
        holder.background.setImageResource(mRecords.get(position).typeImg);

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecords.size();
    }

    public class RecordViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView recordType;
        TextView walletName;
        TextView money;
        TextView date;
        ImageView background;

        RecordViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.record);
            walletName = (TextView) itemView.findViewById(R.id.walletName);
            date = (TextView) itemView.findViewById(R.id.date);
            money = (TextView) itemView.findViewById(R.id.money);
            recordType = (TextView) itemView.findViewById(R.id.typeName);
            background = (ImageView) itemView.findViewById(R.id.typeImg);
        }
    }
}
