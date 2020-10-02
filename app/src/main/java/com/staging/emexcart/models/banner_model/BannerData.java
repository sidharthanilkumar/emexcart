package com.staging.emexcart.models.banner_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class BannerData {
    
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<BannerDetails> data = null;
    
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public List<BannerDetails> getData() {
        return data;
    }
    
    public void setData(List<BannerDetails> data) {
        this.data = data;
    }

}
