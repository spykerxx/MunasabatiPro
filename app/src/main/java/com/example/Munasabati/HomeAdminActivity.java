package com.example.Munasabati;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeAdminActivity extends AppCompatActivity {

    private RecyclerView recycler;
    public static BottomNavigationView bottomNavigationView;
   // private EditText edSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        recycler= findViewById(R.id.homeAdmin_recycler);
        //edSearch= findViewById(R.id.edSearchHomeAdmin);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        loadItems();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent=new Intent(HomeAdminActivity.this , newEventActivity.class);
            startActivity(intent);
        });

        bottomNavigationView = findViewById(R.id.nav_AdminHome);
        bottomNavigationView.setSelectedItemId(R.id.navigation_Home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_Home:
                    return true;

                case R.id.navigation_Calendar:
                    startActivity(new Intent(getApplicationContext() , CalendarActivity.class));
                    //finish();
                    //HomeActivity.bottomNavigationView.setSelectedItemId(R.id.navigation_Services);
                    overridePendingTransition(0,0);
                    return true;

                case R.id.navigation_Requests:
                    startActivity(new Intent(getApplicationContext() , AdminRequestsActivity.class));
                    //finish();
                    overridePendingTransition(0,0);
                    return true;

                case R.id.navigation_Profile:
                    startActivity(new Intent(getApplicationContext() , AdminProfileActivity.class));
                    //finish();
                    overridePendingTransition(0,0);
                    return true;

            }
            return false;
        });


    }

    private void loadItems() {
        CaptionedEventAdapter adapter = new CaptionedEventAdapter(HomeAdminActivity.this,
                MainActivity.allEvents, R.layout.layout);
        recycler.setAdapter(adapter);

    }


}