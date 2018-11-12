package com.nabab.example.productdetails;

public class ProductList {
    private int id;
    private String productName;
    private String productColor;
    private String productPrice;
    private String availableQuantity;
    private String productSize;
    private String brandName;
    private String profileImage;

    public ProductList(int id, String productName, String productColor, String productPrice, String availableQuantity, String productSize, String brandName, String profileImage) {
        this.id = id;
        this.productName = productName;
        this.productColor = productColor;
        this.productPrice = productPrice;
        this.availableQuantity = availableQuantity;
        this.productSize = productSize;
        this.brandName = brandName;
        this.profileImage = profileImage;
    }

    public ProductList(String productName, String productColor, String productPrice, String availableQuantity, String productSize, String brandName, String profileImage) {
        this.productName = productName;
        this.productColor = productColor;
        this.productPrice = productPrice;
        this.availableQuantity = availableQuantity;
        this.productSize = productSize;
        this.brandName = brandName;
        this.profileImage = profileImage;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductColor() {
        return productColor;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getAvailableQuantity() {
        return availableQuantity;
    }

    public String getProductSize() {
        return productSize;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
