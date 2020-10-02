
package com.staging.emexcart.models.payment_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllPaymentMethod {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("enabled")
    @Expose
    private Boolean enabled;
    
   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    
}
