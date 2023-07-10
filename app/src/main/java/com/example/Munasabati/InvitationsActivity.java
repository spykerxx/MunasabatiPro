package com.example.Munasabati;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class InvitationsActivity extends AppCompatActivity {
    private List<Invitation> items = new ArrayList<>();
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitations);



        recycler=findViewById(R.id.invitations_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        loadItems();

        recycler.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recycler ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {


                        Bundle b= new Bundle();
                        b.putString("qr", view.getTag().toString());
                        Intent intent=new Intent(InvitationsActivity.this , InvitationActivity.class);
                        intent.putExtras(b); //Put your id to your next Intent
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        bottomNavigationView= findViewById(R.id.nav_Home);
        bottomNavigationView.setSelectedItemId(R.id.navigation_Profile);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_Profile:
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

                case R.id.navigation_Notifications:
                    startActivity(new Intent(getApplicationContext() , NotificationsActivity.class));
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

        for (int i = 0; i <MainActivity.allInvitations.size() ; i++) {
            if (MainActivity.allInvitations.get(i).getClientName().equals(MainActivity.currentUser.getUsername())){
                items.add(MainActivity.allInvitations.get(i));
            }
        }

        CaptionedInvitationsAdapter adapter = new CaptionedInvitationsAdapter(InvitationsActivity.this,
                items);
        recycler.setAdapter(adapter);

    }

}