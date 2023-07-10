package com.example.Munasabati;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class RequestsActivity extends AppCompatActivity {
    private List<EventRequest> items = new ArrayList<>();
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recycler;
    public static String user= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        recycler=findViewById(R.id.user_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        loadItems();
        //  print();


        bottomNavigationView=(BottomNavigationView)findViewById(R.id.nav_Home);
        bottomNavigationView.setSelectedItemId(R.id.navigation_Requests);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_Requests:
                    return true;

                case R.id.navigation_Home:
                    //startActivity(new Intent(getApplicationContext() , HomeActivity.class));
                    finish();
                    overridePendingTransition(0,0);
                    HomeActivity.bottomNavigationView.setSelectedItemId(R.id.navigation_Home);
                    return true;

                case R.id.navigation_Notifications:
                    startActivity(new Intent(getApplicationContext() , NotificationsActivity.class));
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
        int layoutId=0;

        if(user.equals("admin")){
            layoutId=R.layout.layout_admin_requests;
            for (int i = 0; i <MainActivity.allRequests.size() ; i++) {
                    items.add(MainActivity.allRequests.get(i));
            }
        }

        else{
            layoutId= R.layout.card_captioned_request;
          for (int i = 0; i <MainActivity.allRequests.size() ; i++) {
            if (MainActivity.allRequests.get(i).getClientName().equals(MainActivity.currentUser.getUsername())){
                items.add(MainActivity.allRequests.get(i));
            }
          }
        }

        CaptionedRequestsAdapter adapter = new CaptionedRequestsAdapter(RequestsActivity.this,
                items, layoutId);
        recycler.setAdapter(adapter);

    }

}