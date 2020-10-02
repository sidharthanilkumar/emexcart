package com.staging.emexcart.Network;

import com.staging.emexcart.models.product_model.ProductDetails;
import com.staging.emexcart.models.user_model.UserData;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    @GET("wp-json/wc/v3/products")
    Call<List<ProductDetails>> getAllProducts(@QueryMap Map<String, String> args);

    @GET("wp-json/wc/v3/products/{id}")
    Call<ProductDetails> getSingleProducts(@Path("id") String product_id);

    @FormUrlEncoded
    @POST("wp-json/jwt-auth/v1/token")
    Call<UserData> processLogin(
            @Field("username") String customers_username,
            @Field("password") String customers_password);
}
