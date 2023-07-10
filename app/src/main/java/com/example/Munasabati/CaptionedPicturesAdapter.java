package com.example.Munasabati;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CaptionedPicturesAdapter
        extends RecyclerView.Adapter<CaptionedPicturesAdapter.ViewHolder>{
    private List<EventImage> items;
    private String type;



    public CaptionedPicturesAdapter(List<EventImage> items, String type){
        this.items = items;
        this.type=type;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_pics,
                parent,
                false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final EventImage Events = items.get(position);
        CardView cardView = holder.cardView;
        ImageView selectedImage= cardView.findViewById(R.id.iv_selectImage);
        Drawable drawable= Drawable.createFromStream(getClass().getClassLoader().getResourceAsStream("res/drawable/" + Events.getSource() + ".png"), null);
        selectedImage.setImageDrawable(drawable);
        cardView.setTag(Events.getSource());



        cardView.setOnClickListener(v -> {
            if (type.equals("event"))
            newEventActivity.edPhoto.setText(cardView.getTag().toString());
            else newServiceActivity.edPhoto.setText(cardView.getTag().toString());

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

