package com.example.recipies;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Item> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.itemlist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
        items = new ArrayList<>();
        fetchData();
    }
    private void fetchData() {
        String url="https://api.spoonacular.com/food/search?apiKey=99cf38df9cef439fa966bf01710ba698";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray searchResults = response.getJSONArray("searchResults");

                    for (int i = 0; i < searchResults.length(); i++) {
                        JSONObject category = searchResults.getJSONObject(i);
                        JSONArray results = category.getJSONArray("results");

                        for (int j = 0; j < results.length(); j++) {
                            JSONObject recipe = results.getJSONObject(j);
                            String name = recipe.getString("name");
                            String img_url = recipe.getString("image");
                            //String food_url = recipe.getString("foodUrl");
                            String food_recipe = recipe.getString("content");

                            items.add(new Item(name, img_url, food_recipe));

                            ItemAdapter adapter = new ItemAdapter(MainActivity.this, items);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}