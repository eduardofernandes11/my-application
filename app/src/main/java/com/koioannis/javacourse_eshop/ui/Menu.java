package com.koioannis.javacourse_eshop.ui;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.koioannis.javacourse_eshop.R;
import com.koioannis.javacourse_eshop.bizlogic.AdminController;
import com.koioannis.javacourse_eshop.bizlogic.OrderController;
import com.koioannis.javacourse_eshop.model.Basket;
import com.koioannis.javacourse_eshop.model.Product;

import java.util.List;

public class Menu extends AppCompatActivity{
    AdminController adminController;
    OrderController orderController;
    TextView cart, sobre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adminController = AdminController.getInstance();
        orderController = OrderController.getInstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        cart = (TextView)findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orderController.getBasket().getProductsToOrder().isEmpty()){
                    Snackbar.make(view, "Por favor adicione pelo menos um produto", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        sobre = (TextView)findViewById(R.id.sobre);
        sobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Sobre.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        TextView agro = (TextView)findViewById(R.id.agro);
        agro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Parceiros.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void toMainActivity(View view){
        super.onBackPressed();
    }

}