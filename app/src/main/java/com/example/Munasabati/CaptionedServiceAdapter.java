package com.example.Munasabati;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CaptionedServiceAdapter
        extends RecyclerView.Adapter<CaptionedServiceAdapter.ViewHolder>{
    private Context context;
    private List<Service> items;


    public CaptionedServiceAdapter(Context context, List<Service> items){
        this.context = context;
        this.items = items;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_service,
                parent,
                false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Service services = items.get(position);
        CardView cardView = holder.cardView;
        ImageView serviceImage= cardView.findViewById(R.id.iv_CardServiceImage);
        TextView txtName = cardView.findViewById(R.id.tv_CardServiceName);
        TextView txtProviderName = cardView.findViewById(R.id.tv_CardServiceProvider);
        TextView txtPrice = cardView.findViewById(R.id.tv_CardServicePrice);

        txtName.setText(services.getName());
        txtProviderName.setText(services.getProviderName());
        txtPrice.setText(""+services.getPrice());
        txtName.setText(services.getName());

        Drawable drawable= Drawable.createFromStream(getClass().getClassLoader().getResourceAsStream("res/drawable/" + services.getPhoto() + ".png"), null);;
        serviceImage.setImageDrawable(drawable);

        cardView.setOnClickListener(v -> {
            //add action here..
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

