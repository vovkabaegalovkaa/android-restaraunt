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
import com.oopip.kursach.model.Sauce;

import java.util.List;

public class SauceAdapter  extends RecyclerView.Adapter<SauceAdapter.SauceViewHolder> implements RecyclerViewInterface {

    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    List<Sauce> sauces;

    public SauceAdapter(Context context, List<Sauce> sauces, RecyclerViewInterface recyclerViewInterface){
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.sauces = sauces;
    }

    @NonNull
    @Override
    public SauceAdapter.SauceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View sauceItems = LayoutInflater.from(context).inflate(R.layout.sauce_item, parent,false);
        return new SauceAdapter.SauceViewHolder(sauceItems, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull SauceAdapter.SauceViewHolder holder, int position) {
        holder.sauceTitle.setText(sauces.get(position).getTitle());
        holder.saucePrice.setText(sauces.get(position).getPrice());
        holder.sauceCompound.setText(sauces.get(position).getCompound());
    }

    @Override
    public int getItemCount() {
        return sauces.size();
    }

    @Override
    public void onItemClick(int position) {

    }

    public static final class SauceViewHolder extends RecyclerView.ViewHolder{

        TextView sauceTitle, saucePrice, sauceCompound;

        public SauceViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            sauceTitle = itemView.findViewById(R.id.SauceTitle);
            saucePrice = itemView.findViewById(R.id.saucePrice);
            sauceCompound = itemView.findViewById(R.id.saucekkal);

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
