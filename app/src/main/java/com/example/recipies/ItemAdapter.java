package com.example.recipies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
//tao adapter de hien thi du lieu cua cac thuoc tinh trong recyclerview
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {
    Context context;
    List<Item> items;

    public ItemAdapter(Context context, List<Item> items){
        this.items = items;
        this.context = context;
    }
    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_tag, parent, false);
        return new ItemHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Item item = items.get(position);
        holder.itemName.setText(item.getName());
        Glide.with(context).load(item.getImg_url()).into(holder.itemImage);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", item.getName());
                bundle.putString("img_url", item.getImg_url());
                bundle.putString("recipe", item.getRecipe());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        TextView itemName;
        TextView itemRecipe;
        ImageView itemImage;
        View view;
        ConstraintLayout constraintLayout;
        public ItemHolder(@NonNull View itemview){
            super(itemview);
            view = itemview;
            constraintLayout = view.findViewById(R.id.item);
            itemName = view.findViewById(R.id.itemview);
            itemImage = view.findViewById(R.id.item_img);
        }
    }
}
