package com.inveitix.a05_recycler_view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.inveitix.a05_recycler_view.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.cc);
//        setSupportActionBar(binding.toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        setupSpinner();
//        setupRecView();
    }

    private void setupRecView() {
        GalleryAdapter adapter = new GalleryAdapter(getClickListener());
        binding.recView.setLayoutManager(new GridLayoutManager(this, 2));
        binding.recView.setAdapter(adapter);
    }

    private GalleryItemViewHolder.GalleryItemListener getClickListener() {
        return new GalleryItemViewHolder.GalleryItemListener() {
            @Override
            public void onGalleryItemClicked(ImageItem item) {
                Toast.makeText(MainActivity.this, "Gallery item clicked: " + item.imageDescription, Toast.LENGTH_SHORT).show();
            }
        };
//        return new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Gallery item clicked", Toast.LENGTH_SHORT).show();
//            }
//        };
    }

    private void setupSpinner() {
        String[] spinnerItems = new String[] {
                "One", "Two", "Three"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_send:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
