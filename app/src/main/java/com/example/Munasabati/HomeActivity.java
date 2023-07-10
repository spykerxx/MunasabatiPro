package com.example.Munasabati;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String urladdress=MainActivity.IP+"events.php";
    String[] roomNumber;
    String[] price;
    String[] imagepath;
    String[] eventDate;
    String[] eventLocation;
    ListView listView;
    private RecyclerView recycler;
    BufferedInputStream is;
    String line=null;
    String result=null;
    private ImageView sideMenu;
    public static String currentRoomNumber, orderStatus;
    public static BottomNavigationView bottomNavigationView;
    private int xConditon=0;

    EditText search;

//*************
    private List<Event> items = new ArrayList<>();
    //************** TO BE REMOVED

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       // saveSharedPref();

        Bundle b = new Bundle();


        listView = (ListView) findViewById(R.id.lview);
        recycler = (RecyclerView) findViewById(R.id.recyclerHome);
        search = (EditText) findViewById(R.id.edSearch);
        sideMenu= (ImageView) findViewById(R.id.sideButton);

        TextView seeAll= (TextView) findViewById(R.id.textViewSeeAll);
        seeAll.setOnClickListener(v -> {
            listView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2600));
           // listView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ConstraintLayout constraintLayout = findViewById(R.id.homeLayout);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(R.id.lview,ConstraintSet.TOP,R.id.edSearch,ConstraintSet.BOTTOM,10);
            constraintSet.connect(R.id.lview,ConstraintSet.START,R.id.homeLayout,ConstraintSet.START,62);
            constraintSet.applyTo(constraintLayout);
            xConditon=1;
        });

//********************************************
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
       // RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        recycler.setLayoutManager(layoutManager);
        loadItems();

        recycler.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recycler ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        TextView textViewName = (TextView) view.findViewById(R.id.tv_CardEventName);

                        b.putString("key", textViewName.getText().toString().trim());
                        Intent intent=new Intent(HomeActivity.this , EventActivity.class);
                        intent.putExtras(b); //Put your id to your next Intent
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        bottomNavigationView= findViewById(R.id.nav_Home);
        bottomNavigationView.setSelectedItemId(R.id.navigation_Home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_Home:
                    return true;

                case R.id.navigation_Requests:
                    startActivity(new Intent(getApplicationContext() , RequestsActivity.class));
                   // finish();
                    overridePendingTransition(0,0);
                    return true;

                case R.id.navigation_Notifications:
                    startActivity(new Intent(getApplicationContext() , NotificationsActivity.class));
                    //finish();
                    overridePendingTransition(0,0);
                    return true;

                case R.id.navigation_Profile:
                    startActivity(new Intent(getApplicationContext() , InvitationsActivity.class));
                    //finish();
                    overridePendingTransition(0,0);
                    return true;

            }
            return false;
        });

        //*************************************************************************

        sideMenu.setOnClickListener(v -> {
           if(xConditon==1){
               listView.setLayoutParams(new ConstraintLayout.LayoutParams(1400, 2600));
               // listView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
               ConstraintLayout constraintLayout = findViewById(R.id.homeLayout);
               ConstraintSet constraintSet = new ConstraintSet();
               constraintSet.clone(constraintLayout);
               constraintSet.connect(R.id.lview,ConstraintSet.TOP,R.id.view3,ConstraintSet.BOTTOM,1050);
               constraintSet.connect(R.id.lview,ConstraintSet.START,R.id.homeLayout,ConstraintSet.START,62);
               constraintSet.applyTo(constraintLayout);
               xConditon=0;
           }
           else if (xConditon==0){
               Intent intent=new Intent(HomeActivity.this , LoginActivity.class);
               startActivity(intent);
               finishAffinity();
           }

        });

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        collectData();
        CustomListView customListView=new CustomListView(this,roomNumber,price,imagepath, eventDate, eventLocation);

        listView.setAdapter(customListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                TextView textViewName = (TextView) view.findViewById(R.id.tv_listName);

                b.putString("key", textViewName.getText().toString().trim());
                Intent intent=new Intent(HomeActivity.this , EventActivity.class);
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }

        });

        search.setOnEditorActionListener((v, actionId, event) -> {
            try{
                String eventName= search.getText().toString();
                boolean found=false;
                for (int i = 0; i < MainActivity.allEvents.size(); i++) {
                    if(MainActivity.allEvents.get(i).getName().equals(eventName)){
                        found=true;
                        break;
                    }
                }
                if(!found){
                    Toast.makeText(getApplicationContext(), "Can't find event!", Toast.LENGTH_SHORT).show();
                    return true;
                }
                b.putString("key", eventName);
                Intent intent=new Intent(HomeActivity.this , EventActivity.class);
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);



            } catch (Exception e) {
                 Toast.makeText(getApplicationContext(), "Can't find event!", Toast.LENGTH_SHORT).show();
            }
            return true;
        });


    }

    private void collectData()
    {
//Connection
        try{

            URL url=new URL(urladdress);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        //content
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            StringBuilder sb=new StringBuilder();
            while ((line=br.readLine())!=null){
                sb.append(line+"\n");
            }
            is.close();
            result=sb.toString();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();

        }

//JSON
        try{
            JSONArray ja=new JSONArray(result);
            JSONObject jo=null;
            roomNumber=new String[ja.length()];
            price=new String[ja.length()];
            imagepath=new String[ja.length()];
            eventDate=new String[ja.length()];
            eventLocation=new String[ja.length()];

            for(int i=0;i<=ja.length();i++){
                jo=ja.getJSONObject(i);
                roomNumber[i]=jo.getString("eventname");
                price[i]=jo.getString("price");
                imagepath[i]=jo.getString("eventphoto");
                eventDate[i]=jo.getString("eventdate");
                eventLocation[i]=jo.getString("eventlocation");
            }
        }
        catch (Exception ex)
        {

            ex.printStackTrace();
        }


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
    public static void clearReservation(){
        currentRoomNumber="";

    }

    /*public void saveSharedPref(){
        SharedPreferences sharedPreferences= getSharedPreferences(MainActivity.SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putInt(MainActivity.PROFITS_PREFS,MainActivity.profits);
        editor.apply();

    }*/

    private void loadItems() {
        CaptionedEventAdapter adapter = new CaptionedEventAdapter(HomeActivity.this,
                MainActivity.allEvents, R.layout.card_captioned2);
        recycler.setAdapter(adapter);
    }
}
