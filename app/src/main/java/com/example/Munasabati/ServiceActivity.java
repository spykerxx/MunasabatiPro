package com.example.Munasabati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {

    public static String eventName= "null";

    public static Service selectedService= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        TextView tvName = (TextView) findViewById(R.id.tv_MainServiceName);
        TextView tvDetail = (TextView) findViewById(R.id.tv_MainServiceDetail);
        TextView tvPrice = (TextView) findViewById(R.id.tv_MainServicePrice);
        TextView tvMinimumDuration = (TextView) findViewById(R.id.textViewMinimumDuration);
        ImageView imageViewPhoto = (ImageView) findViewById(R.id.imageViewService);
        Button checkIn = (Button) findViewById(R.id.buttonServiceCheckIn);

        checkIn.setOnClickListener(v -> {
            Intent intent=new Intent(ServiceActivity.this , PaymentActivity.class);
            startActivity(intent);


        });

        Bundle b = getIntent().getExtras();
        String value = "null";
        if(b != null){
            value = b.getString("serviceName");
            eventName= b.getString("eventName");
        }

        for (int i=0; i<MainActivity.allServices.size(); i++){
            if (MainActivity.allServices.get(i).getName().equals(value)){
                selectedService=MainActivity.allServices.get(i);
                break;
            }
        }


        if(selectedService!=null){
            tvName.setText(value);
            Drawable drawable= Drawable.createFromStream(getClass().getClassLoader().getResourceAsStream("res/drawable/" + selectedService.getPhoto() + ".png"), null);;
            imageViewPhoto.setImageDrawable(drawable);
            tvPrice.setText("$"+selectedService.getPrice());
            tvDetail.setText(selectedService.getDetail());
            tvMinimumDuration.setText(selectedService.getMinDuration()+" hours");

        }

    }

   /* private void pushServiceNotification(ServiceNotification notification){

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[3];
                field[0] = "eventName";
                field[1] = "serviceName";
                field[2] = "serviceProviderName";

                //Creating array for data
                String[] data = new String[3];
                data[0] = notification.getEventName();
                data[1] = notification.getServiceName();
                data[2] = notification.getServiceProviderName();

                PutData putData = new PutData(MainActivity.IP+"addServiceNotification.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                     //    Toast.makeText(null, result, Toast.LENGTH_SHORT).show();

                    }
                }
                //End Write and Read data with URL
            }
        });
    }
*/

}