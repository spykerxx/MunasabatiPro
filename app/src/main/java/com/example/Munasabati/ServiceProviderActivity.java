package com.example.Munasabati;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ServiceProviderActivity extends AppCompatActivity {

    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider);

        TextView greeting = findViewById(R.id.textViewHiProvider);
        greeting.setText("Hi " + MainActivity.username);
        ImageView back = findViewById(R.id.sideButton5);
        back.setOnClickListener(v -> finish());

        FloatingActionButton fabAddService = findViewById(R.id.fabAddService);
        fabAddService.setOnClickListener(v -> startActivity(new Intent(ServiceProviderActivity.this, newServiceActivity.class)));

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));


        recycler= findViewById(R.id.recyclerProviderServices);


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        loadItems();


    }

    private void loadItems() {
        CaptionedServiceAdapter adapter = new CaptionedServiceAdapter(getApplicationContext(),
                MainActivity.allServices);
        recycler.setAdapter(adapter);
    }

}
