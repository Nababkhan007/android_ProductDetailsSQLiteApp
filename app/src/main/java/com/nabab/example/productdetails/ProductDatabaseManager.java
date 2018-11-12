package com.nabab.example.productdetails;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.nabab.example.productdetails.ProductDatabaseHelper.PRODUCT_COLUMN_AVAILABLE_QUANTITY;
import static com.nabab.example.productdetails.ProductDatabaseHelper.PRODUCT_COLUMN_BRAND_NAME;
import static com.nabab.example.productdetails.ProductDatabaseHelper.PRODUCT_COLUMN_COLOR;
import static com.nabab.example.productdetails.ProductDatabaseHelper.PRODUCT_COLUMN_ID;
import static com.nabab.example.productdetails.ProductDatabaseHelper.PRODUCT_COLUMN_NAME;
import static com.nabab.example.productdetails.ProductDatabaseHelper.PRODUCT_COLUMN_PRICE;
import static com.nabab.example.productdetails.ProductDatabaseHelper.PRODUCT_COLUMN_SIZE;
import static com.nabab.example.productdetails.ProductDatabaseHelper.PROFILE_IMAGE;
import static com.nabab.example.productdetails.ProductDatabaseHelper.TABLE_NAME;

public class ProductDatabaseManager {
    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    private ProductDatabaseHelper productDatabaseHelper;

    public ProductDatabaseManager(Context context) {
        this.context = context;
        productDatabaseHelper = new ProductDatabaseHelper(context);
    }

    public boolean insertProductList(ProductList productList){
        sqLiteDatabase = productDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductDatabaseHelper.PRODUCT_COLUMN_NAME, productList.getProductName());
        contentValues.put(ProductDatabaseHelper.PRODUCT_COLUMN_COLOR, productList.getProductColor());
        contentValues.put(ProductDatabaseHelper.PRODUCT_COLUMN_PRICE, productList.getProductPrice());
        contentValues.put(ProductDatabaseHelper.PRODUCT_COLUMN_AVAILABLE_QUANTITY, productList.getAvailableQuantity());
        contentValues.put(ProductDatabaseHelper.PRODUCT_COLUMN_SIZE, productList.getProductSize());
        contentValues.put(ProductDatabaseHelper.PRODUCT_COLUMN_BRAND_NAME, productList.getBrandName());
        contentValues.put(ProductDatabaseHelper.PROFILE_IMAGE, productList.getProfileImage());

        long insertRow = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if(insertRow>0){
            sqLiteDatabase.close();
            return true;

        }else{
            return false;

        }

    }

    public List<ProductList> getAllProduct(){
        sqLiteDatabase = productDatabaseHelper.getReadableDatabase();
        List<ProductList> productLists = new ArrayList<>();
        String query = "select * from "+TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex(PRODUCT_COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(PRODUCT_COLUMN_NAME));
                String color = cursor.getString(cursor.getColumnIndex(PRODUCT_COLUMN_COLOR));
                String price = cursor.getString(cursor.getColumnIndex(PRODUCT_COLUMN_PRICE));
                String quantity = cursor.getString(cursor.getColumnIndex(PRODUCT_COLUMN_AVAILABLE_QUANTITY));
                String size = cursor.getString(cursor.getColumnIndex(PRODUCT_COLUMN_SIZE));
                String brandName = cursor.getString(cursor.getColumnIndex(PRODUCT_COLUMN_BRAND_NAME));
                String profileImage = cursor.getString(cursor.getColumnIndex(PROFILE_IMAGE));
                ProductList productList = new ProductList(id, name, color, price, quantity, size, brandName, profileImage);
                productLists.add(productList);
            }while (cursor.moveToNext());

        }

        sqLiteDatabase.close();
        return productLists;
    }
}
