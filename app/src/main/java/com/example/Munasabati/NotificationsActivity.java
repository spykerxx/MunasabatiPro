package com.example.Munasabati;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {
    private List<EventNotification> items = new ArrayList<>();
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        recycler=findViewById(R.id.notifications_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        loadItems();
        //  print();


        bottomNavigationView=(BottomNavigationView)findViewById(R.id.nav_Home);
        bottomNavigationView.setSelectedItemId(R.id.navigation_Notifications);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_Notifications:
                    return true;

                case R.id.navigation_Home:
                    //startActivity(new Intent(getApplicationContext() , HomeActivity.class));
                    finish();
                    HomeActivity.bottomNavigationView.setSelectedItemId(R.id.navigation_Home);
                    overridePendingTransition(0,0);
                    return true;

                case R.id.navigation_Requests:
                    startActivity(new Intent(getApplicationContext() , RequestsActivity.class));
                    finish();
                    overridePendingTransition(0,0);
                    return true;

                case R.id.navigation_Profile:
                    startActivity(new Intent(getApplicationContext() , InvitationsActivity.class));
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

        for (int i = 0; i <MainActivity.allNotifications.size() ; i++) {
            if (MainActivity.allNotifications.get(i).getClientName().equals(MainActivity.currentUser.getUsername())){
                items.add(MainActivity.allNotifications.get(i));
            }
        }

        System.out.println(items.size()+" this is sizzzzzze");
        System.out.println(MainActivity.allNotifications.size()+" this is main size");

        CaptionedNotificationsAdapter adapter = new CaptionedNotificationsAdapter(NotificationsActivity.this,
                items);
        recycler.setAdapter(adapter);

    }

}