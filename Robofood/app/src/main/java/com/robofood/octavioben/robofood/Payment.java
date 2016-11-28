package com.robofood.octavioben.robofood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Payment extends AppCompatActivity {

    ImageButton payment_button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        payment_button_back = (ImageButton) findViewById(R.id.payment_button_back);

        payment_button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Menu = new Intent(Payment.this, Menu.class);
                startActivity(Menu);
            }
        });
    }
}
