package com.example.Munasabati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class AdminProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        EditText txtUsername = findViewById(R.id.edProfileUsername);
        EditText txtPhone = findViewById(R.id.edProfilePhone);
        EditText txtEmail = findViewById(R.id.edProfileEmail);
        EditText txtPassword = findViewById(R.id.edProfilePass);
        EditText txtConfirmPass = findViewById(R.id.edProfilePassConf);
        Button update= findViewById(R.id.buttonProfileUpdate);

        if(MainActivity.currentUser!=null){
            txtUsername.setText(MainActivity.username);
            txtPhone.setText(MainActivity.currentUser.getPhone());
            txtEmail.setText(MainActivity.currentUser.getEmail());
            txtUsername.setEnabled(false);
        }

        update.setOnClickListener(v -> {
            String username= MainActivity.username; //you can't change username, (it is the primary key!)
            String email= String.valueOf(txtEmail.getText());
            String phone= String.valueOf(txtPhone.getText());
            String password= String.valueOf(txtPassword.getText());
            String confirmPassword= String.valueOf(txtConfirmPass.getText());


            if(!password.equals(confirmPassword)){
                Toast.makeText(getApplicationContext(), "Password and confirm password don't match!", Toast.LENGTH_SHORT).show();
                return;
            }

            if(username.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() ){
                Toast.makeText(getApplicationContext(), "Some required fields are blank!", Toast.LENGTH_SHORT).show();
                return;
            }
            Handler handler = new Handler(Looper.getMainLooper());
            String finalPassword = password;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[4];
                    field[0] = "username";
                    field[1] = "email";
                    field[2] = "password";
                    field[3] = "phone";
                    //Creating array for data
                    String[] data = new String[4];
                    data[0] = username;
                    data[1] = email;
                    data[2] = finalPassword;
                    data[3] = phone;
                    PutData putData = new PutData(MainActivity.IP+"updateUser.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                            if (result.equals("Update Success")){
                            MainActivity.currentUser.setEmail(email);
                            MainActivity.currentUser.setPhone(phone);
                            MainActivity.currentUser.setPass(finalPassword);
                            }

                        }
                    }
                    //End Write and Read data with URL
                }
            });
        });



        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_AdminProfile);
        bottomNavigationView.setSelectedItemId(R.id.navigation_Profile);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_Profile:
                    return true;

                case R.id.navigation_Calendar:
                    startActivity(new Intent(getApplicationContext() , CalendarActivity.class));
                    finish();
                    //HomeActivity.bottomNavigationView.setSelectedItemId(R.id.navigation_Services);
                    overridePendingTransition(0,0);
                    return true;

                case R.id.navigation_Requests:
                    startActivity(new Intent(getApplicationContext() , AdminRequestsActivity.class));
                    finish();
                    overridePendingTransition(0,0);
                    return true;

                case R.id.navigation_Home:
                    //startActivity(new Intent(getApplicationContext() , NotificationsActivity.class));
                    finish();
                    HomeAdminActivity.bottomNavigationView.setSelectedItemId(R.id.navigation_Home);
                    overridePendingTransition(0,0);
                    return true;

            }
            return false;
        });

    }
}
