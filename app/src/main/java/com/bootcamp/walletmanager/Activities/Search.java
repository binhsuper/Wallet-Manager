package com.bootcamp.walletmanager.Activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.bootcamp.walletmanager.Datamodel.DealType;
import com.bootcamp.walletmanager.R;

public class Search extends CustomActivity {

    Button searchBtn, backBtn;
    EditText groupInput, walletInput, amountInput, maxAmountInput, noteInput;
    Spinner kindSpinner, rangeSpinner;
    LinearLayout rangeLayout, groupLayout;

    public enum RangeType
    {
        ALL(0),
        LOWER(1),
        HIGHER(2),
        EQUALS(3),
        RANGES(4);

        RangeType (int i)
        {
            this.type = i;
        }

        private int type;

        public int getInt()
        {
            return type;
        }
    }

    public enum Kinds
    {
        ALL(0),
        INCOME(1),
        SPENDING(2);

        Kinds (int i)
        {
            this.type = i;
        }

        private int type;

        public int getInt()
        {
            return type;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        implementToolbar();
        implementUserInput();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if(resultCode == RESULT_OK) {
                    String value1 = data.getStringExtra("walletName");
                    walletInput.setText(value1);
                }
                break;
            case 1:
                if(resultCode == RESULT_OK) {
                    String value2 = data.getStringExtra("groupName");
                    groupInput.setText(value2);
                }
                break;
        }
    }

    private void implementToolbar() {
        backBtn = (Button) findViewById(R.id.back);
        LinearLayout layout = findViewById(R.id.root);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchBtn = (Button) findViewById(R.id.search);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpMoneySearch();
            }
        });
    }

    private void setUpMoneySearch() {
        final Intent intent = new Intent(this, SearchResult.class);
        intent.putExtra("amountType", rangeSpinner.getSelectedItemPosition());
        intent.putExtra("wallet", walletInput.getText().toString());
        intent.putExtra("note", noteInput.getText().toString());
        intent.putExtra("kind", kindSpinner.getSelectedItemPosition());

        if (rangeSpinner.getSelectedItemPosition() == RangeType.RANGES.getInt()){
            if (amountInput.length() != 0 && maxAmountInput.length() != 0) {
                if (Integer.parseInt(getAmountFromInput(amountInput)) < Integer.parseInt(getAmountFromInput(maxAmountInput))) {
                    intent.putExtra("minAmount", getAmountFromInput(amountInput));
                    intent.putExtra("maxAmount", getAmountFromInput(maxAmountInput));
                }
                else {
                    Toast.makeText(getApplicationContext(), "Số tiền lớn nhất phải lớn hơn số tiền khởi điểm", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Bạn cần nhập đủ khoảng tiền", Toast.LENGTH_LONG).show();
                return;
            }

        }
        else {
            if (rangeSpinner.getSelectedItemPosition() != RangeType.ALL.getInt()) {
                intent.putExtra("minAmount", getAmountFromInput(amountInput));
            }
        }

        if (kindSpinner.getSelectedItemPosition() != Kinds.ALL.getInt()) {
            intent.putExtra("group", groupInput.getText().toString());
        }

        startActivity(intent);
    }

    private String getAmountFromInput(EditText input) {
        String money = input.getText().toString().replaceAll("\\D+","");
        return money;
    }

    private void implementUserInput() {

        //TODO: Implement Edittext

        groupInput = (EditText) findViewById(R.id.group_input);
        walletInput = (EditText) findViewById(R.id.wallet_input);
        amountInput = (EditText) findViewById(R.id.record_amount_input);
        maxAmountInput = (EditText) findViewById(R.id.maxAmount);
        noteInput = (EditText) findViewById(R.id.note_input);


        walletInput.setInputType(InputType.TYPE_NULL);
        groupInput.setInputType(InputType.TYPE_NULL);

        final Intent walletPick = new Intent(this, WalletList.class);
        final Intent groupPick = new Intent(this, DealTypes.class);

        walletInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                walletPick.putExtra("STATE", "SEARCH");
                startActivityForResult(walletPick, 0);
            }
        });


        //TODO: Implement spinner
        kindSpinner = (Spinner) findViewById(R.id.recordKind);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.recordKind, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kindSpinner.setAdapter(adapter);

        rangeSpinner = (Spinner) findViewById(R.id.rangeSpinner);
        ArrayAdapter<CharSequence> rangeAdapter = ArrayAdapter.createFromResource(this,
                R.array.amountRange, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rangeSpinner.setAdapter(rangeAdapter);

        rangeLayout = findViewById(R.id.rangeLayout);
        groupLayout = findViewById(R.id.groupLayout);

        groupInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupPick.putExtra("STATE", "EDIT");
                if (kindSpinner.getSelectedItem().toString().equals("Khoản thu")) {
                    groupPick.putExtra("DEAL_KIND", "income");
                }
                else {
                    groupPick.putExtra("DEAL_KIND", "spending");
                }
                startActivityForResult(groupPick, 1);
            }
        });

        rangeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 4) {
                    amountInput.setVisibility(View.VISIBLE);
                    rangeLayout.setVisibility(View.VISIBLE);
                }
                else if (position == 0){
                    amountInput.setVisibility(View.GONE);
                    rangeLayout.setVisibility(View.GONE);
                }
                else {
                    rangeLayout.setVisibility(View.GONE);
                    amountInput.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        kindSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                groupInput.setText("Tất cả");
                if (position == 0) {
                    groupLayout.setVisibility(View.GONE);
                }
                else {
                    groupLayout.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
