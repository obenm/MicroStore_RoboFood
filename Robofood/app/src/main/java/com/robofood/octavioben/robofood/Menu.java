package com.robofood.octavioben.robofood;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {

    ImageButton menu_button_cart, menu_cart_button_close;
    Button menu_cart_button_checkout;
    FrameLayout menu_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menu_cart = (FrameLayout) findViewById(R.id.menu_cart);
        menu_button_cart = (ImageButton) findViewById(R.id.menu_button_cart);
        menu_cart_button_close = (ImageButton) findViewById(R.id.menu_cart_button_close);
        menu_cart_button_checkout = (Button) findViewById(R.id.menu_cart_button_checkout);

        menu_button_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu_cart.setVisibility(View.VISIBLE);
            }
        });

        menu_cart_button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu_cart.setVisibility(View.INVISIBLE);
            }
        });

        menu_cart_button_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Menu = new Intent(Menu.this, Payment.class);
                startActivity(Menu);
            }
        });
    }
}
