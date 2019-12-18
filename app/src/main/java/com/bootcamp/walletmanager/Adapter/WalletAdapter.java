package com.bootcamp.walletmanager.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bootcamp.walletmanager.Datamodel.WalletData;
import com.bootcamp.walletmanager.Datamodel.Wallets;
import com.bootcamp.walletmanager.R;

import java.util.List;
import java.util.Random;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.WalletViewHolder> {
    List<Wallets> mWallets;
    Context mContext;


    public WalletAdapter(Context context, List<Wallets> wallets) {
        mWallets = wallets;
        mContext = context;
    }

    @NonNull
    @Override
    public WalletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardView itemView = (CardView) layoutInflater.inflate(R.layout.wallets_layout, parent, false);
        return new WalletViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletViewHolder holder, final int position) {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(200), rnd.nextInt(200), rnd.nextInt(200));
        holder.background.setBackgroundColor(color);

        holder.walletName.setText(mWallets.get(position).getName());
        holder.money.setText(mWallets.get(position).getAmount() + ".00 đ");
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                int walletType = mWallets.get(position).getWalletType();
                if (walletType == WalletData.WalletType.NORMAL.getValue()) {
                    toast = Toast.makeText(mContext, "Ví thường", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if (walletType == WalletData.WalletType.BANK_ACCOUNT.getValue()) {
                    toast = Toast.makeText(mContext, "bank account", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if (walletType == WalletData.WalletType.CREDIT_CARD.getValue()) {
                    toast = Toast.makeText(mContext, "credit card", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if (walletType == WalletData.WalletType.SAVINGS.getValue()) {
                    toast = Toast.makeText(mContext, "saving account", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWallets.size();
    }

    public class WalletViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView walletName;
        TextView money;
        LinearLayout background;

        WalletViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.wallet);
            walletName = (TextView) itemView.findViewById(R.id.walletName);
            money = (TextView) itemView.findViewById(R.id.walletMoney);
            background = (LinearLayout) itemView.findViewById(R.id.walletBackground);
        }
    }
}
