package com.example.dinnerapp.connection;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {

    private static MySingleton mInstance;
    private static RequestQueue requestQueue;
    private static Context mCtx;

    private MySingleton(Context context){

        this.mCtx = context;
        requestQueue = getRequestque();

    }

    public RequestQueue getRequestque(){

        if(requestQueue == null){

            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());

        }
        return requestQueue;

    }

    public static synchronized MySingleton getInstance(Context context){

        if( mInstance == null){
            mInstance = new MySingleton(context);
        }

        return mInstance;

    }
    public<T> void addToRequestque(Request<T> request){

        requestQueue.add(request);

    }

}
