package com.inveitix.a04lifecycleandevents;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int RQ_REG_ACT = 11;
    private EditText edtEmail;
    private Button btnLogin;
    private View txtCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initListeners();
    }

    private void initViews() {
        btnLogin = findViewById(R.id.btn_login);
        edtEmail = findViewById(R.id.edt_email);
        txtCreateAccount = findViewById(R.id.txt_create_account);
    }

    private void initListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainScreen();
            }
        });
        txtCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegisterScreen();
            }
        });
    }

    private void startRegisterScreen() {
        Intent regScreenIntent = new Intent(this, RegisterActivity.class);
        startActivityForResult(regScreenIntent, RQ_REG_ACT);
    }

    private void startMainScreen() {
        String email = edtEmail.getText().toString();
        Intent mainActIntent = new Intent(this, MainActivity.class);
        mainActIntent.putExtra("USER_EMAIL", email);
        mainActIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mainActIntent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RQ_REG_ACT) {
            if(resultCode == RESULT_OK) {
                String email = data.getStringExtra("USER_NAME");
                edtEmail.setText(email);
            }
        }
    }
}
