package com.robofood.octavioben.robofood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import org.json.JSONArray;

import java.io.IOException;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Menu extends AppCompatActivity {

    JSONArray datosJSON;

    ImageButton menu_button_cart, menu_cart_button_close;
    Button menu_cart_button_checkout;
    FrameLayout menu_cart;

    String IP = "http://obenm.esy.es";
    String GET = "http://obenm.esy.es/get_products.php";

    Bitmap menu_bitmap_1, menu_bitmap_2, menu_bitmap_3, menu_bitmap_4, menu_bitmap_5, menu_bitmap_6;
    ImageView menu_image_1, menu_image_2, menu_image_3, menu_image_4, menu_image_5, menu_image_6;

    ObtenerWebService hiloconexion;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    FrameLayout alert_frame;
    Button alert_btn;

    private void mostrarAlerta(String s){
        TextView alert_text = (TextView) findViewById(R.id.alert_text);
        alert_text.setText(s);
        alert_frame.setVisibility(View.VISIBLE);
    }

    Button menu_button_order_1, menu_button_order_2, menu_button_order_3, menu_button_order_4, menu_button_order_5, menu_button_order_6;
    FrameLayout menu_cart_item_1, menu_cart_item_2, menu_cart_item_3, menu_cart_item_4, menu_cart_item_5, menu_cart_item_6;
    ImageView menu_cart_image_1, menu_cart_image_2, menu_cart_image_3, menu_cart_image_4, menu_cart_image_5, menu_cart_image_6;
    TextView menu_cart_name_1, menu_cart_name_2, menu_cart_name_3, menu_cart_name_4, menu_cart_name_5, menu_cart_name_6;
    TextView menu_cart_price_1, menu_cart_price_2, menu_cart_price_3, menu_cart_price_4, menu_cart_price_5, menu_cart_price_6;
    TextView menu_cart_cuantity_1, menu_cart_cuantity_2, menu_cart_cuantity_3, menu_cart_cuantity_4, menu_cart_cuantity_5, menu_cart_cuantity_6;

    ImageButton menu_cart_more_1, menu_cart_more_2, menu_cart_more_3, menu_cart_more_4, menu_cart_more_5, menu_cart_more_6;
    ImageButton menu_cart_less_1, menu_cart_less_2, menu_cart_less_3, menu_cart_less_4, menu_cart_less_5, menu_cart_less_6;
    ImageButton menu_cart_close_button_1, menu_cart_close_button_2, menu_cart_close_button_3, menu_cart_close_button_4, menu_cart_close_button_5, menu_cart_close_button_6;

    TextView menu_cart_total;

    private int[] carrito_item = new int[6];
    private String[] carrito_item_id = new String[6];
    private Bitmap[] carrito_item_imagen = new Bitmap[6];
    private String[] carrito_item_name = new String[6];
    private double[] carrito_item_price = new double[6];
    private int[] carrito_item_cuantity = new int[6];
    private double carrito_total = 0;

    private void eliminarProducto(int btn){
        carrito_item[btn-1] = 0;
        carrito_item_cuantity[btn-1] = 0;
        if(btn == 1){
            menu_cart_item_1.setVisibility(View.GONE);
        }
        else if(btn == 2){
            menu_cart_item_2.setVisibility(View.GONE);
        }
        else if(btn == 3){
            menu_cart_item_3.setVisibility(View.GONE);
        }
        else if(btn == 4){
            menu_cart_item_4.setVisibility(View.GONE);
        }
        else if(btn == 5){
            menu_cart_item_5.setVisibility(View.GONE);
        }
        else if(btn == 6){
            menu_cart_item_6.setVisibility(View.GONE);
        }
        calcularTotal();
    }

    private void calcularTotal(){
        double suma = 0.00;
        for(int i = 0; i < 6; i++){
            if(carrito_item[i] == 1){
                suma = suma + (carrito_item_price[i] * carrito_item_cuantity[i]);
            }
        }
        carrito_total = suma;
        menu_cart_total.setText("$ " + carrito_total);
    }

    private void cambiarCantidad(int item, int modo){
        if(modo == 1){
            carrito_item_cuantity[item-1]++;
        }
        else if(modo == 2){
            carrito_item_cuantity[item-1]--;
        }
        if(carrito_item_cuantity[item-1] <= 0){
            carrito_item_cuantity[item-1] = 1;
        }

        if(item == 1){
            menu_cart_cuantity_1.setText(Integer.toString(carrito_item_cuantity[item-1]));
        }
        else if(item == 2){
            menu_cart_cuantity_2.setText(Integer.toString(carrito_item_cuantity[item-1]));
        }
        else if(item == 3){
            menu_cart_cuantity_3.setText(Integer.toString(carrito_item_cuantity[item-1]));
        }
        else if(item == 4){
            menu_cart_cuantity_4.setText(Integer.toString(carrito_item_cuantity[item-1]));
        }
        else if(item == 5){
            menu_cart_cuantity_5.setText(Integer.toString(carrito_item_cuantity[item-1]));
        }
        else if(item == 6){
            menu_cart_cuantity_6.setText(Integer.toString(carrito_item_cuantity[item-1]));
        }
        calcularTotal();
    }

    private void agregarCarrito(String id, int btn) throws JSONException {
        if(btn == 1){
            if(carrito_item[0] == 1){
                carrito_item_cuantity[0]++;
            }
            else{
                carrito_item[0] = 1;
                carrito_item_cuantity[0] = 1;
            }
            menu_cart_image_1.setImageBitmap(menu_bitmap_1);
            menu_cart_name_1.setText((String) datosJSON.getJSONObject(0).getString("name"));
            menu_cart_price_1.setText("$ " + (String) datosJSON.getJSONObject(0).getString("price"));
            carrito_item_price[0] = Double.parseDouble(datosJSON.getJSONObject(0).getString("price"));
            menu_cart_cuantity_1.setText(Integer.toString(carrito_item_cuantity[0]));
            menu_cart_item_1.setVisibility(View.VISIBLE);
        }
        else if(btn == 2){
            if(carrito_item[1] == 1){
                carrito_item_cuantity[1]++;
            }
            else{
                carrito_item[1] = 1;
                carrito_item_cuantity[1] = 1;
            }
            menu_cart_image_2.setImageBitmap(menu_bitmap_2);
            menu_cart_name_2.setText((String) datosJSON.getJSONObject(1).getString("name"));
            menu_cart_price_2.setText("$ " + (String) datosJSON.getJSONObject(1).getString("price"));
            carrito_item_price[1] = Double.parseDouble(datosJSON.getJSONObject(1).getString("price"));
            menu_cart_cuantity_2.setText(Integer.toString(carrito_item_cuantity[1]));
            menu_cart_item_2.setVisibility(View.VISIBLE);
        }
        else if(btn == 3){
            if(carrito_item[2] == 1){
                carrito_item_cuantity[2]++;
            }
            else{
                carrito_item[2] = 1;
                carrito_item_cuantity[2] = 1;
            }
            menu_cart_image_3.setImageBitmap(menu_bitmap_3);
            menu_cart_name_3.setText((String) datosJSON.getJSONObject(2).getString("name"));
            menu_cart_price_3.setText("$ " + (String) datosJSON.getJSONObject(2).getString("price"));
            carrito_item_price[2] = Double.parseDouble(datosJSON.getJSONObject(2).getString("price"));
            menu_cart_cuantity_3.setText(Integer.toString(carrito_item_cuantity[2]));
            menu_cart_item_3.setVisibility(View.VISIBLE);
        }
        else if(btn == 4){
            if(carrito_item[3] == 1){
                carrito_item_cuantity[3]++;
            }
            else{
                carrito_item[3] = 1;
                carrito_item_cuantity[3] = 1;
            }
            menu_cart_image_4.setImageBitmap(menu_bitmap_4);
            menu_cart_name_4.setText((String) datosJSON.getJSONObject(3).getString("name"));
            menu_cart_price_4.setText("$ " + (String) datosJSON.getJSONObject(3).getString("price"));
            carrito_item_price[3] = Double.parseDouble(datosJSON.getJSONObject(3).getString("price"));
            menu_cart_cuantity_4.setText(Integer.toString(carrito_item_cuantity[3]));
            menu_cart_item_4.setVisibility(View.VISIBLE);
        }
        else if(btn == 5){
            if(carrito_item[4] == 1){
                carrito_item_cuantity[4]++;
            }
            else{
                carrito_item[4] = 1;
                carrito_item_cuantity[4] = 1;
            }
            menu_cart_image_5.setImageBitmap(menu_bitmap_5);
            menu_cart_name_5.setText((String) datosJSON.getJSONObject(4).getString("name"));
            menu_cart_price_5.setText("$ " + (String) datosJSON.getJSONObject(4).getString("price"));
            carrito_item_price[4] = Double.parseDouble(datosJSON.getJSONObject(4).getString("price"));
            menu_cart_cuantity_5.setText(Integer.toString(carrito_item_cuantity[4]));
            menu_cart_item_5.setVisibility(View.VISIBLE);
        }
        else if(btn == 6){
            if(carrito_item[5] == 1){
                carrito_item_cuantity[5]++;
            }
            else{
                carrito_item[5] = 1;
                carrito_item_cuantity[5] = 1;
            }
            menu_cart_image_6.setImageBitmap(menu_bitmap_6);
            menu_cart_name_6.setText((String) datosJSON.getJSONObject(5).getString("name"));
            menu_cart_price_6.setText("$ " + (String) datosJSON.getJSONObject(5).getString("price"));
            carrito_item_price[5] = Double.parseDouble(datosJSON.getJSONObject(5).getString("price"));
            menu_cart_cuantity_6.setText(Integer.toString(carrito_item_cuantity[5]));
            menu_cart_item_6.setVisibility(View.VISIBLE);
        }

        calcularTotal();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menu_cart = (FrameLayout) findViewById(R.id.menu_cart);
        menu_button_cart = (ImageButton) findViewById(R.id.menu_button_cart);
        menu_cart_button_close = (ImageButton) findViewById(R.id.menu_cart_button_close);
        menu_cart_button_checkout = (Button) findViewById(R.id.menu_cart_button_checkout);

        menu_button_order_1 = (Button) findViewById(R.id.menu_button_order_1);
        menu_button_order_2 = (Button) findViewById(R.id.menu_button_order_2);
        menu_button_order_3 = (Button) findViewById(R.id.menu_button_order_3);
        menu_button_order_4 = (Button) findViewById(R.id.menu_button_order_4);
        menu_button_order_5 = (Button) findViewById(R.id.menu_button_order_5);
        menu_button_order_6 = (Button) findViewById(R.id.menu_button_order_6);

        menu_cart_item_1 = (FrameLayout) findViewById(R.id.menu_cart_item_1);
        menu_cart_item_2 = (FrameLayout) findViewById(R.id.menu_cart_item_2);
        menu_cart_item_3 = (FrameLayout) findViewById(R.id.menu_cart_item_3);
        menu_cart_item_4 = (FrameLayout) findViewById(R.id.menu_cart_item_4);
        menu_cart_item_5 = (FrameLayout) findViewById(R.id.menu_cart_item_5);
        menu_cart_item_6 = (FrameLayout) findViewById(R.id.menu_cart_item_6);

        menu_cart_image_1 = (ImageView) findViewById(R.id.menu_cart_image_1);
        menu_cart_image_2 = (ImageView) findViewById(R.id.menu_cart_image_2);
        menu_cart_image_3 = (ImageView) findViewById(R.id.menu_cart_image_3);
        menu_cart_image_4 = (ImageView) findViewById(R.id.menu_cart_image_4);
        menu_cart_image_5 = (ImageView) findViewById(R.id.menu_cart_image_5);
        menu_cart_image_6 = (ImageView) findViewById(R.id.menu_cart_image_6);

        menu_cart_name_1 = (TextView) findViewById(R.id.menu_cart_name_1);
        menu_cart_name_2 = (TextView) findViewById(R.id.menu_cart_name_2);
        menu_cart_name_3 = (TextView) findViewById(R.id.menu_cart_name_3);
        menu_cart_name_4 = (TextView) findViewById(R.id.menu_cart_name_4);
        menu_cart_name_5 = (TextView) findViewById(R.id.menu_cart_name_5);
        menu_cart_name_6 = (TextView) findViewById(R.id.menu_cart_name_6);

        menu_cart_name_1 = (TextView) findViewById(R.id.menu_cart_name_1);
        menu_cart_name_2 = (TextView) findViewById(R.id.menu_cart_name_2);
        menu_cart_name_3 = (TextView) findViewById(R.id.menu_cart_name_3);
        menu_cart_name_4 = (TextView) findViewById(R.id.menu_cart_name_4);
        menu_cart_name_5 = (TextView) findViewById(R.id.menu_cart_name_5);
        menu_cart_name_6 = (TextView) findViewById(R.id.menu_cart_name_6);

        menu_cart_price_1 = (TextView) findViewById(R.id.menu_cart_price_1);
        menu_cart_price_2 = (TextView) findViewById(R.id.menu_cart_price_2);
        menu_cart_price_3 = (TextView) findViewById(R.id.menu_cart_price_3);
        menu_cart_price_4 = (TextView) findViewById(R.id.menu_cart_price_4);
        menu_cart_price_5 = (TextView) findViewById(R.id.menu_cart_price_5);
        menu_cart_price_6 = (TextView) findViewById(R.id.menu_cart_price_6);

        menu_cart_cuantity_1 = (TextView) findViewById(R.id.menu_cart_cuantity_1);
        menu_cart_cuantity_2 = (TextView) findViewById(R.id.menu_cart_cuantity_2);
        menu_cart_cuantity_3 = (TextView) findViewById(R.id.menu_cart_cuantity_3);
        menu_cart_cuantity_4 = (TextView) findViewById(R.id.menu_cart_cuantity_4);
        menu_cart_cuantity_5 = (TextView) findViewById(R.id.menu_cart_cuantity_5);
        menu_cart_cuantity_6 = (TextView) findViewById(R.id.menu_cart_cuantity_6);

        menu_cart_more_1 = (ImageButton) findViewById(R.id.menu_cart_more_1);
        menu_cart_more_2 = (ImageButton) findViewById(R.id.menu_cart_more_2);
        menu_cart_more_3 = (ImageButton) findViewById(R.id.menu_cart_more_3);
        menu_cart_more_4 = (ImageButton) findViewById(R.id.menu_cart_more_4);
        menu_cart_more_5 = (ImageButton) findViewById(R.id.menu_cart_more_5);
        menu_cart_more_6 = (ImageButton) findViewById(R.id.menu_cart_more_6);

        menu_cart_less_1 = (ImageButton) findViewById(R.id.menu_cart_less_1);
        menu_cart_less_2 = (ImageButton) findViewById(R.id.menu_cart_less_2);
        menu_cart_less_3 = (ImageButton) findViewById(R.id.menu_cart_less_3);
        menu_cart_less_4 = (ImageButton) findViewById(R.id.menu_cart_less_4);
        menu_cart_less_5 = (ImageButton) findViewById(R.id.menu_cart_less_5);
        menu_cart_less_6 = (ImageButton) findViewById(R.id.menu_cart_less_6);

        menu_cart_close_button_1 = (ImageButton) findViewById(R.id.menu_cart_close_button_1);
        menu_cart_close_button_2 = (ImageButton) findViewById(R.id.menu_cart_close_button_2);
        menu_cart_close_button_3 = (ImageButton) findViewById(R.id.menu_cart_close_button_3);
        menu_cart_close_button_4 = (ImageButton) findViewById(R.id.menu_cart_close_button_4);
        menu_cart_close_button_5 = (ImageButton) findViewById(R.id.menu_cart_close_button_5);
        menu_cart_close_button_6 = (ImageButton) findViewById(R.id.menu_cart_close_button_6);

        menu_cart_total = (TextView) findViewById(R.id.menu_cart_total);

        menu_button_order_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    agregarCarrito((String) datosJSON.getJSONObject(0).getString("idproduct"), 1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        menu_button_order_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    agregarCarrito((String) datosJSON.getJSONObject(1).getString("idproduct"), 2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        menu_button_order_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    agregarCarrito((String) datosJSON.getJSONObject(2).getString("idproduct"), 3);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        menu_button_order_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    agregarCarrito((String) datosJSON.getJSONObject(3).getString("idproduct"), 4);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        menu_button_order_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    agregarCarrito((String) datosJSON.getJSONObject(4).getString("idproduct"), 5);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        menu_button_order_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    agregarCarrito((String) datosJSON.getJSONObject(5).getString("idproduct"), 6);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        menu_cart_more_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarCantidad(1, 1);
            }
        });
        menu_cart_more_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarCantidad(2, 1);
            }
        });
        menu_cart_more_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarCantidad(3, 1);
            }
        });
        menu_cart_more_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarCantidad(4, 1);
            }
        });
        menu_cart_more_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarCantidad(5, 1);
            }
        });
        menu_cart_more_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarCantidad(6, 1);
            }
        });

        menu_cart_less_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarCantidad(1, 2);
            }
        });
        menu_cart_less_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarCantidad(2, 2);
            }
        });
        menu_cart_less_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarCantidad(3, 2);
            }
        });
        menu_cart_less_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarCantidad(4, 2);
            }
        });
        menu_cart_less_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarCantidad(5, 2);
            }
        });
        menu_cart_less_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarCantidad(6, 2);
            }
        });

        menu_cart_close_button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarProducto(1);
            }
        });
        menu_cart_close_button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarProducto(2);
            }
        });
        menu_cart_close_button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarProducto(3);
            }
        });
        menu_cart_close_button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarProducto(4);
            }
        });
        menu_cart_close_button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarProducto(5);
            }
        });
        menu_cart_close_button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarProducto(6);
            }
        });

        //textViewTest = (TextView) findViewById(R.id.textViewTest);

        menu_button_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu_cart.setVisibility(View.VISIBLE);
                calcularTotal();
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
                Variables v = Variables.getInstance();
                v.setTotal(carrito_total);
            }
        });

        TextView menu_username = (TextView) findViewById(R.id.menu_username);
        Variables v = Variables.getInstance();
        menu_username.setText(v.getUser());

        alert_frame = (FrameLayout) findViewById(R.id.alert_frame);

        alert_btn = (Button) findViewById(R.id.alert_btn);
        alert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert_frame.setVisibility(View.GONE);
            }
        });

        ImageButton menu_info_button = (ImageButton) findViewById(R.id.menu_info_button);
        menu_info_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 mostrarAlerta("Developed by Octavio Benitez");
            }
        });


        hiloconexion = new ObtenerWebService();
        hiloconexion.execute(GET);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Menu Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public class ObtenerWebService extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            String cadena = GET;

            String devuelve = "";

            URL url = null; // Url de donde queremos obtener información
            try {
                url = new URL(cadena);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                        " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
                //connection.setHeader("content-type", "application/json");

                int respuesta = connection.getResponseCode();
                StringBuilder result = new StringBuilder();

                if (respuesta == HttpURLConnection.HTTP_OK) {


                    InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada

                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader

                    // El siguiente proceso lo hago porque el JSONOBject necesita un String y tengo
                    // que tranformar el BufferedReader a String. Esto lo hago a traves de un
                    // StringBuilder.

                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);        // Paso toda la entrada al StringBuilder
                    }

                    //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                    JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                    //Accedemos al vector de resultados

                    datosJSON = respuestaJSON.getJSONArray("datos");
                        for(int i = 0; i < datosJSON.length(); i++){
                            /*devuelve = devuelve + datosJSON.getJSONObject(i).getString("idproduct") + " " +
                                    datosJSON.getJSONObject(i).getString("name") + " " +
                                    datosJSON.getJSONObject(i).getString("price") + " " +
                                    datosJSON.getJSONObject(i).getString("details") + " " +
                                    datosJSON.getJSONObject(i).getString("image") + "\n";*/
                            //datosJSON.getJSONObject(i).getString("name");
                    }

                    URL url_image_item_1 = new URL(datosJSON.getJSONObject(0).getString("image"));
                    HttpURLConnection conimagen1 = (HttpURLConnection) url_image_item_1.openConnection();
                    conimagen1.connect();
                    menu_bitmap_1 = BitmapFactory.decodeStream(conimagen1.getInputStream());

                    URL url_image_item_2 = new URL(datosJSON.getJSONObject(1).getString("image"));
                    HttpURLConnection conimagen2 = (HttpURLConnection) url_image_item_2.openConnection();
                    conimagen2.connect();
                    menu_bitmap_2 = BitmapFactory.decodeStream(conimagen2.getInputStream());

                    URL url_image_item_3 = new URL(datosJSON.getJSONObject(2).getString("image"));
                    HttpURLConnection conimagen3 = (HttpURLConnection) url_image_item_3.openConnection();
                    conimagen3.connect();
                    menu_bitmap_3 = BitmapFactory.decodeStream(conimagen3.getInputStream());

                    URL url_image_item_4 = new URL(datosJSON.getJSONObject(3).getString("image"));
                    HttpURLConnection conimagen4 = (HttpURLConnection) url_image_item_4.openConnection();
                    conimagen4.connect();
                    menu_bitmap_4 = BitmapFactory.decodeStream(conimagen4.getInputStream());

                    URL url_image_item_5 = new URL(datosJSON.getJSONObject(4).getString("image"));
                    HttpURLConnection conimagen5 = (HttpURLConnection) url_image_item_5.openConnection();
                    conimagen5.connect();
                    menu_bitmap_5 = BitmapFactory.decodeStream(conimagen5.getInputStream());

                    URL url_image_item_6 = new URL(datosJSON.getJSONObject(5).getString("image"));
                    HttpURLConnection conimagen6 = (HttpURLConnection) url_image_item_6.openConnection();
                    conimagen6.connect();
                    menu_bitmap_6 = BitmapFactory.decodeStream(conimagen6.getInputStream());


                    //Log.d("return:", devuelve);


                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Log.d("myTag", devuelve);
            return devuelve;
        }

        @Override
        protected void onCancelled(String aVoid) {
            super.onCancelled(aVoid);
        }

        @Override
        protected void onPostExecute(String aVoid) {
            //textViewTest.setText(aVoid);
            //super.onPostExecute(aVoid);
            //Log.d("myTag", aVoid);

            colocarItems();
        }

        @Override
        protected void onPreExecute() {
            //resultado.setText("");
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        private void colocarItems(){
            TextView menu_name_1, menu_detail_1, menu_price_1;
            TextView menu_name_2, menu_detail_2, menu_price_2;
            TextView menu_name_3, menu_detail_3, menu_price_3;
            TextView menu_name_4, menu_detail_4, menu_price_4;
            TextView menu_name_5, menu_detail_5, menu_price_5;
            TextView menu_name_6, menu_detail_6, menu_price_6;



            FrameLayout menu_item_1, menu_item_2, menu_item_3, menu_item_4, menu_item_5, menu_item_6;

            menu_name_1 = (TextView) findViewById(R.id.menu_name_1);
            menu_detail_1 = (TextView) findViewById(R.id.menu_detail_1);
            menu_price_1 = (TextView) findViewById(R.id.menu_price_1);

            menu_name_2 = (TextView) findViewById(R.id.menu_name_2);
            menu_detail_2 = (TextView) findViewById(R.id.menu_detail_2);
            menu_price_2 = (TextView) findViewById(R.id.menu_price_2);

            menu_name_3 = (TextView) findViewById(R.id.menu_name_3);
            menu_detail_3 = (TextView) findViewById(R.id.menu_detail_3);
            menu_price_3 = (TextView) findViewById(R.id.menu_price_3);

            menu_name_4 = (TextView) findViewById(R.id.menu_name_4);
            menu_detail_4 = (TextView) findViewById(R.id.menu_detail_4);
            menu_price_4 = (TextView) findViewById(R.id.menu_price_4);

            menu_name_5 = (TextView) findViewById(R.id.menu_name_5);
            menu_detail_5 = (TextView) findViewById(R.id.menu_detail_5);
            menu_price_5 = (TextView) findViewById(R.id.menu_price_5);

            menu_name_6 = (TextView) findViewById(R.id.menu_name_6);
            menu_detail_6 = (TextView) findViewById(R.id.menu_detail_6);
            menu_price_6 = (TextView) findViewById(R.id.menu_price_6);

            menu_item_1 = (FrameLayout) findViewById(R.id.menu_item_1);
            menu_item_2 = (FrameLayout) findViewById(R.id.menu_item_2);
            menu_item_3 = (FrameLayout) findViewById(R.id.menu_item_3);
            menu_item_4 = (FrameLayout) findViewById(R.id.menu_item_4);
            menu_item_5 = (FrameLayout) findViewById(R.id.menu_item_5);
            menu_item_6 = (FrameLayout) findViewById(R.id.menu_item_6);

            menu_image_1 = (ImageView) findViewById(R.id.menu_image_1);
            menu_image_2 = (ImageView) findViewById(R.id.menu_image_2);
            menu_image_3 = (ImageView) findViewById(R.id.menu_image_3);
            menu_image_4 = (ImageView) findViewById(R.id.menu_image_4);
            menu_image_5 = (ImageView) findViewById(R.id.menu_image_5);
            menu_image_6 = (ImageView) findViewById(R.id.menu_image_6);

            try {
                if((String) datosJSON.getJSONObject(0).getString("idproduct") != null){
                    menu_item_1.setVisibility(View.VISIBLE);
                    menu_image_1.setImageBitmap(menu_bitmap_1);
                    menu_name_1.setText((String) datosJSON.getJSONObject(0).getString("name"));
                    menu_detail_1.setText((String) datosJSON.getJSONObject(0).getString("details"));
                    menu_price_1.setText("$ " + (String) datosJSON.getJSONObject(0).getString("price"));
                }
                if((String) datosJSON.getJSONObject(1).getString("idproduct") != null){
                    menu_item_2.setVisibility(View.VISIBLE);
                    menu_image_2.setImageBitmap(menu_bitmap_2);
                    menu_name_2.setText((String) datosJSON.getJSONObject(1).getString("name"));
                    menu_detail_2.setText((String) datosJSON.getJSONObject(1).getString("details"));
                    menu_price_2.setText("$ " + (String) datosJSON.getJSONObject(1).getString("price"));
                }
                if((String) datosJSON.getJSONObject(2).getString("idproduct") != null){
                    menu_item_3.setVisibility(View.VISIBLE);
                    menu_image_3.setImageBitmap(menu_bitmap_3);
                    menu_name_3.setText((String) datosJSON.getJSONObject(2).getString("name"));
                    menu_detail_3.setText((String) datosJSON.getJSONObject(2).getString("details"));
                    menu_price_3.setText("$ " + (String) datosJSON.getJSONObject(2).getString("price"));
                }
                if((String) datosJSON.getJSONObject(3).getString("idproduct") != null){
                    menu_item_4.setVisibility(View.VISIBLE);
                    menu_image_4.setImageBitmap(menu_bitmap_4);
                    menu_name_4.setText((String) datosJSON.getJSONObject(3).getString("name"));
                    menu_detail_4.setText((String) datosJSON.getJSONObject(3).getString("details"));
                    menu_price_4.setText("$ " + (String) datosJSON.getJSONObject(3).getString("price"));
                }
                if((String) datosJSON.getJSONObject(4).getString("idproduct") != null){
                    menu_item_5.setVisibility(View.VISIBLE);
                    menu_image_5.setImageBitmap(menu_bitmap_5);
                    menu_name_5.setText((String) datosJSON.getJSONObject(4).getString("name"));
                    menu_detail_5.setText((String) datosJSON.getJSONObject(4).getString("details"));
                    menu_price_5.setText("$ " + (String) datosJSON.getJSONObject(4).getString("price"));
                }
                if((String) datosJSON.getJSONObject(5).getString("idproduct") != null){
                    menu_item_6.setVisibility(View.VISIBLE);
                    menu_image_6.setImageBitmap(menu_bitmap_6);
                    menu_name_6.setText((String) datosJSON.getJSONObject(5).getString("name"));
                    menu_detail_6.setText((String) datosJSON.getJSONObject(5).getString("details"));
                    menu_price_6.setText("$ " + (String) datosJSON.getJSONObject(5).getString("price"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
