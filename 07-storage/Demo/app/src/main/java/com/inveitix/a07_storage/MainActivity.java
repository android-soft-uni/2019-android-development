package com.inveitix.a07_storage;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.inveitix.a07_storage.data.local.AppDatabase;
import com.inveitix.a07_storage.data.local.DatabaseInstance;
import com.inveitix.a07_storage.data.local.User;
import com.inveitix.a07_storage.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final int REQUEST_CODE_CAMERA = 13;
    private ActivityMainBinding binding;
    public static final String FILENAME = "Settings.txt";
    private String currentPhotoPath;
    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.btnPhoto.setOnClickListener(v -> takePhoto());
        binding.btnWriteFile.setOnClickListener(v -> makeTextFile());
        binding.btnReadFile.setOnClickListener(v -> readTextFile());
        binding.recViewPhotos.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ImageAdapter();
        adapter.addClickListener(data -> Toast.makeText(MainActivity.this, "Img:" + data.getImagePath(), Toast.LENGTH_SHORT).show());
        binding.recViewPhotos.setAdapter(adapter);
//        setupDatabase();
    }

    private void setupDatabase() {
        User userEntity = new User();
        userEntity.setFirstName("Teo");
        userEntity.setLastName("Teo");

        DatabaseInstance dbi = DatabaseInstance.getInstance(this);
        dbi.insertSingleAsync(userEntity);

        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setFirstName("Teo" + i);
            user.setLastName("Teo");
            dbi.insertSingleAsync(user);
        }

        dbi.getAll(new DatabaseInstance.DatabaseListener<List<User>>() {
            @Override
            public void onDataReceived(List<User> data) {
                for (User u : data) {
                    Log.e(TAG, u.toString());
                }
            }
        });
    }

    private void readTextFile() {
        String[] strings = fileList();
        for (String filen : strings) {
            Log.e(TAG, "File:" + filen );
        }
        try {
            InputStream instream = openFileInput(FILENAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
            String singleLine = null;
            do {
                singleLine = reader.readLine();
                Log.e(TAG, "Line: " + singleLine );
            }
            while (singleLine != null);
        } catch (Exception e) {
            Toast.makeText(this, "Error reading", Toast.LENGTH_LONG).show();
        }
    }

    private void makeTextFile() {
        String text = binding.edtFile.getText().toString();
        try {
            PrintWriter outputStream = new PrintWriter(openFileOutput(FILENAME, Context.MODE_PRIVATE));
            outputStream.print(text);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error writing", Toast.LENGTH_LONG).show();
        }

    }

    private void takePhoto() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    11);
            return;
        }

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.e("TAG", "Image fail failure");
                return;
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(cameraIntent, REQUEST_CODE_CAMERA);

                // Save a file: path for use with ACTION_VIEW intents
                currentPhotoPath = photoFile.getAbsolutePath();
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = new File(storageDir,
                imageFileName +  /* prefix */
                ".jpg"         /* suffix */
                      /* directory */
        );
        return image;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(currentPhotoPath != null) {
            adapter.addItem(ImageUtils.getBitmapFromFile(currentPhotoPath), currentPhotoPath);
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//        }
//    }
}
