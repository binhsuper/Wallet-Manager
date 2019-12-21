package com.bootcamp.walletmanager.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bootcamp.walletmanager.Datamodel.Records;
import com.bootcamp.walletmanager.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

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
        holder.walletName.setText(mRecords.get(position).getFromWallet());
        holder.recordType.setText(mRecords.get(position).getType());

        String currentTime = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(mRecords.get(position).getDate());
        holder.date.setText(currentTime);

        if (mRecords.get(position).getKind().equals("spending")) {
            holder.money.setText("- " +mRecords.get(position).getAmount() + " đ");
            holder.money.setTextColor(Color.RED);
        }
        else {
            holder.money.setText("+ " +mRecords.get(position).getAmount() + " đ");
            holder.money.setTextColor(Color.rgb(11,176,13));
        }

        switch (mRecords.get(position).getType()) {
            case "Quà tặng" :
                holder.background.setImageResource(R.drawable.type_gift);
                break;
            case "Lương" :
                holder.background.setImageResource(R.drawable.type_salary);
                break;
            case "Bán đồ" :
                holder.background.setImageResource(R.drawable.type_sale);
                break;
            case "Thưởng" :
                holder.background.setImageResource(R.drawable.type_reward);
                break;
            case "Khác" :
                holder.background.setImageResource(R.drawable.type_other);
                break;
            case "Thực phẩm" :
                holder.background.setImageResource(R.drawable.type_food);
                break;
            case "Tạp hoá" :
                holder.background.setImageResource(R.drawable.type_groceries);
                break;
            case "Sức khoẻ" :
                holder.background.setImageResource(R.drawable.type_health);
                break;
            case "Di chuyển" :
                holder.background.setImageResource(R.drawable.type_transport);
                break;
            case "Hoá đơn" :
                holder.background.setImageResource(R.drawable.type_tax);
                break;
        }

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
