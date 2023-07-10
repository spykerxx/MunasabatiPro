package com.example.Munasabati;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class SignupActivity extends AppCompatActivity {
    private ImageView alreadyHaveAccount;
    private EditText editTextUsername, editTextEmail, editTextPassword, editTextConfirmPassword, editTextPhone;
    private Button buttonSignup;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        alreadyHaveAccount= findViewById(R.id.imageViewAlreadyHave);
        editTextUsername= findViewById(R.id.editTextTextPersonName);
        editTextEmail= findViewById(R.id.editTextEmail);
        editTextPassword= findViewById(R.id.editTextSignupPass);
        editTextConfirmPassword= findViewById(R.id.editTextSignupPassConf);
        editTextPhone= findViewById(R.id.editTextTextPhone);
        buttonSignup= findViewById(R.id.buttonSignUp);
        spinner= findViewById(R.id.spinnerSignup);



        ScrollView scrollView= findViewById(R.id.scrollView2);
        scrollView.setSmoothScrollingEnabled(true);


        buttonSignup.setOnClickListener(view -> {

            String username= String.valueOf(editTextUsername.getText());
            String email= String.valueOf(editTextEmail.getText());
            String password= String.valueOf(editTextPassword.getText());
            String phone= String.valueOf(editTextPhone.getText());
            String confirmPassword= String.valueOf(editTextConfirmPassword.getText());
            String type= spinner.getSelectedItem().toString();

            if(!password.equals(confirmPassword)){
                Toast.makeText(getApplicationContext(), "Password and confirm password don't match!", Toast.LENGTH_SHORT).show();
                return;
            }

            if(username.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() ){
                Toast.makeText(getApplicationContext(), "Some required fields are blank!", Toast.LENGTH_SHORT).show();
                return;
            }

            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[5];
                    field[0] = "username";
                    field[1] = "email";
                    field[2] = "password";
                    field[3] = "phone";
                    field[4] = "type";
                    //Creating array for data
                    String[] data = new String[5];
                    data[0] = username;
                    data[1] = email;
                    data[2] = password;
                    data[3] = phone;
                    data[4] = type;
                    PutData putData = new PutData(MainActivity.IP+"signup.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                          if(result.equals("Sign Up Success")){
                              Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                              Intent intent=new Intent(SignupActivity.this , LoginActivity.class);
                              startActivity(intent);
                              finish();
                              //****************** applying current user ********************************
                              User user= new User(username, phone, email, password, type);
                              MainActivity.allUsers.add(user);
                              MainActivity.getCurrentUser();
                              //*************************************************************************
                          }
                          else{
                              Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                          }

                        }
                    }
                    //End Write and Read data with URL
                }
            });

        });

        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignupActivity.this , LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });






    }
}