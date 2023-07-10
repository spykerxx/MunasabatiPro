package com.example.Munasabati;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashSet;

public class CalendarActivity extends AppCompatActivity {

    MaterialCalendarView calendarView;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        bottomNavigationView = findViewById(R.id.nav_AdminCalendar);
        bottomNavigationView.setSelectedItemId(R.id.navigation_Calendar);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_Calendar:
                    return true;

                case R.id.navigation_Home:
                    //startActivity(new Intent(getApplicationContext() , CalendarActivity.class));
                    finish();
                    HomeAdminActivity.bottomNavigationView.setSelectedItemId(R.id.navigation_Home);
                    overridePendingTransition(0,0);
                    return true;

                case R.id.navigation_Requests:
                    startActivity(new Intent(getApplicationContext() , AdminRequestsActivity.class));
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


        calendarView= findViewById(R.id.calendarView);
        Collection<CalendarDay> dates= new HashSet<>();
        calendarView.setDateSelected(CalendarDay.today(), true);
        calendarView.setSelectionColor(Color.rgb(255, 165, 0));

        for (int i = 0; i <MainActivity.allEvents.size() ; i++) {
            try {
                dates.add(CalendarDay.from(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(MainActivity.allEvents.get(i).getDate())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        calendarView.addDecorator(new EventDecorator(dates));
    }

     class EventDecorator implements DayViewDecorator {

        private final HashSet<CalendarDay> dates;
        private Drawable drawable;

        public EventDecorator( Collection<CalendarDay> dates) {
            drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.grey2png);
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setSelectionDrawable(drawable);
        }
    }
}