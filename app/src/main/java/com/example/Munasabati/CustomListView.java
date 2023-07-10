package com.example.Munasabati;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.annotation.NonNull;

import org.jetbrains.annotations.Nullable;


public class CustomListView extends ArrayAdapter<String>{

    private String[] roomNumber;
    private String[] price;
    private String[] imagepath;
    private String[] eventDate;
    private String[] eventLocation;
    private Activity context;
    Bitmap bitmap;


    public CustomListView(Activity context,String[] roomNumber,String[] price,String[] imagepath, String[] eventDate, String[] eventLocation)  {
        super(context, R.layout.layout,roomNumber);
        this.context=context;
        this.roomNumber=roomNumber;
        this.price=price;
        this.imagepath=imagepath;
        this.eventDate=eventDate;
        this.eventLocation=eventLocation;
    }


    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r=convertView;
       final ViewHolder viewHolder;
        if(r==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.layout,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)r.getTag();

        }

        viewHolder.tvRoomNumber.setText(roomNumber[position]);
        viewHolder.tvPrice.setText(price[position]);
        Drawable drawable= Drawable.createFromStream(getClass().getClassLoader().getResourceAsStream("res/drawable/" + imagepath[position] + ".png"), null);;
        viewHolder.ivPhoto.setImageDrawable(drawable);
        viewHolder.tvDate.setText(eventDate[position]);
        viewHolder.tvLocation.setText(eventLocation[position]);
        viewHolder.spinner.setVisibility(View.GONE);

        return r;
    }

    class ViewHolder{

        TextView tvRoomNumber;
        TextView tvPrice;
        TextView tvDate;
        TextView tvLocation;
        ImageView ivPhoto;
        Spinner spinner;

        ViewHolder(View v){
            tvRoomNumber=(TextView)v.findViewById(R.id.tv_listName);
            tvPrice=(TextView)v.findViewById(R.id.tv_ServiceName);
            ivPhoto=(ImageView)v.findViewById(R.id.iv_eventimage);
            tvDate= (TextView) v.findViewById(R.id.tv_eventDate);
            tvLocation= (TextView) v.findViewById(R.id.tv_eventLocation);
            spinner= v.findViewById(R.id.spinnerAdmin);


        }


    }





}
