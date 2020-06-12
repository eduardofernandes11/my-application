package com.koioannis.javacourse_eshop.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.koioannis.javacourse_eshop.R;
import com.koioannis.javacourse_eshop.bizlogic.AdminController;
import com.koioannis.javacourse_eshop.bizlogic.OrderController;
import com.koioannis.javacourse_eshop.model.Product;

public class Parceiros extends AppCompatActivity{
    AdminController adminController;
    OrderController orderController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agricultores);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        ImageView joao = (ImageView)findViewById(R.id.joao);
        joao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AGJoao.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        ImageView joaquim = (ImageView)findViewById(R.id.joaquim);
        joaquim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AGJoaquim.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        ImageView carlos = (ImageView)findViewById(R.id.carlos);
        carlos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AGCarlos.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }

    public void toMainActivity(View view){
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}