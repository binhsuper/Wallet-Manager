package com.bootcamp.walletmanager.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bootcamp.walletmanager.Application.LoggedAccount;
import com.bootcamp.walletmanager.Datamodel.Records;
import com.bootcamp.walletmanager.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.realm.RealmList;

public class CreateDeal extends CustomActivity {
    private String TAG = "DealInput";
    private EditText moneyInput, groupInput, dateInput, walletInput, noteInput;
    private String dealKind;
    private ImageView groupImg;
    private TextView title;
    final Calendar myCalendar = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy", Locale.US);

    Records selectedRecord;
    String viewMode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_deal);
        moneyInput = (EditText) findViewById(R.id.editDealAmount);
        groupInput = (EditText) findViewById(R.id.editDealType);
        dateInput = (EditText) findViewById(R.id.editDealDate);
        walletInput = (EditText) findViewById(R.id.editDealWallet);
        noteInput = (EditText) findViewById(R.id.editDealNotes);
        groupImg = (ImageView) findViewById(R.id.groupImg);
        title = (TextView) findViewById(R.id.title);
        configureToolbar();


        //TODO: Hide keyboard
        ConstraintLayout rootLayout = (ConstraintLayout) findViewById(R.id.createDealLayout);
        rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
            }
        });
        configureSelectionInput();

    }

    //TODO: Set up toolbar buttons, title.

    private void configureToolbar() {
        Button closeBtn = (Button) findViewById(R.id.dealCloseBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), Dasboard.class));
            }
        });
        final Button checkBtn = (Button) findViewById(R.id.dealCheckBtn);
        Button editBtn = (Button) findViewById(R.id.edit);
        final Button deleteBtn = (Button) findViewById(R.id.delete);

        //TODO: Open view or edit, delete record form.
        viewMode = getIntent().getStringExtra("ViewState");
        if (viewMode.equals("VIEW")) {
            checkBtn.setVisibility(View.GONE);
            title.setText("");
            showRecordDetails();
        }
        //TODO: Open create record form.
        else {
            editBtn.setVisibility(View.GONE);
            deleteBtn.setVisibility(View.GONE);
        }

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewMode.equals("VIEW")) {
                    lockEdit();
                    implementDealInput();
                    v.setVisibility(View.GONE);
                }
                else {
                    if (checkInput()) {
                        implementDealInput();
                    }
                    else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unlockEdit();
                checkBtn.setVisibility(View.VISIBLE);
                moneyInput.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(moneyInput, InputMethodManager.SHOW_IMPLICIT);
                moneyInput.setSelection(moneyInput.getText().length());
            }
        });

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Bạn có muốn xoá giao dịch này!")
                        .setCancelable(false)
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (selectedRecord.getKind().equals("spending"))
                                    updateWallet(selectedRecord.getFromWallet(), "income", Integer.parseInt(selectedRecord.getAmount()));
                                else
                                    updateWallet(selectedRecord.getFromWallet(), "spending", Integer.parseInt(selectedRecord.getAmount()));

                                deleteRecord(selectedRecord.getRecordID());
                                Toast.makeText(getApplicationContext(), "Xoá giao dịch thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), Dasboard.class));
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    //TODO: Get the record details and display.

    private void showRecordDetails() {
        String id = getIntent().getStringExtra("RecordId");
        for (int i = 0; i < LoggedAccount.getCurrentLogin().getUserRecords().size(); i++) {
            Records record = LoggedAccount.getCurrentLogin().getUserRecords().get(i);
            if (record.getRecordID().equals(id)) {
                Log.d(TAG, "showRecordDetails: " + id);
                lockEdit();
                selectedRecord = record;
                moneyInput.setText("VND " + record.getAmount());
                groupInput.setText(record.getType());
                dateInput.setText(dateFormat.format(record.getDate()));
                walletInput.setText(record.getFromWallet());
                noteInput.setText(record.getNotes());
                dealKind = record.getKind();
                switch (record.getType()) {
                    case "Quà tặng" :
                        groupImg.setImageResource(R.drawable.type_gift);
                        break;
                    case "Lương" :
                        groupImg.setImageResource(R.drawable.type_salary);
                        break;
                    case "Bán đồ" :
                        groupImg.setImageResource(R.drawable.type_sale);
                        break;
                    case "Thưởng" :
                        groupImg.setImageResource(R.drawable.type_reward);
                        break;
                    case "Khác" :
                        groupImg.setImageResource(R.drawable.type_other);
                        break;
                    case "Thực phẩm" :
                        groupImg.setImageResource(R.drawable.type_food);
                        break;
                    case "Tạp hoá" :
                        groupImg.setImageResource(R.drawable.type_groceries);
                        break;
                    case "Sức khoẻ" :
                        groupImg.setImageResource(R.drawable.type_health);
                        break;
                    case "Di chuyển" :
                        groupImg.setImageResource(R.drawable.type_transport);
                        break;
                    case "Hoá đơn" :
                        groupImg.setImageResource(R.drawable.type_tax);
                        break;
                }
            }
        }
    }

    private void implementDealInput() {
        final Intent intent = new Intent(this, Dasboard.class);
        String money = moneyInput.getText().toString().replaceAll("\\D+","");
        Date dateCreated;
        String group = groupInput.getText().toString();
        String wallet = walletInput.getText().toString();
        String notes = noteInput.getText().toString();

        if (dateInput.getText().toString().equals("Hôm nay")){
            dateCreated = Calendar.getInstance().getTime();
        }
        else {
            dateCreated = myCalendar.getTime();
        }

        //TODO: Input when user edit record
        if (viewMode.equals("VIEW")) {
            int diffAmount = Integer.parseInt(money) - Integer.parseInt(selectedRecord.getAmount());
            Log.d(TAG, "implementDealInput: " + selectedRecord.getAmount() + " " + Integer.parseInt(money));
            updateWallet(wallet, dealKind, diffAmount);

            updateRecord(selectedRecord.getRecordID(), money, group, dateCreated, notes, dealKind);

            Toast.makeText(getApplicationContext(), "Sửa giao dịch thành công", Toast.LENGTH_SHORT).show();
        }

        //TODO: Input when user create record
        else {
            createNewRecord(money, group, dateCreated, wallet, notes, dealKind);
            updateWallet(wallet, dealKind, Integer.parseInt(money));
            finish();
            startActivity(intent);
        }
    }

    private void lockEdit() {
        moneyInput.setEnabled(false);
        groupInput.setEnabled(false);
        dateInput.setEnabled(false);
        walletInput.setEnabled(false);
        noteInput.setEnabled(false);
    }

    private void unlockEdit() {
        moneyInput.setEnabled(true);
        groupInput.setEnabled(true);
        dateInput.setEnabled(true);
        noteInput.setEnabled(true);
    }

    private boolean checkInput() {
        if (moneyInput.getText().length() == 0 || groupInput.length() == 0 || dateInput.length() == 0 || walletInput.length() == 0) {
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
                if (viewMode.equals("VIEW")) {
                    dealType.putExtra("STATE", "EDIT");
                    dealType.putExtra("DEAL_KIND", selectedRecord.getKind());
                }
                else {
                    dealType.putExtra("STATE", "CREATE");
                }
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
        dateInput.setText(dateFormat.format(myCalendar.getTime()));
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
