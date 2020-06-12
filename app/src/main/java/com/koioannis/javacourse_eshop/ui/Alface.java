package com.koioannis.javacourse_eshop.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.koioannis.javacourse_eshop.R;
import com.koioannis.javacourse_eshop.bizlogic.AdminController;
import com.koioannis.javacourse_eshop.bizlogic.OrderController;
import com.koioannis.javacourse_eshop.model.Product;

public class Alface extends AppCompatActivity{
    AdminController adminController;
    OrderController orderController;

    ImageView cart;
    ImageView certo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alface);
        adminController = AdminController.getInstance();
        orderController = OrderController.getInstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        cart = (ImageView)findViewById(R.id.cart);
        certo = (ImageView)findViewById(R.id.certo);

        if(orderController.getBasket().getProductsToOrder() != null)
            for (Product p : orderController.getBasket().getProductsToOrder())
                if (p.getName() == "Alface Roxa") {
                    cart.setVisibility(View.GONE);
                    certo.setVisibility(View.VISIBLE);
                }
    }

    public void toMainActivity(View view){
        super.onBackPressed();
    }

    public void addBasket1(View view) {
        int tag = Integer.parseInt(view.getTag().toString());
        orderController.addProduct(tag);
        cart.setVisibility(View.GONE);
        certo.setVisibility(View.VISIBLE);
        //view.setBackgroundResource(R.drawable.ic_done);
    }
}