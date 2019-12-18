package com.bootcamp.walletmanager.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bootcamp.walletmanager.R;

public class Login extends CustomActivity {

    String TAG = "login";
    EditText emailInput;
    EditText passwordInput;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ConstraintLayout rootLayout = (ConstraintLayout) findViewById(R.id.loginLayout);
        rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
            }
        });
        emailInput = (EditText) findViewById(R.id.email_input);
        passwordInput = (EditText) findViewById(R.id.password_input);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        openRegisterPage();
    }

    private void openRegisterPage() {
        Button registerBtn = (Button) findViewById(R.id.registerBtn);
        final Intent intent = new Intent(this, Register.class);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent, 0);
            }
        });
    }

    public void login() {
        loginBtn.setEnabled(false);
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        // TODO: Implement authentication logic.
        if (!validate()) {
            onLoginFailed();
            return;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                setResult(RESULT_OK, null);
                this.finish();
            }
        }
    }

    public void onLoginSuccess() {
        loginBtn.setEnabled(true);
        Toast.makeText(getBaseContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
        setResult(RESULT_OK, null);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Sai email hoặc mật khẩu", Toast.LENGTH_LONG).show();
        loginBtn.setEnabled(true);
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("Email không hợp lệ");
            valid = false;
        } else {
            emailInput.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            passwordInput.setError("mật khẩu phải ít nhất 4 kí tự");
            valid = false;
        } else {
            passwordInput.setError(null);
        }

        return valid;
    }
}
