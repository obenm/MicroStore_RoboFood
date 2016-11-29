package com.robofood.octavioben.robofood;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
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

public class Home extends AppCompatActivity {

    EditText login_user, login_password;

    Typeface notosansregular;

    Button login_button;

    JSONArray datosJSON;
    String IP = "http://obenm.esy.es";
    String GET = "http://obenm.esy.es/get_users.php";
    ObtenerWebService hiloconexion;
    private GoogleApiClient client;

    FrameLayout alert_frame;
    Button alert_btn;

    private void mostrarAlerta(String s){
        TextView alert_text = (TextView) findViewById(R.id.alert_text);
        alert_text.setText(s);
        alert_frame.setVisibility(View.VISIBLE);
    }

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
                int contador = 0;
                for(int i = 0; i < datosJSON.length(); i++) {
                    try {
                        if (login_user.getText().toString().equals(datosJSON.getJSONObject(i).getString("user")) && login_password.getText().toString().equals(datosJSON.getJSONObject(i).getString("password"))) {
                            contador = 1;
                            Intent Menu = new Intent(Home.this, Menu.class);
                            startActivity(Menu);
                            Variables v = Variables.getInstance();
                            v.setUser(datosJSON.getJSONObject(i).getString("name"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if(contador == 0){
                    mostrarAlerta("Incorrect User or Password");
                }
            }
        });

        alert_frame = (FrameLayout) findViewById(R.id.alert_frame);

        alert_btn = (Button) findViewById(R.id.alert_btn);
        alert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert_frame.setVisibility(View.GONE);
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
/*                    for(int i = 0; i < datosJSON.length(); i++){
                            devuelve = devuelve + datosJSON.getJSONObject(i).getString("iduser") + " " +
                                    datosJSON.getJSONObject(i).getString("user") + " " +
                                    datosJSON.getJSONObject(i).getString("password") + " " +
                                    datosJSON.getJSONObject(i).getString("name") + " " + "\n";
                        //datosJSON.getJSONObject(i).getString("name");
                    }*/



                    Log.d("return:", devuelve);


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
            Log.d("myTag", aVoid);

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

    }
}
