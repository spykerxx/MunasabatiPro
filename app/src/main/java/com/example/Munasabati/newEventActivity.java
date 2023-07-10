package com.example.Munasabati;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class newEventActivity extends AppCompatActivity {

    public static String eventLatitude, eventLongitude;
    public static String address= "";
    String dateTime="";
    public static TextView edPhoto;
    public static String locationString="", timeString="";
    private EditText edName, edPrice, edDetail;
    private RecyclerView recycler;
    private int mYear, mMonth, mDay, mHour, mMinute;
    int myYear, myMonth, myDay, myHour, myMinute;
    Calendar myCalendar= null;
    public static String purpose="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        edName= findViewById(R.id.editTextEventName);
        edPrice= findViewById(R.id.editTextEventPrice);
        TextView edLocation = findViewById(R.id.tv_PickLocation);
        TextView edTime = findViewById(R.id.tv_PickTime);
        edDetail= findViewById(R.id.editTextEventDetail);
        recycler= findViewById(R.id.recyclerPickEventImage);
        edPhoto= findViewById(R.id.tv_SelectedPhoto);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recycler.setLayoutManager(layoutManager);
        loadItems();



        if (purpose.equals("edit")){
            Bundle b = getIntent().getExtras();
            String eventName = "null";
            if(b != null){
                eventName= b.getString("eventName");
            }
            Event selectedEvent= MainActivity.getEventByName(eventName);
            edName.setText(eventName);
            edName.setEnabled(false);
            edPrice.setText(selectedEvent.getPrice());
            edDetail.setText(selectedEvent.getDescription());
            edPhoto.setText(selectedEvent.getPhoto());
            locationString=selectedEvent.getLocation();
            timeString=selectedEvent.getDate();
            eventLongitude=selectedEvent.getLongitude().toString();
            eventLatitude=selectedEvent.getLatitude().toString();
        }

        Button addEvent = findViewById(R.id.buttonAddEvent);

        addEvent.setOnClickListener(v -> {
            String name= String.valueOf(edName.getText());
            String price= String.valueOf(edPrice.getText());
            String location= locationString;
            String time= timeString;
            String photo= String.valueOf(edPhoto.getText());
            String detail= String.valueOf(edDetail.getText());


            if(name.isEmpty() || price.isEmpty() || location.isEmpty() || time.isEmpty() || photo.isEmpty() || detail.isEmpty() ){
                Toast.makeText(getApplicationContext(), "all fields required!", Toast.LENGTH_SHORT).show();
                return;
            }


            Event newEvent= new Event(name, time, location, photo, price, detail);
            newEvent.setLongitude(Double.parseDouble(eventLongitude));
            newEvent.setLatitude(Double.parseDouble(eventLatitude));
            if (purpose.equals("edit")){
                int selectedEventPos= -1;
                for (int i = 0; i <MainActivity.allEvents.size() ; i++) {
                    if (MainActivity.allEvents.get(i).getName().equals(edName.getText().toString())){
                        selectedEventPos=i;
                        break;
                    }
                }
                editEvent(MainActivity.allEvents.get(selectedEventPos), newEvent);
            }

            else
            addEvent(newEvent);

        });

        edLocation.setOnClickListener(v -> {
            MapServicesActivity.op="newEvent";
            Intent intent=new Intent(newEventActivity.this , MapServicesActivity.class);
            startActivity(intent);
        });

        edTime.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year, monthOfYear, dayOfMonth) -> {

                       myYear=year;
                       myMonth=monthOfYear;
                       myDay=dayOfMonth;

                        myCalendar = new GregorianCalendar(myYear,
                                myMonth,
                                myDay,
                                myHour,
                                myMinute);
                        long  time = myCalendar.getTimeInMillis();
                        Date date= new Date(time);
                        dateTime= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
                        timeString= dateTime;

                    }, mYear, mMonth, mDay);

            datePickerDialog.show();

            // Get Current Time
            final Calendar cc = Calendar.getInstance();
            mHour = cc.get(Calendar.HOUR_OF_DAY);
            mMinute = cc.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    (view, hourOfDay, minute) -> {

                        myHour=hourOfDay;
                        myMinute=minute;



                    }, mHour, mMinute, false);
            timePickerDialog.show();

            });
    }

    private void addEvent(Event event){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            //Starting Write and Read data with URL
            //Creating array for parameters
            String[] field = new String[8];
            field[0] = "eventname";
            field[1] = "eventdate";
            field[2] = "eventlocation";
            field[3] = "eventphoto";
            field[4] = "price";
            field[5] = "description";
            field[6] = "latitude";
            field[7] = "longitude";
            //Creating array for data
            String[] data = new String[8];
            data[0] = event.getName();
            data[1] = event.getDate();
            data[2] = event.getLocation();
            data[3] = event.getPhoto();
            data[4] = event.getPrice();
            data[5] = event.getDescription();
            data[6] = eventLatitude;
            data[7] = eventLongitude;
            PutData putData = new PutData(MainActivity.IP+"addEvent.php", "POST", field, data);
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                    if(result.equals("adding success")){
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        MainActivity.allEvents.add(event);
                        startActivity(new Intent(getApplicationContext(), HomeAdminActivity.class));
                        finishAffinity();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    }

                }
            }
            //End Write and Read data with URL
        });
    }

    private void loadItems(){
        CaptionedPicturesAdapter adapter = new CaptionedPicturesAdapter(MainActivity.eventImages, "event");
        recycler.setAdapter(adapter);
    }

    private void editEvent(Event eventOld, Event eventNew){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            //Starting Write and Read data with URL
            //Creating array for parameters
            String[] field = new String[8];
            field[0] = "eventname";
            field[1] = "eventdate";
            field[2] = "eventlocation";
            field[3] = "eventphoto";
            field[4] = "price";
            field[5] = "description";
            field[6] = "latitude";
            field[7] = "longitude";
            //Creating array for data
            String[] data = new String[8];
            data[0] = eventNew.getName();
            data[1] = eventNew.getDate();
            data[2] = eventNew.getLocation();
            data[3] = eventNew.getPhoto();
            data[4] = eventNew.getPrice();
            data[5] = eventNew.getDescription();
            data[6] = eventLatitude;
            data[7] = eventLongitude;
            PutData putData = new PutData(MainActivity.IP+"editEvent.php", "POST", field, data);
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    if(result.equals("adding success")){
                        eventOld.setPrice(eventNew.getPrice());
                        eventOld.setPhoto(eventNew.getPhoto());
                        eventOld.setDate(eventNew.getDate());
                        eventOld.setDescription(eventNew.getDescription());
                        eventOld.setLocation(eventNew.getLocation());
                        eventOld.setLatitude(eventNew.getLatitude());
                        eventOld.setLongitude(eventNew.getLongitude());
                        startActivity(new Intent(getApplicationContext(), HomeAdminActivity.class));
                        finishAffinity();
                    }

                }
            }
            //End Write and Read data with URL
        });
    }
}