package com.example.Munasabati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String  IP= "http://192.168.1.33/loginreg/";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String USER_PREFS = "user_prefs";
    public static final String PASS_PREFS = "pass_prefs";
    public static final String TYPE_PREFS = "type_prefs";
    public static String username="";
    public static String password="";
    public static int typeSpinnerSelection=-1;

    private static final String BASE_URL = MainActivity.IP+"events.php";
    public static List<Event> allEvents = new ArrayList<>();

    private static final String BASE_URL_Services = MainActivity.IP+"services.php";
    public static List<Service> allServices = new ArrayList<>();

    public static List<EventRequest> allRequests = new ArrayList<>();

    public static List<EventNotification> allNotifications = new ArrayList<>();

    public static List<Invitation> allInvitations = new ArrayList<>();

    public static List<User> allUsers = new ArrayList<>();
    public static User currentUser=null;

    public static List<EventImage> eventImages = new ArrayList<>();
    public static List<EventImage> serviceImages = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView skip = findViewById(R.id.startSkipTextView);
        Button startButton = findViewById(R.id.startButton);

       // StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        //******** Load resources ********
       loadSharedPref();
       loadEvents();
       loadServices();
       loadRequests();
       loadNotifications();
       loadInvitations();
       loadUsers();
       loadEventImages();
       loadServiceImages();
       //*********************************

        startButton.setOnClickListener(view -> {
               Intent intent=new Intent(MainActivity.this , SignupActivity.class);
               startActivity(intent);
               finish();

        });

        skip.setOnClickListener(view -> {
                Intent intent=new Intent(MainActivity.this , LoginActivity.class);
                startActivity(intent);
                finish();

        });

    }

    public void loadSharedPref(){
        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        username=sharedPreferences.getString(USER_PREFS,"");
        password=sharedPreferences.getString(PASS_PREFS,"");
        typeSpinnerSelection=sharedPreferences.getInt(TYPE_PREFS,-1);

    }

    private void loadEvents() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                response -> {

                    try {

                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i<array.length(); i++){

                            JSONObject object = array.getJSONObject(i);
                            String name = object.getString("eventname");
                            String date = object.getString("eventdate");
                            String location= object.getString("eventlocation");
                            String photo= object.getString("eventphoto");
                            String price= object.getString("price");
                            String description= object.getString("description");
                            double latitude= object.getDouble("latitude");
                            double longitude= object.getDouble("longitude");
                            //String photoName= object.getString("price");
                            // Toast.makeText(UserShowActivity.this, name,Toast.LENGTH_LONG).show();
                            Event event = new Event(name, date,location, photo, price, description);
                            event.setLatitude(latitude);
                            event.setLongitude(longitude);
                            allEvents.add(event);
                        }
                        //text.setText(items.toString());
                    }catch (Exception e){

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(MainActivity.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(MainActivity.this).add(stringRequest);

    }


    private void loadServices() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL_Services,
                response -> {

                    try {

                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i<array.length(); i++){

                            JSONObject object = array.getJSONObject(i);
                            String name = object.getString("name");
                            String price = object.getString("price");
                            String minDuration = object.getString("minimumDuration");
                            String photo= object.getString("photo");
                            String detail= object.getString("detail");
                            String providerName= object.getString("providerName");
                            Service service = new Service(name, price, minDuration, photo, detail, providerName);
                            allServices.add(service);
                        }
                        //text.setText(items.toString());
                    }catch (Exception e){

                    }






                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(MainActivity.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(MainActivity.this).add(stringRequest);

    }

    private void loadRequests() {

        String BASE_URL_requests = MainActivity.IP + "requests.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL_requests,
                response -> {

                    try {

                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i<array.length(); i++){

                            JSONObject object = array.getJSONObject(i);
                            String clientName = object.getString("clientName");
                            String eventName = object.getString("eventName");
                            EventRequest request = new EventRequest(clientName, eventName);
                            allRequests.add(request);
                        }
                        //text.setText(items.toString());
                    }catch (Exception e){

                    }






                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(MainActivity.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(MainActivity.this).add(stringRequest);

    }

    private void loadNotifications() {

        String BASE_URL_notifications = MainActivity.IP + "notifications.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL_notifications,
                response -> {

                    try {

                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i<array.length(); i++){

                            JSONObject object = array.getJSONObject(i);
                            String clientName = object.getString("username");
                            String eventName = object.getString("title");
                            String comment = object.getString("comment");
                            String time= object.getString("time");
                            EventNotification notification = new EventNotification(clientName, eventName, comment, time);
                            allNotifications.add(notification);
                        }
                        //text.setText(items.toString());
                    }catch (Exception e){

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(MainActivity.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(MainActivity.this).add(stringRequest);

    }

    private void loadInvitations() {

        String BASE_URL_invitations = MainActivity.IP + "invitations.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL_invitations,
                response -> {

                    try {

                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i<array.length(); i++){

                            JSONObject object = array.getJSONObject(i);
                            String clientName = object.getString("clientName");
                            String eventName = object.getString("eventName");
                            String time= object.getString("time");
                            String qr= object.getString("qr");
                            Invitation invitation = new Invitation(clientName, eventName, time);
                            invitation.setQr(qr);
                            allInvitations.add(invitation);
                        }
                        //text.setText(items.toString());
                    }catch (Exception e){

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(MainActivity.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(MainActivity.this).add(stringRequest);

    }

    private void loadUsers() {

        String BASE_URL_users = MainActivity.IP + "users.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL_users,
                response -> {

                    try {

                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i<array.length(); i++){

                            JSONObject object = array.getJSONObject(i);
                            String name = object.getString("username");
                            String email = object.getString("email");
                            String pass= object.getString("password");
                            String phone= object.getString("phone");
                            String type= object.getString("type");
                            User user = new User(name, phone, email, pass, type);
                            allUsers.add(user);


                        }
                       getCurrentUser();
                        //text.setText(items.toString());
                    }catch (Exception e){

                    }

                }, error -> Toast.makeText(MainActivity.this, error.toString(),Toast.LENGTH_LONG).show());

        Volley.newRequestQueue(MainActivity.this).add(stringRequest);

    }

    private void loadEventImages(){
        eventImages.add(new EventImage("eventpic")); eventImages.add(new EventImage("eventpic2"));
        eventImages.add(new EventImage("eventpic3")); eventImages.add(new EventImage("eventpic4"));
    }

    private void loadServiceImages(){
        serviceImages.add(new EventImage("servicepic")); serviceImages.add(new EventImage("servicepic2"));
        serviceImages.add(new EventImage("servicepic3")); serviceImages.add(new EventImage("servicepic4"));
    }

    public static void getCurrentUser() {
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getUsername().equals(username)) {
                currentUser = allUsers.get(i);
                break;
            }
        }
    }

    public static Event getEventByName(String eventName){
        for (int i = 0; i < allEvents.size(); i++) {
            if(allEvents.get(i).getName().equals(eventName)){
                return allEvents.get(i);
            }
        }
        return null;
    }

}