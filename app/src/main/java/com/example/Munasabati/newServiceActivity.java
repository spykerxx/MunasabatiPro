package com.example.Munasabati;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class newServiceActivity extends AppCompatActivity {

    private EditText edName, edPrice, edMinDur, edDetail;
    public static TextView edPhoto;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_service);

        edName= findViewById(R.id.editTextServiceName);
        edPrice= findViewById(R.id.editTextServicePrice);
        edMinDur= findViewById(R.id.editTextServiceMinDuration);
        edPhoto= findViewById(R.id.tv_SelectedPhotoService);
        edDetail= findViewById(R.id.editTextServiceDetail);
        recycler= findViewById(R.id.recyclerPickServiceImage);

        Button addService = findViewById(R.id.buttonAddService);
        ImageView sideButton= findViewById(R.id.sideButton3);
        sideButton.setOnClickListener(v -> finish());

        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recycler.setLayoutManager(layoutManager);
        loadItems();

        addService.setOnClickListener(v -> {
            String name= String.valueOf(edName.getText());
            String price= String.valueOf(edPrice.getText());
            String minDur= String.valueOf(edMinDur.getText());
            String photo= String.valueOf(edPhoto.getText());
            String detail= String.valueOf(edDetail.getText());

            if(name.isEmpty() || price.isEmpty() || minDur.isEmpty() || photo.isEmpty() || detail.isEmpty() ){
                Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Service service= new Service(name, price, minDur, photo, detail, MainActivity.username);
            addService(service);

        });

    }

    private void addService(Service service){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            //Starting Write and Read data with URL
            //Creating array for parameters
            String[] field = new String[6];
            field[0] = "name";
            field[1] = "price";
            field[2] = "minimumDuration";
            field[3] = "photo";
            field[4] = "detail";
            field[5] = "providerName";
            //Creating array for data
            String[] data = new String[6];
            data[0] = service.getName();
            data[1] = service.getPrice();
            data[2] = service.getMinDuration();
            data[3] = service.getPhoto();
            data[4] = service.getDetail();
            data[5] = service.getProviderName();
            PutData putData = new PutData(MainActivity.IP+"addService.php", "POST", field, data);
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                    if(result.equals("adding success")){
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        MainActivity.allServices.add(service);
                        startActivity(new Intent(getApplicationContext(), ServiceProviderActivity.class));
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
        CaptionedPicturesAdapter adapter = new CaptionedPicturesAdapter(MainActivity.serviceImages, "service");
        recycler.setAdapter(adapter);
    }
}