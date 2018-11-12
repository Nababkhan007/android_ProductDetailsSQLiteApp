package com.nabab.example.productdetails;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AddProductActivity extends AppCompatActivity {
    private EditText productNameEt, productColorEt, productPriceEt, availableQuantityEt, productSizeEt;
    private CheckBox catsEyeCkb, dorjiBariCkb, nababFashionCkb;
    private Button captureProductImageBtn, addBtn, showBtn;
    private ImageView imageView;
    private ProductDatabaseHelper productDatabaseHelper;
    private ProductDatabaseManager productDatabaseManager;
    private static final int REQUEST_CODE_FOR_CAMERA = 1;
    private String imageFromCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        initialization();
        onClick();
    }

    private void onClick() {
        captureProductImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager()) !=null);{
                    startActivityForResult(intent, REQUEST_CODE_FOR_CAMERA);
                }
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = productNameEt.getText().toString();
                String color = productColorEt.getText().toString();
                String price = productPriceEt.getText().toString();
                String quantity = availableQuantityEt.getText().toString();
                String size = productSizeEt.getText().toString();

                 String brandName = "";
                if(catsEyeCkb.isChecked()){
                    brandName = catsEyeCkb.getText().toString();
                }else if(dorjiBariCkb.isChecked()){
                    brandName = dorjiBariCkb.getText().toString();
                }else if(nababFashionCkb.isChecked()){
                    brandName = nababFashionCkb.getText().toString();
                }

                ProductList productList = new ProductList(name, color, price, quantity, size, brandName, imageFromCamera);
                boolean insert = productDatabaseManager.insertProductList(productList);
                if(insert){
                    Toast.makeText(AddProductActivity.this, "Successfully data added", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddProductActivity.this, "Data added failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddProductActivity.this, ShowProductDetailsActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_FOR_CAMERA && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageFromCamera = encodeImage(bitmap, Bitmap.CompressFormat.JPEG, 70);
            imageView.setImageBitmap(bitmap);
        }
    }

    public static String encodeImage(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int quality){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, quality, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
    }

    private void initialization() {
        productNameEt = findViewById(R.id.productNameEtId);
        productColorEt = findViewById(R.id.productColorEtId);
        productPriceEt = findViewById(R.id.productPriceEtId);
        availableQuantityEt = findViewById(R.id.availableQuantityEtId);
        productSizeEt = findViewById(R.id.productSizeEtId);

        catsEyeCkb = findViewById(R.id.catsEyeCkbId);
        dorjiBariCkb = findViewById(R.id.dorjiBariCkbId);
        nababFashionCkb = findViewById(R.id.nababFashionCkbId);

        captureProductImageBtn = findViewById(R.id.captureProductImageBtnId);
        addBtn = findViewById(R.id.addBtnId);
        showBtn = findViewById(R.id.showBtnId);

        imageView = findViewById(R.id.profileIvId);

        productDatabaseHelper = new ProductDatabaseHelper(this);
        productDatabaseManager = new ProductDatabaseManager(this);
    }
}
