package com.staging.emexcart.TempStaticFiles;

public class CartModel {
    String prodName;
    String originalPrice;
    String imageUrl;
    String subTotal;

    public CartModel(String prodName, String originalPrice, String imageUrl, String subTotal) {
        this.prodName = prodName;
        this.originalPrice = originalPrice;
        this.imageUrl = imageUrl;
        this.subTotal = subTotal;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }
}
