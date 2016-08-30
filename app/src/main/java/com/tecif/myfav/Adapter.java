package com.tecif.myfav;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.itemViewHolder> {

    public static class itemViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView itemTitulo;
        TextView itemDescripcion;
        RatingBar itemRank;
        ImageView itemPhoto;

        itemViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            itemTitulo = (TextView)itemView.findViewById(R.id.item_tittle);
            itemDescripcion = (TextView)itemView.findViewById(R.id.item_description);
            itemPhoto = (ImageView) itemView.findViewById(R.id.item_photo);
            itemRank=(RatingBar)itemView.findViewById(R.id.item_rating);
        }
    }

    List<Item> items;

    Adapter(List<Item> items){
        this.items = items;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public itemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_desc, viewGroup, false);
        itemViewHolder pvh = new itemViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(itemViewHolder itemViewHolder, int i) {
        itemViewHolder.itemTitulo.setText(items.get(i).titulo);
        itemViewHolder.itemDescripcion.setText(items.get(i).descripcion);
        itemViewHolder.itemPhoto.setImageResource(items.get(i).photoId);
        itemViewHolder.itemRank.setRating(items.get(i).rank);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
