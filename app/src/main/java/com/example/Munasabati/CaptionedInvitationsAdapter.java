package com.example.Munasabati;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CaptionedInvitationsAdapter
        extends RecyclerView.Adapter<CaptionedInvitationsAdapter.ViewHolder>{
    private Context context;
    private List<Invitation> items;



    public CaptionedInvitationsAdapter(Context context, List<Invitation> items){
        this.context = context;
        this.items = items;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_notification,
                parent,
                false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Invitation invitation = items.get(position);
        CardView cardView = holder.cardView;

        cardView.setTag(invitation.getQr());

        TextView txtComment = cardView.findViewById(R.id.tv_notificationDetail);
        txtComment.setText(txtComment.getText()+" "+invitation.getEventName());

        TextView txtTime = cardView.findViewById(R.id.tv_time);

        //*********** setting up 'time ago' for notification **************
        Date date1=null;

        try {
            date1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(invitation.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PrettyTime prettyTime = new PrettyTime(Locale.getDefault());
        String ago = prettyTime.format(date1);
        txtTime.setText(ago);

        //*****************************************************************

        cardView.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public ViewHolder(CardView cardView){
            super(cardView);
            this.cardView = cardView;
        }

    }
}

