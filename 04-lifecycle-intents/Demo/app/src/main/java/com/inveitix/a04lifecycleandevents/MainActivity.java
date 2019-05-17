package com.inveitix.a04lifecycleandevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String email = getIntent().getStringExtra("USER_EMAIL");
        TextView txtEmail = findViewById(R.id.txt_email);
        txtEmail.setText(email);
    }
}
