package com.example.recipies;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class FoodActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_frag);

        ImageView imageView = findViewById(R.id.food_post);
        TextView nameView = findViewById(R.id.foodname);
        TextView recipeView = findViewById(R.id.recipe);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String img_url = bundle.getString("img_url");
        String recipe = bundle.getString("recipe");

        Glide.with(this).load(img_url).into(imageView);
        nameView.setText(name);
        recipeView.setText(recipe);
    }
}
