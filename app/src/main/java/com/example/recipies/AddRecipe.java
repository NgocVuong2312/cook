package com.example.recipies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class AddRecipe extends AppCompatActivity {
    private EditText recipeName;
    private EditText recipeDescription;
    private Button addButton;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.add_recipe);
        recipeName = findViewById(R.id.recipename);
        recipeDescription = findViewById(R.id.recipedes);
        addButton = findViewById(R.id.additem);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://localhost:3000/searchResults";
                String name = recipeName.getText().toString();
                String recipe = recipeDescription.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        response -> Toast.makeText(AddRecipe.this, "Success", Toast.LENGTH_SHORT).show(),
                        error -> Toast.makeText(AddRecipe.this, "Failed", Toast.LENGTH_SHORT).show()){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("name", name);
                        params.put("recipe", recipe);
                        return params;
                    }
                };
                requestQueue = Volley.newRequestQueue(AddRecipe.this);
                requestQueue.add(stringRequest);
            }
        });
    }
}

