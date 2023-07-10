package com.example.Munasabati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;
import android.widget.Toast;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class ForgotPassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        sendResetPasswordCode(getApplicationContext());
        TextView resend= findViewById(R.id.TextViewResend);
        resend.setOnClickListener(v -> sendResetPasswordCode(getApplicationContext()));


    }

    public static void sendResetPasswordCode(Context context){

        final String username= "hamada-g1997k@hotmail.com";
        final String password= "0599730177";

        String to= "mohammedgh30@gmail.com";
        String subject= "Password Reset";
        String messageToSend= "Your reset password code is 12345";

        Properties props= new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.live.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");



        Session session= Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message= new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(messageToSend);


            Transport.send(message);

            Toast.makeText(context, "A Reset password email is sent successfully!", Toast.LENGTH_SHORT).show();
        } catch (MessagingException e) {
            e.printStackTrace();
            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
        }
       // Toast.makeText(context, "at least it is being called!", Toast.LENGTH_SHORT).show();
    }
}