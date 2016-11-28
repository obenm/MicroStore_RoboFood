package com.robofood.octavioben.robofood;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Home extends AppCompatActivity {

    EditText login_user, login_password;

    Typeface notosansregular;

    Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        notosansregular = Typeface.createFromAsset(getAssets(),"fonts/notosansregular.ttf");

        login_user = (EditText) findViewById(R.id.login_user);
        login_password = (EditText) findViewById(R.id.login_password);
        login_user.setTypeface(notosansregular);
        login_password.setTypeface(notosansregular);

        login_button = (Button) findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Menu = new Intent(Home.this, Menu.class);
                startActivity(Menu);
            }
        });
    }
}
