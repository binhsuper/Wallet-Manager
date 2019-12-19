package com.bootcamp.walletmanager.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.bootcamp.walletmanager.Adapter.WalletListAdapter;
import com.bootcamp.walletmanager.Application.LoggedAccount;
import com.bootcamp.walletmanager.Datamodel.Wallets;
import com.bootcamp.walletmanager.R;

public class WalletList extends AppCompatActivity implements WalletListAdapter.WalletSelected {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_list);
        configureToolbar();
        configureRecyclerView();

    }

    private void configureToolbar() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        Button closeBtn = (Button) findViewById(R.id.walletListCloseBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void configureRecyclerView() {
        RecyclerView walletsView = findViewById(R.id.walletListRV);
        walletsView.setHasFixedSize(true);
        LinearLayoutManager walletLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        walletsView.setLayoutManager(walletLayout);
        WalletListAdapter walletListAdapter = new WalletListAdapter(this , LoggedAccount.getCurrentLogin().getUserWallets(), this);
        walletsView.setAdapter(walletListAdapter);
    }

    @Override
    public void onWalletSelected(Wallets wallet) {
        Intent intent = new Intent();
        intent.putExtra("walletName", wallet.getName());

        setResult(RESULT_OK, intent);
        finish();
    }
}
