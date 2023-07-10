package com.example.Munasabati;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class CaptionedRequestsAdapter
        extends RecyclerView.Adapter<CaptionedRequestsAdapter.ViewHolder> {
    private Context context;
    private List<EventRequest> items;
    private int layoutID;


    public CaptionedRequestsAdapter(Context context, List<EventRequest> items, int layoutID) {
        this.context = context;
        this.items = items;
        this.layoutID = layoutID;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(layoutID,
                parent,
                false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final EventRequest eventRequest = items.get(position);
        CardView cardView = holder.cardView;

        if (layoutID == R.layout.card_captioned_request) {

            TextView txtEventName = cardView.findViewById(R.id.tv_CardRequestEventName);
            txtEventName.setText(eventRequest.getEventName());

            TextView txtQuit = cardView.findViewById(R.id.tv_quit);
            txtQuit.setOnClickListener(v -> {
                cardView.setVisibility(View.GONE);

                for (int i = 0; i < MainActivity.allRequests.size(); i++) {
                    if (MainActivity.allRequests.get(i).getEventName().equals(eventRequest.getEventName())) {
                        MainActivity.allRequests.remove(i);
                        break;
                    }

                }
            });
        } else if (layoutID == R.layout.layout_admin_requests) {
            TextView txtEventName = cardView.findViewById(R.id.tv_AdminEventName);
            TextView txtClientName = cardView.findViewById(R.id.tv_AdminClient);
            ImageView eventPhoto = cardView.findViewById(R.id.iv_AdminEventImage);
            Button accept = cardView.findViewById(R.id.btnAdminAccept);
            Button reject = cardView.findViewById(R.id.btnAdminReject);

            String clientName = eventRequest.getClientName();
            String eventName = eventRequest.getEventName();

            txtEventName.setText(eventRequest.getEventName());

            txtClientName.setText(clientName);
            Event selectedEvent = null;
            for (int i = 0; i < MainActivity.allEvents.size(); i++) {
                if (MainActivity.allEvents.get(i).getName().equals(eventName)) {
                    selectedEvent = MainActivity.allEvents.get(i);
                    break;
                }
            }

            Drawable drawable = Drawable.createFromStream(getClass().getClassLoader().getResourceAsStream("res/drawable/" + selectedEvent.getPhoto() + ".png"), null);
            eventPhoto.setImageDrawable(drawable);

            String timeStamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());

            accept.setOnClickListener(v -> {
                Invitation invitation = new Invitation(clientName, eventName, timeStamp);
                String qr= clientName + eventName + (MainActivity.allInvitations.size() + 1);
                invitation.setQr(qr);
                MainActivity.allInvitations.add(invitation);
                pushInvitation(clientName, eventName, timeStamp, qr);

                //****************** add notification ***********************************

                EventNotification notification= new EventNotification(clientName, "Approved Request", "You have an approved request event. check your invitations", timeStamp);
                MainActivity.allNotifications.add(notification);
                pushNotification(notification);

                //******************* remove view ***************************************
                items.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, items.size());
                removeRequest(clientName, eventName);
            });
            //*********************************************************************************************

            reject.setOnClickListener(v -> {
                EventNotification notification= new EventNotification(clientName, "Rejected Request", "Your request to attend event "+eventName+"is rejected!", timeStamp);
                MainActivity.allNotifications.add(notification);
                pushNotification(notification);
                items.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, items.size());
                removeRequest(clientName, eventName);
            });
        }


        cardView.setOnClickListener(v -> {
            //add actions here..
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }

    }

    private void pushInvitation(String name, String event, String time, String qr){

    Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[4];
                field[0] = "clientName";
                field[1] = "eventName";
                field[2] = "time";
                field[3] = "qr";

                //Creating array for data
                String[] data = new String[4];
                data[0] = name;
                data[1] = event;
                data[2] = time;
                data[3] = qr;

                PutData putData = new PutData(MainActivity.IP+"addInvitation.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                       // Toast.makeText(null, result, Toast.LENGTH_SHORT).show();

                    }
                }
                //End Write and Read data with URL
            });
    }

    private void pushNotification(EventNotification notification){

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[4];
                field[0] = "username";
                field[1] = "title";
                field[2] = "comment";
                field[3] = "time";

                //Creating array for data
                String[] data = new String[4];
                data[0] = notification.getClientName();
                data[1] = notification.getTitle();
                data[2] = notification.getComment();
                data[3] = notification.getTime();

                PutData putData = new PutData(MainActivity.IP+"addNotification.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                       // Toast.makeText(null, result, Toast.LENGTH_SHORT).show();

                    }
                }
                //End Write and Read data with URL
            }
        });
    }

    private void removeRequest(String clientName, String eventName){

        for (int i = 0; i < MainActivity.allRequests.size(); i++) {
            if (MainActivity.allRequests.get(i).getClientName().equals(clientName) && MainActivity.allRequests.get(i).getEventName().equals(eventName)){
                MainActivity.allRequests.remove(i);
                break;
            }
        }

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[2];
                field[0] = "clientName";
                field[1] = "eventName";


                //Creating array for data
                String[] data = new String[2];
                data[0] = clientName;
                data[1] = eventName;


                PutData putData = new PutData(MainActivity.IP+"removeRequest.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        // Toast.makeText(null, result, Toast.LENGTH_SHORT).show();

                    }
                }
                //End Write and Read data with URL
            }
        });
    }


}

