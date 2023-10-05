package com.oopip.kursach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oopip.kursach.R;
import com.oopip.kursach.RecyclerViewInterface;
import com.oopip.kursach.model.Soda;

import java.util.List;

public class SodaAdapter extends RecyclerView.Adapter<SodaAdapter.SodaViewHolder> implements RecyclerViewInterface {

    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    List<Soda> sodas;

    public SodaAdapter(Context context, List<Soda> sodas, RecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.sodas = sodas;
    }

    @NonNull
    @Override
    public SodaAdapter.SodaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View sodaItems = LayoutInflater.from(context).inflate(R.layout.soda_item, parent,false);
        return new SodaAdapter.SodaViewHolder(sodaItems, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull SodaAdapter.SodaViewHolder holder, int position) {
        holder.sodaTitle.setText(sodas.get(position).getTitle());
        holder.sodaPrice.setText(sodas.get(position).getPrice());
        holder.sodaCompound.setText(sodas.get(position).getCompound());
    }

    @Override
    public int getItemCount() {
        return sodas.size();
    }

    @Override
    public void onItemClick(int position) {

    }

    public static final class SodaViewHolder extends RecyclerView.ViewHolder{

        TextView sodaTitle, sodaPrice, sodaCompound;

        public SodaViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            sodaTitle = itemView.findViewById(R.id.SodaTitle);
            sodaPrice = itemView.findViewById(R.id.sodaPrice);
            sodaCompound = itemView.findViewById(R.id.sodakkal);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }

}
