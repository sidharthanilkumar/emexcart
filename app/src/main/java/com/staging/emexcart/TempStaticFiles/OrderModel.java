package com.staging.emexcart.TempStaticFiles;

public class OrderModel {
    String prodName;
    String imageUrl;
    String status;
    String prodId;
    String Data;
    String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



    public OrderModel(String prodName, String imageUrl, String status, String prodId, String data,String price) {
        this.prodName = prodName;
        this.imageUrl = imageUrl;
        this.status = status;
        this.prodId = prodId;
        this.Data = data;
        this.price = price;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }
}
