package com.koioannis.javacourse_eshop.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.koioannis.javacourse_eshop.R;
import com.koioannis.javacourse_eshop.bizlogic.AdminController;
import com.koioannis.javacourse_eshop.bizlogic.BillingController;
import com.koioannis.javacourse_eshop.bizlogic.OrderController;

public class MetodosPaymentActivity extends AppCompatActivity {
    OrderController orderController;
    AdminController adminController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        orderController = OrderController.getInstance();
        adminController = AdminController.getInstance();

        Button fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (orderController.getBasket().getProductsToOrder().isEmpty()){
                    Snackbar.make(view, "Por favor selecione um metodo de pagamento", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    return;
                }*/
                Intent intent = new Intent(MetodosPaymentActivity.this, PaymentActivity.class);
                MetodosPaymentActivity.this.startActivity(intent);
            }
        });

    }
    public void toCheckout(View view) {
        super.onBackPressed();
    }
}