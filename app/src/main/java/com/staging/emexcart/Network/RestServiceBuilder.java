package com.staging.emexcart.Network;

import android.content.Context;

public class RestServiceBuilder {
    private static ApiInterface service;

    public  static ApiInterface getService(Context context){
        service = ApiClient.createService(ApiInterface.class,context);
        return service;
    }
}
