package com.bootcamp.walletmanager.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bootcamp.walletmanager.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateDeal extends CustomActivity {
    private String TAG = "DealInput";
    private EditText moneyInput, groupInput, dateInput, walletInput, noteInput;
    private String dealKind;
    private ImageView groupImg;
    final Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_deal);
        configureToolbar();
        moneyInput = (EditText) findViewById(R.id.editDealAmount);
        groupInput = (EditText) findViewById(R.id.editDealType);
        dateInput = (EditText) findViewById(R.id.editDealDate);
        walletInput = (EditText) findViewById(R.id.editDealWallet);
        noteInput = (EditText) findViewById(R.id.editDealNotes);
        groupImg = (ImageView) findViewById(R.id.groupImg);

        ConstraintLayout rootLayout = (ConstraintLayout) findViewById(R.id.createDealLayout);
        rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
            }
        });
        configureSelectionInput();

    }

    private void configureToolbar() {
        Button closeBtn = (Button) findViewById(R.id.dealCloseBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final Intent intent = new Intent(this, Dasboard.class);
        Button checkBtn = (Button) findViewById(R.id.dealCheckBtn);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput()) {
                    String money = moneyInput.getText().toString().replaceAll("\\D+","");
                    String currentTime = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                    String group = groupInput.getText().toString();
                    String wallet = walletInput.getText().toString();
                    String notes = noteInput.getText().toString();

                    createNewRecord(money, group, currentTime, wallet, notes, dealKind);
                    updateWallet(wallet, dealKind, Integer.parseInt(money));
                    finish();
                    startActivity(intent);
                }
                else {

                }
            }
        });
    }

    private boolean checkInput() {
        if (moneyInput.getText().length() == 0 || groupInput.length() == 0 || dateInput.length() == 0 || walletInput.length() == 0) {
            Toast toast = Toast.makeText(this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        else {
            return true;
        }
    }


    private void configureSelectionInput() {
        groupInput.setInputType(InputType.TYPE_NULL);
        dateInput.setInputType(InputType.TYPE_NULL);
        walletInput.setInputType(InputType.TYPE_NULL);

        final Intent dealType = new Intent(this, DealTypes.class);
        final Intent walletPick = new Intent(this, WalletList.class);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        groupInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + "group selected");
                startActivityForResult(dealType, 0);
            }
        });

        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + "date selected");
                new DatePickerDialog(CreateDeal.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        walletInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(walletPick, 1);
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateInput.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if(resultCode == RESULT_OK) {
                String value1 = data.getStringExtra("groupName");
                String value2 = data.getStringExtra("groupKind");
                Log.d(TAG, "return value: " + value1 + value2);

                groupImg.setImageResource(data.getIntExtra("groupImg", 0));
                groupImg.clearColorFilter();
                groupInput.setText(value1);
                dealKind = value2;
            }
        }
        else if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String value1 = data.getStringExtra("walletName");
                walletInput.setText(value1);
            }
        }
    }
}
