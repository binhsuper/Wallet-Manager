package com.bootcamp.walletmanager.Activities;

import android.app.ProgressDialog;
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

public class Register extends CustomActivity {

    String TAG = "register";
    EditText usernameInput;
    EditText emailInput;
    EditText passwordInput ;
    EditText confirmPasswordInput;
    Button registerBtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameInput = (EditText) findViewById(R.id.register_username_input);
        emailInput = (EditText) findViewById(R.id.register_email_input);
        passwordInput = (EditText) findViewById(R.id.register_password_input);
        confirmPasswordInput = (EditText) findViewById(R.id.password_confirm_input);
        registerBtn = (Button) findViewById(R.id.register);

        ConstraintLayout rootLayout = (ConstraintLayout) findViewById(R.id.registerLayout);
        rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        registerBtn.setEnabled(false);

        String name = usernameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        // TODO: Create new user account.
        if (!validate()) {
            onSignupFailed();
            return;
        }

    }


    public void onSignupSuccess() {
        registerBtn.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Đăng ký không thành công", Toast.LENGTH_LONG).show();

        registerBtn.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = usernameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String reEnterPassword = confirmPasswordInput.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            usernameInput.setError("Tên phải tối thiểu 3 chữ cái");
            valid = false;
        } else {
            usernameInput.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("Email không hợp lệ");
            valid = false;
        } else {
            emailInput.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            passwordInput.setError("Mật khẩu phải tối thiểu 4 chữ cái");
            valid = false;
        } else {
            passwordInput.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            confirmPasswordInput.setError("Mật khẩu chưa khớp");
            valid = false;
        } else {
            confirmPasswordInput.setError(null);
        }

        return valid;
    }
}
