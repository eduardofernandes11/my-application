package com.koioannis.javacourse_eshop.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.koioannis.javacourse_eshop.R;
import com.koioannis.javacourse_eshop.bizlogic.BillingController;
import com.koioannis.javacourse_eshop.bizlogic.OrderController;
import java.text.DecimalFormat;


public class CheckoutActivity extends AppCompatActivity {
    private OrderController orderController;
    private BillingController billingController;
    private TextView totalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        totalCost = findViewById(R.id.totalCost);
        displayProducts();

        Button checkoutBtn = findViewById(R.id.checkoutBtn);
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent paymentActivity = new Intent(CheckoutActivity.this, MetodosPaymentActivity.class);
                CheckoutActivity.this.startActivity(paymentActivity);
            }
        });

    }
    private void displayProducts(){
        orderController = OrderController.getInstance();
        billingController = BillingController.getInstance();
        int productsCount = orderController.getProductsCount();
        for (int i=0; i < productsCount; i++){
                ConstraintLayout layout = (ConstraintLayout) findViewByString("constraintLayout", i);
                View line = findViewByString("line", i);

                totalCost.setText("€" + billingController.getTotalPrice("-1"));
                totalCost.setAlpha(1f);
                TextView productName = (TextView) layout.getChildAt(0);
                TextView productPrice = (TextView) layout.getChildAt(1);
                TextView quantityTextView = (TextView) layout.getChildAt(3);
                quantityTextView.setText(Double.toString(orderController.getProductQuantity(i)));
                productName.setTextSize(18);
                productName.setText(orderController.getProductName(i));
                productPrice.setTextSize(18);
                productPrice.setText(orderController.getProductPrice(i) + "€");
                line.setAlpha(0.1f);
                layout.setAlpha(1f);
        }
    }

    public void toMainActivity(View view){
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void addQuantity(View view){
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");

        if (changeProductQuantity(true, view)) {
            changePrice(true, view);
            totalCost.setText("€" + decimalFormat.format(billingController.getTotalPrice("-1")));
        }

    }

    public void removeQuantity(View view){
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");

        if (changeProductQuantity(false, view)) {
            changePrice(false, view);
            totalCost.setText("€" + decimalFormat.format(billingController.getTotalPrice("-1")));
        }

    }

    private void changePrice(Boolean isAdd, View view){
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        orderController = OrderController.getInstance();
        int tag = Integer.parseInt(view.getTag().toString());
        TextView priceTextView = (TextView)findViewByString("productPrice", tag);

        String str = priceTextView.getText().toString();
        str = str.substring(0, str.indexOf("€"));
        str = str.replace(",", ".");
        double price = Double.parseDouble(str);
        String finalPrice = "";

        if (isAdd)
            finalPrice = decimalFormat.format(price + (orderController.getInitialProductPrice(tag)*0.1))+ "€";
        if (!isAdd)
            finalPrice = decimalFormat.format(price - (orderController.getInitialProductPrice(tag) * 0.1)) + "€";

        priceTextView.setText(finalPrice);
    }

    private boolean changeProductQuantity(Boolean isAdd, View view){
        int tag = Integer.parseInt(view.getTag().toString());
        TextView quantity = (TextView)findViewByString("productQuantity" , tag);
        orderController = OrderController.getInstance();

        double productQuantity = Double.parseDouble(quantity.getText().toString());

        if (productQuantity == 9 && isAdd)
            return false;

        if (productQuantity == 1 && !isAdd) {
            return false;
        }

        if (isAdd) {
            productQuantity+= 0.1;
            orderController.addProductQuantity(tag, productQuantity);
            orderController.setProductPrice(tag, productQuantity);
        } else {
            productQuantity-= 0.1;
            orderController.addProductQuantity(tag, productQuantity);
        }
        changeQuantityTextview(quantity, productQuantity);
        return true;
    }

    private void changeQuantityTextview(TextView quantity, double productQuantity){
        quantity.setText(Double.toString(productQuantity));
    }

    private View findViewByString(String type, int tag) {
        String productQuantityID = type + tag;
        Resources res = getResources();
        int id = res.getIdentifier(productQuantityID, "id", getApplicationContext().getPackageName());

        return findViewById(id);
    }

}
