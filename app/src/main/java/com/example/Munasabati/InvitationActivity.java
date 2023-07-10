package com.example.Munasabati;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InvitationActivity extends AppCompatActivity {

    private TextView eventName, eventTime, eventLocation;
    private ImageView qrCode, eventImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);

        Bundle b = getIntent().getExtras();
        String qr="null";
        if(b != null)
            qr = b.getString("qr");
        Invitation selectedInvitation= null;
        Event selectedEvent= null;

        for (int i = 0; i < MainActivity.allInvitations.size(); i++) {
            if(MainActivity.allInvitations.get(i).getQr().equals(qr)){
                selectedInvitation=MainActivity.allInvitations.get(i);
                break;
            }
        }

        for (int i = 0; i < MainActivity.allEvents.size(); i++) {
            if(MainActivity.allEvents.get(i).getName().equals(selectedInvitation.getEventName())){
                selectedEvent=MainActivity.allEvents.get(i);
                break;
            }
        }


        eventImage= findViewById(R.id.imageViewInvitation);
        eventName= findViewById(R.id.textViewInvitationEventName);
        eventLocation= findViewById(R.id.textViewInvitationEventLocation);
        eventTime= findViewById(R.id.textViewInvitationEventDate);
        qrCode= findViewById(R.id.imageViewInvitationQR);

        eventName.setText(selectedEvent.getName());
        eventLocation.setText(selectedEvent.getLocation());

        String strDate="";

        try {
            Date date1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(selectedEvent.getDate());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm aa");
            strDate = dateFormat.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        eventTime.setText(strDate);
        Drawable drawable= Drawable.createFromStream(getClass().getClassLoader().getResourceAsStream("res/drawable/" + selectedEvent.getPhoto() + ".png"), null);
        eventImage.setImageDrawable(drawable);

        //*********************** QR Code processing ********************

        MultiFormatWriter writer= new MultiFormatWriter();
        MultiFormatReader reader= new MultiFormatReader();
        try {
            BitMatrix matrix= writer.encode(selectedInvitation.getQr(), BarcodeFormat.QR_CODE,350,350);
            BarcodeEncoder encoder= new BarcodeEncoder();
            Bitmap bitmap= encoder.createBitmap(matrix);
            qrCode.setImageBitmap(bitmap);

        } catch (WriterException  e) {
            e.printStackTrace();
        }

            //********************* Reading QR From imageview ***************************
       /* Bitmap bMap=((BitmapDrawable)qrCode.getDrawable()).getBitmap();
        int[] intArray = new int[bMap.getWidth()*bMap.getHeight()];

        bMap.getPixels(intArray, 0, bMap.getWidth(), 0, 0, bMap.getWidth(), bMap.getHeight());

        LuminanceSource source = new RGBLuminanceSource(bMap.getWidth(), bMap.getHeight(),intArray);

        BinaryBitmap bitmapx = new BinaryBitmap(new HybridBinarizer(source));
        Result matrix1= null;
        try {
            matrix1 = reader.decode(bitmapx);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, ""+matrix1.getText(), Toast.LENGTH_SHORT).show();
*/

    }
}