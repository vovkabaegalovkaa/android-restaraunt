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
import com.oopip.kursach.model.Potato;

import java.util.List;

public class PotatoAdapter extends RecyclerView.Adapter<PotatoAdapter.PotatoViewHolder> implements RecyclerViewInterface {

    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    List<Potato> potatoes;

    public PotatoAdapter(Context context, List<Potato> potatoes, RecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.potatoes = potatoes;
    }

    @NonNull
    @Override
    public PotatoAdapter.PotatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View potatoItems = LayoutInflater.from(context).inflate(R.layout.potato_item, parent,false);
        return new PotatoAdapter.PotatoViewHolder(potatoItems, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull PotatoViewHolder holder, int position) {
        holder.potatoTitle.setText(potatoes.get(position).getTitle());
        holder.potatoPrice.setText(potatoes.get(position).getPrice());
        holder.potatoCompound.setText(potatoes.get(position).getCompound());
    }

    @Override
    public int getItemCount() {
        return potatoes.size();
    }

    @Override
    public void onItemClick(int position) {

    }

    public static final class PotatoViewHolder extends RecyclerView.ViewHolder{

        TextView potatoTitle, potatoPrice, potatoCompound;

        public PotatoViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            potatoTitle = itemView.findViewById(R.id.PotatoTitle);
            potatoPrice = itemView.findViewById(R.id.potatoPrice);
            potatoCompound = itemView.findViewById(R.id.potatokkal);

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
