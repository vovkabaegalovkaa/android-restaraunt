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
import com.oopip.kursach.model.Burger;

import java.util.List;

public class BurgerAdapter extends RecyclerView.Adapter<BurgerAdapter.BurgerViewHolder> implements RecyclerViewInterface {

    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    List<Burger> burgers;

    public BurgerAdapter(Context context, List<Burger> burgers, RecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.burgers = burgers;
    }

    @NonNull
    @Override
    public BurgerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View burgerItems = LayoutInflater.from(context).inflate(R.layout.burger_item, parent,false);
        return new BurgerAdapter.BurgerViewHolder(burgerItems, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull BurgerViewHolder holder, int position) {
        holder.burgerTitle.setText(burgers.get(position).getTitle());
        holder.burgerPrice.setText(burgers.get(position).getPrice());
        holder.burgerCompound.setText(burgers.get(position).getCompound());
    }

    @Override
    public int getItemCount() {
        return burgers.size();
    }

    @Override
    public void onItemClick(int position) {

    }

    public static final class BurgerViewHolder extends RecyclerView.ViewHolder{

        TextView burgerTitle, burgerPrice, burgerCompound;

        public BurgerViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            burgerTitle = itemView.findViewById(R.id.burgerTitle);
            burgerPrice = itemView.findViewById(R.id.burgerPrice);
            burgerCompound = itemView.findViewById(R.id.burgerkkal);

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
