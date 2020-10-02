package com.staging.emexcart.Network;

import android.content.Context;

import com.staging.emexcart.Utils.Constants;
import com.staging.emexcart.oauth.BasicOAuth;
import com.staging.emexcart.oauth.OAuthInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit retrofit = null;
    private static ApiInterface apiRequests;
    private static final String BASE_URL = Constants.WOOCOMMERCE_URL;





    public static Retrofit getClient() {
        if (retrofit == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OAuthInterceptor oauth1Woocommerce = new OAuthInterceptor.Builder()
                    .consumerKey(Constants.WOOCOMMERCE_CONSUMER_KEY)
                    .consumerSecret(Constants.WOOCOMMERCE_CONSUMER_SECRET)
                    .build();

            BasicOAuth basicOAuthWoocommerce = new BasicOAuth.Builder()
                    .consumerKey(Constants.WOOCOMMERCE_CONSUMER_KEY)
                    .consumerSecret(Constants.WOOCOMMERCE_CONSUMER_SECRET)
                    .build();

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(BASE_URL.startsWith("http://")?  oauth1Woocommerce : basicOAuthWoocommerce)
                    .build();
           /* OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(BASE_URL.startsWith("http://")?  oauth1Woocommerce : basicOAuthWoocommerce)
                    .addInterceptor(interceptor)
                    .build();*/


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


//            apiRequests = retrofit.create(ApiInterface.class);

        }
        return retrofit;
    }
    public static <S> S createService(Class<S> serviceClass, Context context) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OAuthInterceptor oauth1Woocommerce = new OAuthInterceptor.Builder()
                .consumerKey(Constants.WOOCOMMERCE_CONSUMER_KEY)
                .consumerSecret(Constants.WOOCOMMERCE_CONSUMER_SECRET)
                .build();

        BasicOAuth basicOAuthWoocommerce = new BasicOAuth.Builder()
                .consumerKey(Constants.WOOCOMMERCE_CONSUMER_KEY)
                .consumerSecret(Constants.WOOCOMMERCE_CONSUMER_SECRET)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(BASE_URL.startsWith("http://")?  oauth1Woocommerce : basicOAuthWoocommerce)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory( GsonConverterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }

}
