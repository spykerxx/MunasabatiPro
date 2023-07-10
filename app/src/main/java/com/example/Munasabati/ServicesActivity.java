package com.example.Munasabati;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ServicesActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private final List<Service> items = new ArrayList<>();
    public static String  eventName;
    private EditText edSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        TextView greeting = findViewById(R.id.textViewHiClient);
        greeting.setText("Hi "+MainActivity.username);
        ImageView back = findViewById(R.id.iv_ServiceBack);
        back.setOnClickListener(v -> finish());

       Bundle b = getIntent().getExtras();
        if(b != null)
            eventName = b.getString("eventName");

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));


        recycler= findViewById(R.id.recyclerServices);
         RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        loadItems();


        recycler.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recycler ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        TextView textViewName = (TextView) view.findViewById(R.id.tv_CardServiceName);

                        if (b!=null){

                        b.putString("serviceName", textViewName.getText().toString().trim());
                        Intent intent=new Intent(ServicesActivity.this , ServiceActivity.class);
                        intent.putExtras(b); //Put your id to your next Intent
                        startActivity(intent);
                    }
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

      edSearch= findViewById(R.id.edSearchAdminServices);
        edSearch.setOnEditorActionListener((v, actionId, event) -> {
            try{
                String serviceName= edSearch.getText().toString();
                boolean found=false;
                for (int i = 0; i < MainActivity.allServices.size(); i++) {
                    if(MainActivity.allServices.get(i).getName().equals(serviceName)){
                        found=true;
                        break;
                    }
                }
                if(!found){
                    Toast.makeText(getApplicationContext(), "Can't find service!", Toast.LENGTH_SHORT).show();
                    return true;
                }
                b.putString("serviceName", serviceName);
                Intent intent=new Intent(getApplicationContext() , ServiceActivity.class);
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);



            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Can't find event!", Toast.LENGTH_SHORT).show();
            }
            return true;
        });

    }

    private void loadItems() {
        CaptionedServiceAdapter adapter = new CaptionedServiceAdapter(ServicesActivity.this,
                MainActivity.allServices);
        recycler.setAdapter(adapter);
    }

}
