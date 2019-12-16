package com.bootcamp.walletmanager.Activities;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.bootcamp.walletmanager.Adapter.RecordAdapter;
import com.bootcamp.walletmanager.Adapter.WalletAdapter;
import com.bootcamp.walletmanager.Datamodel.RecordData;
import com.bootcamp.walletmanager.Datamodel.SideBar;
import com.bootcamp.walletmanager.Datamodel.WalletData;
import com.bootcamp.walletmanager.R;

public class Dasboard extends AppCompatActivity {

    WalletData walletData = new WalletData();
    RecordData recordData = new RecordData();

    String TAG = "Main";
    Boolean isFABOpen = false;
    FloatingActionButton fab;
    LinearLayout fab1, fab2;
    TextView addWallet, addDeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        configureRecyclerViews();
        countBalance();

        configureActionBar();
        configureFloatingBtn();


    }

    private void configureFloatingBtn() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (LinearLayout) findViewById(R.id.fab1);
        fab2 = (LinearLayout) findViewById(R.id.fab2);
        addWallet = (TextView) findViewById(R.id.addWalletText);
        addDeal = (TextView) findViewById(R.id.addDealText);

        FloatingActionButton addDealBtn = (FloatingActionButton) findViewById(R.id.addDealBtn),
        addWalletBtn = (FloatingActionButton) findViewById(R.id.addWalletBtn);

        final Intent dealIntent = new Intent(this, CreateDeal.class);
        addDealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + "creating deal");
                startActivity(dealIntent);
            }
        });

        final Intent walletIntent = new Intent(this, CreateWallet.class);
        addWalletBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + "creating wallets");
                startActivity(walletIntent);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                    isFABOpen = true;
                }else{
                    closeFABMenu();
                    isFABOpen = false;
                }
            }
        });
    }

    private void rotateBtn(int from, int to) {
        final AnimationSet animSet = new AnimationSet(true);
        animSet.setInterpolator(new DecelerateInterpolator());
        animSet.setFillAfter(true);
        animSet.setFillEnabled(true);

        final RotateAnimation animRotate = new RotateAnimation(from, to,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        animRotate.setDuration(100);
        animRotate.setFillAfter(true);
        animSet.addAnimation(animRotate);
        fab.startAnimation(animSet);
    }

    private void showFABMenu(){
        addWallet.setVisibility(View.VISIBLE);
        addDeal.setVisibility(View.VISIBLE);
        isFABOpen=true;
        rotateBtn(0, 45);
        int distance = fab.getWidth() * 4/3;
        fab1.animate().translationY(-(distance));
        fab2.animate().translationY(-(distance * 2));
    }

    private void closeFABMenu(){
        addWallet.setVisibility(View.INVISIBLE);
        addDeal.setVisibility(View.INVISIBLE);
        isFABOpen=false;
        rotateBtn(45, 0);
        fab1.animate().translationY(0);
        fab2.animate().translationY(0);
    }

    private void configureActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.text));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Button notificationBtn = (Button) findViewById(R.id.notificationBtn);

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + "open notification");
            }
        });

        SideBar sideBar = new SideBar(this, toolbar);
    }

    private void countBalance() {
        TextView balance = findViewById(R.id.accountBalance);
        int value = 0;
        for (int i = 0; i < walletData.getWallet().size(); i++) {
            value += walletData.getWallet().get(i).getAmount();
        }
        balance.setText(Integer.toString(value) + ".00 đ");
    }

    private void configureRecyclerViews() {

        RecyclerView walletsView = findViewById(R.id.wallets);
        walletsView.setHasFixedSize(true);
        LinearLayoutManager walletLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        walletsView.setLayoutManager(walletLayout);
        WalletAdapter walletAdapter = new WalletAdapter(this ,walletData.getWallet());
        walletsView.setAdapter(walletAdapter);

        RecyclerView records = findViewById(R.id.records);
        records.setHasFixedSize(true);
        LinearLayoutManager recordLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        records.setLayoutManager(recordLayout);
        RecordAdapter recordAdapter = new RecordAdapter(this , recordData.getRecords());
        records.setAdapter(recordAdapter);
    }

    @Override
    public void onBackPressed() {
        if(!isFABOpen){
            super.onBackPressed();
        }else{
            closeFABMenu();
        }
    }
}
