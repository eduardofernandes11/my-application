package com.koioannis.javacourse_eshop.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.koioannis.javacourse_eshop.R;
import com.koioannis.javacourse_eshop.bizlogic.AdminController;
import com.koioannis.javacourse_eshop.bizlogic.OrderController;

public class MainActivity extends AppCompatActivity {
    AdminController adminController;
    OrderController orderController;
    ImageButton maca, alface, batata, beterraba, cenoura, morango;
    TextView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AdminController.instantiateApp();

        alface = (ImageButton) findViewById(R.id.alface);
        alface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Alface.class);
                startActivity(intent);
            }
        });
        maca = (ImageButton) findViewById(R.id.apple);
        maca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Maca.class);
                startActivity(intent);
            }
        });
        beterraba = (ImageButton) findViewById(R.id.bete);
        beterraba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Beterraba.class);
                startActivity(intent);
            }
        });
        batata = (ImageButton) findViewById(R.id.batata);
        batata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Batata.class);
                startActivity(intent);
            }
        });
        cenoura = (ImageButton) findViewById(R.id.cenoura);
        cenoura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Cenoura.class);
                startActivity(intent);
            }
        });
        morango = (ImageButton) findViewById(R.id.morango);
        morango.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Morango.class);
                startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        FloatingActionButton fab = findViewById(R.id.mainActivityFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orderController.getBasket().getProductsToOrder().isEmpty()){
                    Snackbar.make(view, "Por favor adicione pelo menos um produto", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    return;
                }
                Intent checkoutActivity = new Intent(MainActivity.this, CheckoutActivity.class);
                MainActivity.this.startActivity(checkoutActivity);
            }
        });

        menu = (TextView)findViewById(R.id.menu1);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Menu.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        adminController = AdminController.getInstance();
        orderController = OrderController.getInstance();
    }

    public void search(View view) {
        EditText txtname = (EditText) findViewById(R.id.editText);
        String name = txtname.getText().toString();
        CardView Maca = (CardView) findViewById(R.id.cardView0);
        CardView alface = (CardView) findViewById(R.id.cardView1);
        CardView batata = (CardView) findViewById(R.id.cardView2);
        CardView beterraba = (CardView) findViewById(R.id.cardView3);
        CardView cenoura = (CardView) findViewById(R.id.cardView4);
        CardView morango = (CardView) findViewById(R.id.cardView5);

        if (name.matches("")) {
            Maca.setVisibility(View.VISIBLE);
            alface.setVisibility(View.VISIBLE);
            batata.setVisibility(View.VISIBLE);
            beterraba.setVisibility(View.VISIBLE);
            cenoura.setVisibility(View.VISIBLE);
            morango.setVisibility(View.VISIBLE);
        }else if (("Maça Fuji").contains(name)) {
            Maca.setVisibility(View.VISIBLE);
            alface.setVisibility(View.GONE);
            batata.setVisibility(View.GONE);
            beterraba.setVisibility(View.GONE);
            cenoura.setVisibility(View.GONE);
            morango.setVisibility(View.GONE);
        } else if (("Alface Roxa").contains(name)) {
            alface.setVisibility(View.VISIBLE);
            Maca.setVisibility(View.GONE);
            batata.setVisibility(View.GONE);
            beterraba.setVisibility(View.GONE);
            cenoura.setVisibility(View.GONE);
            morango.setVisibility(View.GONE);
        } else if (("Batata").contains(name)) {
            batata.setVisibility(View.VISIBLE);
            Maca.setVisibility(View.GONE);
            alface.setVisibility(View.GONE);
            beterraba.setVisibility(View.GONE);
            cenoura.setVisibility(View.GONE);
            morango.setVisibility(View.GONE);
        } else if (("Beterraba").contains(name)) {
            beterraba.setVisibility(View.VISIBLE);
            Maca.setVisibility(View.GONE);
            alface.setVisibility(View.GONE);
            batata.setVisibility(View.GONE);
            cenoura.setVisibility(View.GONE);
            morango.setVisibility(View.GONE);
        } else if (("Cenoura").contains(name)) {
            cenoura.setVisibility(View.VISIBLE);
            Maca.setVisibility(View.GONE);
            alface.setVisibility(View.GONE);
            batata.setVisibility(View.GONE);
            morango.setVisibility(View.GONE);
            beterraba.setVisibility(View.GONE);
        } else if (("Morango").contains(name)) {
            morango.setVisibility(View.VISIBLE);
            Maca.setVisibility(View.GONE);
            alface.setVisibility(View.GONE);
            batata.setVisibility(View.GONE);
            cenoura.setVisibility(View.GONE);
            beterraba.setVisibility(View.GONE);
        } else if (name.equals("")) {
            morango.setVisibility(View.VISIBLE);
            Maca.setVisibility(View.VISIBLE);
            alface.setVisibility(View.VISIBLE);
            batata.setVisibility(View.VISIBLE);
            cenoura.setVisibility(View.VISIBLE);
            beterraba.setVisibility(View.VISIBLE);
        } else {
            Maca.setVisibility(View.GONE);
            alface.setVisibility(View.GONE);
            batata.setVisibility(View.GONE);
            beterraba.setVisibility(View.GONE);
            cenoura.setVisibility(View.GONE);
            morango.setVisibility(View.GONE);
        }
    }
}
