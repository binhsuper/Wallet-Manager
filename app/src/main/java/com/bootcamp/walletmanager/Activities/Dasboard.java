package com.bootcamp.walletmanager.Activities;


import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.bootcamp.walletmanager.Adapter.RecordAdapter;
import com.bootcamp.walletmanager.Adapter.WalletAdapter;
import com.bootcamp.walletmanager.Application.LoggedAccount;
import com.bootcamp.walletmanager.Datamodel.Account;
import com.bootcamp.walletmanager.Datamodel.SideBar;
import com.bootcamp.walletmanager.R;

import io.realm.Realm;
import io.realm.RealmResults;

public class Dasboard extends CustomActivity implements SideBar.MenuItemSelected, RecordAdapter.OnClickRecord {

    public int LOGIN_SUCCESSFULLY = 0;

    // TODO: create data source.
    SideBar sideBar;

    String TAG = "useraccount";
    Boolean isFABOpen = false;
    FloatingActionButton fab;
    LinearLayout fab1, fab2;
    TextView addWallet, addDeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openLoginPage();
        configureActionBar();

    }

    // TODO: implement user login/register.
    private void openLoginPage() {
        Intent intent = new Intent(this, Login.class);
        startActivityForResult(intent, LOGIN_SUCCESSFULLY);
    }

    // TODO: User login/register succesfully.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_SUCCESSFULLY) {
            if(resultCode == RESULT_OK) {
                configureActionBar();
                configureRecyclerViews();
                countBalance();
            }
        }
    }
    private void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
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

        int marginRight = fab.getMeasuredWidth() / 8;
        setMargins(fab1, 0, 0, marginRight,0);
        setMargins(fab2, 0, 0, marginRight,0);

        final Intent dealIntent = new Intent(this, CreateDeal.class);

        // TODO: create deal button onclick.
        addDealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + "creating deal");
                if (!findExistedWallet()) {
                    Toast.makeText(getBaseContext(), "Bạn phải tạo ví trước", Toast.LENGTH_LONG).show();
                }
                else {
                    dealIntent.putExtra("ViewState", "CREATE");
                    startActivity(dealIntent);
                }
            }
        });

        // TODO: create wallet button onclick.
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
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Button notificationBtn = (Button) findViewById(R.id.notificationBtn);

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + "open notification");
            }
        });

        sideBar = new SideBar(this, toolbar, LoggedAccount.getCurrentLogin().getName(), LoggedAccount.getCurrentLogin().getEmail(), this);
    }

    private void countBalance() {
        // TODO: Count the amount of all wallets.

        TextView balance = findViewById(R.id.accountBalance);
        int value = 0;
        for (int i = 0; i < LoggedAccount.getCurrentLogin().getUserWallets().size(); i++) {
            value += LoggedAccount.getCurrentLogin().getUserWallets().get(i).getAmount();
        }
        animateTextView(0, value, balance);

    }

    public void animateTextView(int initialValue, int finalValue, final TextView textview) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(initialValue, finalValue);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                textview.setText(valueAnimator.getAnimatedValue().toString() + ".00 đ");
            }
        });
        valueAnimator.start();

    }

    private void configureRecyclerViews() {
        RecyclerView walletsView = findViewById(R.id.wallets);
        walletsView.setHasFixedSize(true);
        LinearLayoutManager walletLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        walletsView.setLayoutManager(walletLayout);
        WalletAdapter walletAdapter = new WalletAdapter(this , LoggedAccount.getCurrentLogin().getUserWallets());
        walletsView.setAdapter(walletAdapter);

        RecyclerView records = findViewById(R.id.records);
        records.setHasFixedSize(true);
        LinearLayoutManager recordLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        records.setLayoutManager(recordLayout);
        RecordAdapter recordAdapter = new RecordAdapter(getMonthlyRecords(), this);
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

    //TODO: Side bar item onclick functions


    @Override
    public void onHistorySelected() {
        startActivity(new Intent(this, RecordHistory.class));
        sideBar.mDrawer.closeDrawer();
    }

    @Override
    public void onChartSelected() {
        startActivity(new Intent(this, Chart.class));
        sideBar.mDrawer.closeDrawer();
    }

    @Override
    public void onSavingSelected() {

    }

    @Override
    public void onDebtSelected() {

    }

    @Override
    public void onLogoutSelected() {
        logOff();
        openLoginPage();
        sideBar.mDrawer.closeDrawer();
    }

    public void logOff() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Account> persons = realm.where(Account.class).equalTo("logged", true).findAll();
                persons.setBoolean("logged", false);
            }
        });
    }

    //TODO: Monthly records onclick functions


    @Override
    public void onRecordSelected(String id) {
        Intent intent = new Intent(this, CreateDeal.class);
        intent.putExtra("ViewState", "VIEW");
        intent.putExtra("RecordId", id);
        startActivity(intent);
    }
}
