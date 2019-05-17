package com.inveitix.a04lifecycleandevents;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {


    private static final int RQ_TAKE_PICTURE = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText edtEmail = findViewById(R.id.edt_email);
        initListeners(edtEmail);
    }

    private void initListeners(final EditText edtEmail) {
        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("USER_NAME", edtEmail.getText().toString());
                setResult(RESULT_OK, data);
                finish();
            }
        });
        findViewById(R.id.btn_contact_us).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialNumber();
            }
        });
        findViewById(R.id.btn_take_picture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCamera();
            }
        });
    }

    private void startCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, RQ_TAKE_PICTURE);
    }

    private void dialNumber() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:0123456789"));
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RQ_TAKE_PICTURE && resultCode == RESULT_OK) {
            ImageView imgAvatar = findViewById(R.id.img_avatar);
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgAvatar.setImageBitmap(imageBitmap);
        }
    }

    private void startEmailClient(String data) {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, data);
        sendIntent.setType("text/plain");

        if ( sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }
}
