package com.nabab.example.productdetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.List;

public class ShowProductDetailsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductListAdapter productListAdapter;
    private ProductDatabaseManager productDatabaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product_details);

        initialization();
    }

    private void initialization() {
        recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productDatabaseManager = new ProductDatabaseManager(this);
        List<ProductList> productLists = productDatabaseManager.getAllProduct();
        productListAdapter = new ProductListAdapter(this, productLists);
        recyclerView.setAdapter(productListAdapter);
    }
}
