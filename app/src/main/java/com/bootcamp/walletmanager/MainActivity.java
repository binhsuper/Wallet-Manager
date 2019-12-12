package com.bootcamp.walletmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Wallets> wallet;
    List<Records> mRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        configureRecyclerViews();
        countBalance();

        configureActionBar();


    }

    private void configureActionBar() {
        Button menuBtn = findViewById(R.id.menuBtn);
        Button notificationBtn = findViewById(R.id.notificationBtn);

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void openMenu() {
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Home");
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("Settings");

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        new SecondaryDrawerItem().withName("setting")
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        return true;
                    }
                })
                .build();

        result.openDrawer();
    }

    private void countBalance() {
        TextView balance = findViewById(R.id.accountBalance);
        int value = 0;
        for (int i = 0; i < wallet.size(); i++) {
            value += wallet.get(i).value;
        }
        balance.setText("$" + Integer.toString(value) + ".00");
    }

    private void configureRecyclerViews() {
        initializeData();

        RecyclerView walletsView = findViewById(R.id.wallets);
        walletsView.setHasFixedSize(true);
        LinearLayoutManager walletLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        walletsView.setLayoutManager(walletLayout);
        WalletAdapter walletAdapter = new WalletAdapter(this ,wallet);
        walletsView.setAdapter(walletAdapter);

        RecyclerView records = findViewById(R.id.records);
        records.setHasFixedSize(true);
        LinearLayoutManager recordLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        records.setLayoutManager(recordLayout);
        RecordAdapter recordAdapter = new RecordAdapter(this ,mRecords);
        records.setAdapter(recordAdapter);
    }

    private void initializeData(){
        wallet = new ArrayList<>();
        wallet.add(new Wallets("Bank account", "$40000.00", 40000));
        wallet.add(new Wallets("Cash", "$200000.00", 200000));
        wallet.add(new Wallets("Saving", "$300000.00", 300000));

        mRecords = new ArrayList<>();
        mRecords.add(new Records("Groceries", "Cash", "11/12/2019", "$20000.00",  R.drawable.type_groceries));
        mRecords.add(new Records("Transport", "Bank account", "10/12/2019", "$50000.00",  R.drawable.type_transport));
        mRecords.add(new Records("Food", "Bank account", "6/12/2019", "$40000.00",  R.drawable.type_food));
    }
}
