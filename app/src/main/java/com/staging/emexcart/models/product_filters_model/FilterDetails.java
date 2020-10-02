package com.staging.emexcart.models.product_filters_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class FilterDetails {
    
    @SerializedName("attribute_name")
    @Expose
    private String attributeName;
    @SerializedName("attribute_slug")
    @Expose
    private String attributeSlug;
    @SerializedName("attribute_terms")
    @Expose
    private List<FilterTerm> attributeTerms = null;
    
    
    public String getAttributeName() {
        return attributeName;
    }
    
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
    
    public String getAttributeSlug() {
        return attributeSlug;
    }

    public void setAttributeSlug(String attributeSlug) {
        this.attributeSlug = attributeSlug;
    }

    public List<FilterTerm> getAttributeTerms() {
        return attributeTerms;
    }

    public void setAttributeTerms(List<FilterTerm> attributeTerms) {
        this.attributeTerms = attributeTerms;
    }

}
