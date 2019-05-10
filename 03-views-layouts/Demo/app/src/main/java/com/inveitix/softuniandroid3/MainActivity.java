package com.inveitix.softuniandroid3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int COFFEE_PRICE = 2;
    private TextView txtQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_order);

        Button btnMinus = findViewById(R.id.btn_minus);
        Button btnPlus = findViewById(R.id.btn_plus);
        txtQuantity = findViewById(R.id.txt_quantity);

        btnMinus.setOnClickListener(minusListener);
        btnPlus.setOnClickListener(plusListener);

        findViewById(R.id.btn_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailBody = "Hi. I want " +
                        txtQuantity.getText() +
                        " coffees, pls. I'll pay " +
                        ((TextView) findViewById(R.id.txt_price)).getText();
                String emailReceiver = "starbucks@stbs.com";

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, emailReceiver);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Pls");
                intent.putExtra(Intent.EXTRA_TEXT, emailBody);

                startActivity(intent);
            }
        });
    }

    View.OnClickListener minusListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int currentQuantity = Integer.parseInt(txtQuantity.getText().toString());
            currentQuantity--;
            txtQuantity.setText(String.valueOf(currentQuantity));
            changePrice(currentQuantity);
        }
    };

    View.OnClickListener plusListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int currentQuantity = Integer.parseInt(txtQuantity.getText().toString());
            currentQuantity++;
            txtQuantity.setText(String.valueOf(currentQuantity));
            changePrice(currentQuantity);
        }
    };

    private void changePrice(int coffeeQuantity) {
        int price = coffeeQuantity * COFFEE_PRICE;
        TextView txtPrice = findViewById(R.id.txt_price);
        txtPrice.setText("$" + price);
    }
}
