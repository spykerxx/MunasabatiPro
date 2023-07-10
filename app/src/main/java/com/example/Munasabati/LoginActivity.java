package com.example.Munasabati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private CheckBox checkBox;
    private Spinner spinner;
    private int typeSelection=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView dontHaveAccount = findViewById(R.id.imageViewDontHave);
        editTextUsername= findViewById(R.id.editTextLoginUser);
        editTextPassword= findViewById(R.id.editTextLoginPass);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        checkBox= findViewById(R.id.checkBoxLogin);
        spinner= findViewById(R.id.spinnerLogin);
       // spinner.getBackground().setColorFilter((Color.WHITE), PorterDuff.Mode.SRC_ATOP);
        dontHaveAccount.setOnClickListener(view -> {
            Intent intent=new Intent(LoginActivity .this , SignupActivity.class);
            startActivity(intent);
            finish();

        });

        autoLogin();

        buttonLogin.setOnClickListener(view -> {
           login();

        });
    }

    private void autoLogin(){
        if (!MainActivity.username.isEmpty()){
            editTextUsername.setText(MainActivity.username);
            editTextPassword.setText(MainActivity.password);
            spinner.setSelection(MainActivity.typeSpinnerSelection);
            checkBox.setChecked(true);
            //login();
        }
    }

    private void rememberMe(){
    if (checkBox.isChecked()) saveSharedPref();
    else clearSharedPrefe();
    }

    private void saveSharedPref(){
        SharedPreferences sharedPreferences= getSharedPreferences(MainActivity.SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(MainActivity.USER_PREFS,editTextUsername.getText().toString());
        editor.putString(MainActivity.PASS_PREFS,editTextPassword.getText().toString());
        editor.putInt(MainActivity.TYPE_PREFS,spinner.getSelectedItemPosition());
        editor.apply();

    }

    private void clearSharedPrefe(){
        SharedPreferences sharedPreferences= getSharedPreferences(MainActivity.SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(MainActivity.USER_PREFS,"");
        editor.putString(MainActivity.PASS_PREFS,"");
        editor.putInt(MainActivity.TYPE_PREFS,-1);
        editor.apply();

    }

    private void login(){
        String username= String.valueOf(editTextUsername.getText());
        String password= String.valueOf(editTextPassword.getText());
        String selectedType= spinner.getSelectedItem().toString();
        System.out.println(selectedType+" fuck");

        if(username.isEmpty()  || password.isEmpty() ){
            Toast.makeText(getApplicationContext(), "Please make sure all fields are not empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[3];
                field[0] = "username";
                field[1] = "password";
                field[2] = "type";
                //Creating array for data
                String[] data = new String[3];
                data[0] = username;
                data[1] = password;
                data[2] = selectedType;
                PutData putData = new PutData(MainActivity.IP+"login.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if(result.equals("Login Success")){
                            rememberMe();

                           // Bundle b = new Bundle();
                            //b.putString("username", username); //Your id
                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                           loginType(selectedType);

                            if (!MainActivity.username.equals(username))
                                MainActivity.username=username;
                                MainActivity.typeSpinnerSelection=typeSelection;
                                MainActivity.getCurrentUser();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                //End Write and Read data with URL
            });

        }
    }

    private void loginType(String type){
        switch (type){
            case "Client": {startActivity(new Intent(getApplicationContext(), HomeActivity.class)); finish(); break;}
            case "Admin": {startActivity(new Intent(getApplicationContext(), HomeAdminActivity.class)); finish(); break;}
            case "Service Provider": {startActivity(new Intent(getApplicationContext(), ServiceProviderActivity.class)); finish(); break;}
            case "Qr Scanner": {startActivity(new Intent(getApplicationContext(), QRScannerActivity.class)); finish(); break;}
            default: break;
        }
    }

}