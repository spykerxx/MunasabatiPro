package com.example.Munasabati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventActivity extends AppCompatActivity {

    private ImageView imageViewPhoto;
    private View viewDark;

    public static Event selectedEvent= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        TextView tvName = findViewById(R.id.textViewEventName);
        TextView tvDate = findViewById(R.id.textViewDate);
        TextView tvTime = findViewById(R.id.textViewTime);
        TextView tvLocation = findViewById(R.id.textViewLocation);
        imageViewPhoto= findViewById(R.id.imageViewRoom);
        Button checkIn = findViewById(R.id.buttonCheckIn);
        viewDark= findViewById(R.id.viewDark);
        viewDark.setVisibility(View.GONE);

        TextView tvGetDirections= findViewById(R.id.textViewGetDirections);

        tvLocation.setOnClickListener(v -> {
            getDirectionsOnMap();
        });

        tvGetDirections.setOnClickListener(v -> {
            getDirectionsOnMap();
        });

        Bundle b = getIntent().getExtras();
        String value = "null";
        if(b != null)
            value = b.getString("key");

        for (int i=0; i<MainActivity.allEvents.size(); i++){
            if (MainActivity.allEvents.get(i).getName().equals(value)){
                selectedEvent=MainActivity.allEvents.get(i);
                break;
            }
        }

        if(selectedEvent!=null){
        tvName.setText(value);
        Drawable drawable= Drawable.createFromStream(getClass().getClassLoader().getResourceAsStream("res/drawable/" + selectedEvent.getPhoto() + ".png"), null);;
        imageViewPhoto.setImageDrawable(drawable);
            Date date1=null;

            try {
                 date1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(selectedEvent.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm aa");
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd-MM-yyyy");
            String shortTimeStr = timeFormat.format(date1);
            String shortDateStr = dateFormat.format(date1);
        tvDate.setText(shortDateStr);
        tvLocation.setText(selectedEvent.getLocation());
        tvTime.setText(shortTimeStr);

        }


        checkIn.setOnClickListener(v -> {

            // inflate the layout of the popup window
            viewDark.setVisibility(View.VISIBLE);
            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = inflater.inflate(R.layout.popup1, null);

            // create the popup window
            int width = 1200;
            int height = 680;
            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

            // show the popup window
            // which view you pass in doesn't matter, it is only used for the window tolken
            popupWindow.showAtLocation(imageViewPhoto, Gravity.CENTER, 0, 100);

            popupWindow.setOnDismissListener(() -> viewDark.setVisibility(View.GONE));

            // dismiss the popup window when touched
            popupView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    popupWindow.dismiss();
                    viewDark.setVisibility(View.GONE);
                    return true;
                }
            });

            viewDark.setOnClickListener(v1 -> viewDark.setVisibility(View.GONE));

            EventRequest request= new EventRequest(MainActivity.currentUser.getUsername(), selectedEvent.getName());
            pushEventRequest(request);

        });


    }

    private void pushEventRequest(EventRequest request){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            //Starting Write and Read data with URL
            //Creating array for parameters
            String[] field = new String[2];
            field[0] = "clientName";
            field[1] = "eventName";

            //Creating array for data
            String[] data = new String[2];
            data[0] = request.getClientName();
            data[1] = request.getEventName();

            PutData putData = new PutData(MainActivity.IP+"addRequest.php", "POST", field, data);
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                    if(result.equals("adding success")){
                        MainActivity.allRequests.add(request);
                    }
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                }
            }
            //End Write and Read data with URL
        });
    }

    private void getDirectionsOnMap(){
        Intent intent=new Intent(EventActivity.this , MapServicesActivity.class);
        MapServicesActivity.op="getEvent";
        startActivity(intent);
    }


}