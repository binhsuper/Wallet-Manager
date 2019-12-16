package com.bootcamp.walletmanager.Adapter;

import android.graphics.Color;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bootcamp.walletmanager.Datamodel.DealType;
import com.bootcamp.walletmanager.Datamodel.Records;
import com.bootcamp.walletmanager.R;

import java.util.List;
import java.util.logging.Handler;

public class DealTypeAdapter extends RecyclerView.Adapter<DealTypeAdapter.DealTypeViewHolder>  {
    List<DealType> mTypes;
    GroupSelected mGroupSelected;

    public DealTypeAdapter(List<DealType> types, GroupSelected groupSelected) {
        mTypes = types;
        mGroupSelected = groupSelected;
    }

    @NonNull
    @Override
    public DealTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardView itemView = (CardView) layoutInflater.inflate(R.layout.deal_type, parent, false);
        return new DealTypeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final DealTypeViewHolder holder, final int i) {
        holder.dealImg.setImageResource(mTypes.get(i).getTypeImg());
        holder.dealName.setText(mTypes.get(i).getTypeName());
        holder.checkBox.setClickable(false);

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGroupSelected.onGroupSelected(mTypes.get(i));
                holder.checkBox.setChecked(true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTypes.size();
    }

    public interface GroupSelected {
        public void onGroupSelected(DealType type);
    }

    public class DealTypeViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView dealName;
        ImageView dealImg;
        CheckBox checkBox;

        DealTypeViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.dealTypeCardView);
            dealName = (TextView) itemView.findViewById(R.id.dealTypeName);
            dealImg = (ImageView) itemView.findViewById(R.id.dealTypeImg);
            checkBox = (CheckBox) itemView.findViewById(R.id.dealTypeCheckBox);
        }
    }
}
