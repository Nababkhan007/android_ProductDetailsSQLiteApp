package com.nabab.example.productdetails;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {
    private Context context;
    private List<ProductList> productLists;

    public ProductListAdapter(Context context, List<ProductList> productLists) {
        this.context = context;
        this.productLists = productLists;
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.model_product_list_design, null);
        return new ProductListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder productListViewHolder, int i) {
        ProductList productList = productLists.get(i);
        productListViewHolder.imageView.setImageBitmap(decodeImage(productList.getProfileImage()));
        productListViewHolder.nameTv.setText(productList.getProductName());
        productListViewHolder.priceTv.setText(productList.getProductPrice());
    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }

    public class ProductListViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView nameTv, priceTv;

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.showProfileIvId);
            nameTv = itemView.findViewById(R.id.showProductNameTvId);
            priceTv = itemView.findViewById(R.id.showProductPriceTvId);
        }
    }

    public static Bitmap decodeImage(String input){
        byte[] decodeByte = Base64.decode(input, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.length);
    }
}
