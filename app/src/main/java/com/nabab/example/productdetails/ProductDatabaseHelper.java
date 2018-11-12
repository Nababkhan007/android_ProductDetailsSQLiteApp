package com.nabab.example.productdetails;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductDatabaseHelper extends SQLiteOpenHelper {
    public Context context;
    public static final String DATABASE_NAME = "ProductListDatabase";
    public static final int VERSION = 1;

    public static final String TABLE_NAME = "ProductList";
    public static final String PRODUCT_COLUMN_ID = "Id";
    public static final String PRODUCT_COLUMN_NAME = "ProductName";
    public static final String PRODUCT_COLUMN_COLOR = "ProductColor";
    public static final String PRODUCT_COLUMN_PRICE = "ProductPrice";
    public static final String PRODUCT_COLUMN_AVAILABLE_QUANTITY = "ProductQuantity";
    public static final String PRODUCT_COLUMN_SIZE = "ProductSize";
    public static final String PRODUCT_COLUMN_BRAND_NAME = "ProductBrandName";
    public static final String PROFILE_IMAGE = "ProfileImage";

    public static final String createTableQuery = "create table "+TABLE_NAME+"("+PRODUCT_COLUMN_ID+" integer primary key,"+PRODUCT_COLUMN_NAME+" text,"+PRODUCT_COLUMN_COLOR+" text,"+PRODUCT_COLUMN_PRICE+" text,"+PRODUCT_COLUMN_AVAILABLE_QUANTITY+" text,"+PRODUCT_COLUMN_SIZE+" text,"+PRODUCT_COLUMN_BRAND_NAME+" text,"+PROFILE_IMAGE+" text"+")";

    public ProductDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
