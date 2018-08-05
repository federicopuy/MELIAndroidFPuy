package com.example.federico.mlibrefedericopuy;

import android.app.Application;
import android.content.Context;
import com.example.federico.mlibrefedericopuy.network.ApiInterface;
import com.example.federico.mlibrefedericopuy.network.RetrofitClient;

public class AppController extends Application {

    private ApiInterface apiInterface;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private static AppController get(Context context) {
        return (AppController) context.getApplicationContext();
    }

    public static AppController create(Context context) {
        return AppController.get(context);
    }

    public ApiInterface getApiInterface() {
        if(apiInterface == null) {
            apiInterface = RetrofitClient.getClient();
        }
        return apiInterface;
    }

}
