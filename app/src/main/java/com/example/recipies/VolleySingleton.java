package com.example.recipies;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    RequestQueue requestQueue;
    private static VolleySingleton Instance;
    private VolleySingleton(Context context){
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }
    public static synchronized VolleySingleton getInstance(Context context){
        if(Instance==null){
            Instance = new VolleySingleton(context);
        }
        return Instance;
    }
    public RequestQueue getRequestQueue(){
        return requestQueue;
    }
}
