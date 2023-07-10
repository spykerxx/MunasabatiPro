package com.example.Munasabati;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CaptionedEventAdapter
        extends RecyclerView.Adapter<CaptionedEventAdapter.ViewHolder>{
    private Context context;
    private List<Event> items;
    private int layoutId;



    public CaptionedEventAdapter(Context context, List<Event> items, int layoutId){
        this.context = context;
        this.items = items;
        this.layoutId=layoutId;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(layoutId,
                parent,
                false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Event Events = items.get(position);
        CardView cardView = holder.cardView;
        if (this.layoutId==R.layout.card_captioned2){
        String strDate="";
        String replacedString="";

        try {
            Date date1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(Events.getDate());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM");
            strDate = dateFormat.format(date1);
            replacedString = strDate.replace("-", "\n");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TextView txtDate =(TextView)cardView.findViewById(R.id.tv_CardEventDate);
        txtDate.setText(replacedString);

        TextView txtName =(TextView)cardView.findViewById(R.id.tv_CardEventName);
        txtName.setText(Events.getName());


        TextView txtLocation = (TextView)cardView.findViewById(R.id.CardEventLocation);
        txtLocation.setText(Events.getLocation());

        ImageView eventImage= (ImageView) cardView.findViewById(R.id.iv_eventimage);

        Drawable drawable= Drawable.createFromStream(getClass().getClassLoader().getResourceAsStream("res/drawable/" + Events.getPhoto() + ".png"), null);;
        eventImage.setImageDrawable(drawable);
        }
       //****************************** Listview ************************

        else{
            TextView tvEventName= cardView.findViewById(R.id.tv_listName);
            TextView tvPrice= cardView.findViewById(R.id.tv_ServiceName);
            ImageView ivPhoto= cardView.findViewById(R.id.iv_eventimage);
            TextView tvDate= cardView.findViewById(R.id.tv_eventDate);
            TextView tvLocation= cardView.findViewById(R.id.tv_eventLocation);
            Spinner spinner= cardView.findViewById(R.id.spinnerAdmin);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int positionx, long id) {
                  switch (positionx){
                      case 1: {addService(Events.getName()); break;}
                      case 2: {newEventActivity.purpose="edit"; editEvent(Events.getName());  break;}
                      case 3: {
                          deleteEvent(Events.getName(), context.getApplicationContext());
                          items.remove(position);
                          notifyItemRemoved(position);
                          notifyItemRangeChanged(position, items.size());
                      break;}
                      default: break;
                  }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });

            tvEventName.setText(Events.getName());
            tvPrice.setText(Events.getPrice());
            tvDate.setText(Events.getDate());
            tvLocation.setText(Events.getLocation());
            Drawable drawable= Drawable.createFromStream(getClass().getClassLoader().getResourceAsStream("res/drawable/" + Events.getPhoto() + ".png"), null);;
            ivPhoto.setImageDrawable(drawable);


        }
        cardView.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public ViewHolder(CardView cardView){
            super(cardView);
            this.cardView = cardView;
        }

    }
    private void addService(String eventName){
        Bundle b = new Bundle();
        b.putString("eventName", eventName); //Your id
        Intent intent=new Intent(context.getApplicationContext() , ServicesActivity.class);
        intent.putExtras(b);
        context.startActivity(intent);
    }

    private void editEvent(String eventName){
        Bundle b = new Bundle();
        b.putString("eventName", eventName); //Your id
        Intent intent=new Intent(context.getApplicationContext() , newEventActivity.class);
        intent.putExtras(b);
        context.startActivity(intent);
    }

    private void deleteEvent(String eventName, Context context){
        for (int i = 0; i < MainActivity.allRequests.size(); i++) {
            if (MainActivity.allEvents.get(i).getName().equals(eventName)){
                MainActivity.allRequests.remove(i);
                break;
            }
        }

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            //Starting Write and Read data with URL
            //Creating array for parameters
            String[] field = new String[1];
            field[0] = "eventname";

            //Creating array for data
            String[] data = new String[1];
            data[0] = eventName;


            PutData putData = new PutData(MainActivity.IP+"deleteEvent.php", "POST", field, data);
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                    Toast.makeText(context.getApplicationContext(), result+" "+ eventName, Toast.LENGTH_SHORT).show();

                }
            }
            //End Write and Read data with URL
        });
    }
}

