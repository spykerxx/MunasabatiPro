package com.example.Munasabati;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

public class QRScannerActivity extends AppCompatActivity {
    TextView result, eventName;
    Button scan;
    Spinner spinner;
    Invitation scannedInvitation=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        result = findViewById(R.id.textViewQrResult);
        eventName = findViewById(R.id.textViewEventNameQR);
        scan = findViewById(R.id.buttonScanQR);
        spinner= findViewById(R.id.spinnerEventName);
        spinner.getBackground().setColorFilter(getResources().getColor(R.color.newcolor), PorterDuff.Mode.SRC_ATOP);

        List<String> spinnerArray= new ArrayList<>();
        for (int i = 0; i < MainActivity.allEvents.size(); i++) {
            spinnerArray.add(MainActivity.allEvents.get(i).getName());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        scan.setOnClickListener(v -> {
            IntentIntegrator intentIntegrator = new IntentIntegrator(this);
            intentIntegrator.setPrompt("Scan the QR Code on Invitation");
            intentIntegrator.setOrientationLocked(true);
            intentIntegrator.initiateScan();
        });

    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            // if the intentResult is null then
            // toast a message as "cancelled"
            if (intentResult != null) {
                if (intentResult.getContents() == null) {
                    Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                } else {
                    // if the intentResult is not null we'll set
                    result.setText(intentResult.getContents());
                    result.setTextColor(Color.GREEN);
                    for (int i = 0; i < MainActivity.allInvitations.size(); i++) {
                        if(intentResult.getContents().equals(MainActivity.allInvitations.get(i).getQr())){
                            scannedInvitation=MainActivity.allInvitations.get(i);
                            break;
                        }
                    }

                        String selected = spinner.getSelectedItem().toString();
                        if (scannedInvitation!=null && selected.equals(scannedInvitation.getEventName())) {
                            result.setText("Valid!");
                            result.setTextColor(Color.GREEN);
                        }
                        else{
                            result.setText("Invalid!");
                            result.setTextColor(Color.RED);
                        }
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }