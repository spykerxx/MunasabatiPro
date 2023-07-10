package com.example.Munasabati;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class AdminRequestsActivity extends AppCompatActivity {
    private List<EventRequest> items = new ArrayList<>();
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_requests);

        recycler=findViewById(R.id.admin_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        loadItems();
        //  print();


        bottomNavigationView= findViewById(R.id.nav_AdminHome);
        bottomNavigationView.setSelectedItemId(R.id.navigation_Requests);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_Requests:
                    return true;

                case R.id.navigation_Home:
                    //startActivity(new Intent(getApplicationContext() , HomeActivity.class));
                    finish();
                    overridePendingTransition(0,0);
                    HomeAdminActivity.bottomNavigationView.setSelectedItemId(R.id.navigation_Home);
                    return true;

                case R.id.navigation_Calendar:
                    startActivity(new Intent(getApplicationContext() , CalendarActivity.class));
                    finish();
                    overridePendingTransition(0,0);
                    return true;

                case R.id.navigation_Profile:
                    startActivity(new Intent(getApplicationContext() , AdminProfileActivity.class));
                    finish();
                    overridePendingTransition(0,0);
                    return true;

            }
            return false;
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void loadItems() {
        items.addAll(MainActivity.allRequests);
        CaptionedRequestsAdapter adapter = new CaptionedRequestsAdapter(AdminRequestsActivity.this,
                items, R.layout.layout_admin_requests);
        recycler.setAdapter(adapter);

    }

}