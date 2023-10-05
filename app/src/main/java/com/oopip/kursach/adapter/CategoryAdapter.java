package com.oopip.kursach.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oopip.kursach.R;
import com.oopip.kursach.RecyclerViewInterface;
import com.oopip.kursach.activity_potato_menu;
import com.oopip.kursach.burgers_menu;
import com.oopip.kursach.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>  implements RecyclerViewInterface {

    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.categories = categories;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryItems = LayoutInflater.from(context).inflate(R.layout.categoty_item, parent,false);
        return new CategoryViewHolder(categoryItems, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        holder.categoryTitle.setText(categories.get(position).getTitle());
        System.out.println(position);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    @Override
    public void onItemClick(int position) {

    }

    public static final class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView categoryTitle;


        public CategoryViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            categoryTitle = itemView.findViewById(R.id.categoryTitle);

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
