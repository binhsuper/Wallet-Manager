package com.bootcamp.walletmanager.Activities;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bootcamp.walletmanager.R;

public class CreateWallet extends AppCompatActivity {

    private String TAG = "UserInput";
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wallet);
        configureToolbar();
        configureWalletTypeInput();
    }

    private void configureToolbar() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        Button closeBtn = (Button) findViewById(R.id.walletCloseBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button checkBtn = (Button) findViewById(R.id.walletCheckBtn);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] userInput = getTextInput();
                if (userInput != null) {
                    for (int i = 0; i < userInput.length; i++) {
                        Log.d(TAG, "input: " + userInput[i] + spinner.getSelectedItem().toString());
                        finish();
                    }
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Tên ví phải chứa ít nhất 6 kí tự", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
    }

    private void configureWalletTypeInput() {
        final ImageView walletType = (ImageView) findViewById(R.id.wallet_type_picker);
        spinner = (Spinner) findViewById(R.id.type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.wallet_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        walletType.setImageResource(R.drawable.wallet_wallet_icon);
                        break;
                    case 1:
                        walletType.setImageResource(R.drawable.wallet_bank);
                        break;
                    case 2:
                        walletType.setImageResource(R.drawable.wallet_credit_card);
                        break;
                    case 3:
                        walletType.setImageResource(R.drawable.wallet_saving);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private String[] getTextInput() {
        EditText walletName = (EditText) findViewById(R.id.editWalletName),
        walletAmount = (EditText) findViewById(R.id.editWalletAmount);

        String name = walletName.getText().toString(),
                amount = walletAmount.getText().toString().replaceAll("\\D+","");

        String[] info = {name, amount};

        if (name.length() < 6) {
            return null;
        }
        else
            return info;

    }
}