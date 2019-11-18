package com.example.espeletiapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Locale;

public class Menu extends AppCompatActivity {

    CarouselView CVcarousel;
    int[] CVimagenes = {R.drawable.lupino, R.drawable.mirla, R.drawable.fraile, R.drawable.cascada, R.drawable.oxypogon, R.drawable.cara};
    TextView cityField, currentTemperatureField, weatherIcon, ciudad, icono, temperatura, ciudad2, icono2, temperatura2, ciudad3, icono3, temperatura3;
    Typeface weatherFont;
    String city = "Mongui,CO";
    String sogamoso = "SOGAMOSO,COLOMBIA";
    String mongua = "MONGUA,COLOMBIA";
    String aquitania = "AQUITANIA,COLOMBIA";
    String OPEN_WEATHER_MAP_API = "47109280e5aa5a1410ba4acd4af3dbc6";
    private Locale locale;
    private Configuration config = new Configuration();
    final private int REQUEST_CODE_ASK_PERMISSION=111;
    ImageButton actividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cityField = (TextView) findViewById(R.id.city_field);
        currentTemperatureField = (TextView) findViewById(R.id.current_temperature_field);
        weatherIcon = (TextView) findViewById(R.id.weather_icon);
        ciudad = findViewById(R.id.ciudad);
        icono = findViewById(R.id.icono);
        temperatura = findViewById(R.id.temperatura);
        ciudad2 = findViewById(R.id.ciudad2);
        icono2 = findViewById(R.id.icono2);
        temperatura2 = findViewById(R.id.temperatura2);
        ciudad3 = findViewById(R.id.ciudad3);
        icono3 = findViewById(R.id.icono3);
        temperatura3 = findViewById(R.id.temperatura3);
        weatherFont = Typeface.createFromAsset(getAssets(), "fonts/weathericons-regular-webfont.ttf");
        weatherIcon.setTypeface(weatherFont);
        icono.setTypeface(weatherFont);
        icono2.setTypeface(weatherFont);
        icono3.setTypeface(weatherFont);
        actividad = findViewById(R.id.actividad);
        taskLoadUp();

        //carrousel de imagenes del menu

        CVcarousel = findViewById(R.id.CVcarousel);
        CVcarousel.setPageCount(CVimagenes.length);
        CVcarousel.setImageListener(OBJimglisten);

        CVcarousel.setImageClickListener(new ImageClickListener() {
            @Override

            public void onClick(int position) {
                if(position == 0)
                {
                    mostrarToast1("Lupinos");
                }
                else if(position == 1)
                {
                    mostrarToast1("Mirla");
                }
                else if(position == 2)
                {
                    mostrarToast1("Frailejón");
                }
                else if(position == 3)
                {
                    mostrarToast1("Cascada del valle de los frailejones");
                }
                else if(position == 4)
                {
                    mostrarToast1("Oxypongo juvenil");
                }
                else if(position == 5)
                {
                    mostrarToast1("Cara de indio");
                }
            }
        });

        solicitarPermisos();
    }

    private void mostrarToast1(String texto)
    {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.activity_toast,(ViewGroup)findViewById(R.id.layout1));

        TextView textView = layout.findViewById(R.id.txt);
        textView.setText(texto);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP,350,160);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_puntos, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {

            case R.id.lenguaje:
                showDialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog(){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(getResources().getString(R.string.lenguaje));
        //obtiene los idiomas del array de string.xml
        String[] types = getResources().getStringArray(R.array.languages);
        b.setItems(types, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                switch(which){
                    case 0:
                        locale = new Locale("en");
                        config.locale =locale;
                        break;
                    case 1:
                        locale = new Locale("es");
                        config.locale =locale;
                        break;
                }
                getResources().updateConfiguration(config, null);
                Intent refresh = new Intent(Menu.this, Menu.class);
                startActivity(refresh);
                finish();
            }

        });

        b.show();
    }

    public void taskLoadUp() {
        if (Function.isNetworkAvailable(getApplicationContext())) {
            DownloadWeather task = new DownloadWeather();
            task.execute(city);
        }if (Function.isNetworkAvailable(getApplicationContext())) {
            DownloadWeather2 task = new DownloadWeather2();
            task.execute(sogamoso);
        }if (Function.isNetworkAvailable(getApplicationContext())) {
            DownloadWeather3 task = new DownloadWeather3();
            task.execute(mongua);
        }if (Function.isNetworkAvailable(getApplicationContext())) {
            DownloadWeather4 task = new DownloadWeather4();
            task.execute(aquitania);
        }else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }
    }


    class DownloadWeather extends AsyncTask< String, Void, String > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected String doInBackground(String...args) {
            String xml = Function.excuteGet("http://api.openweathermap.org/data/2.5/weather?q=" + args[0] +
                    "&units=metric&appid=" + OPEN_WEATHER_MAP_API);
            return xml;
        }
        @Override
        protected void onPostExecute(String xml) {

            try {
                JSONObject json = new JSONObject(xml);
                if (json != null) {
                    JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = json.getJSONObject("main");
                    DateFormat df = DateFormat.getDateTimeInstance();

                    cityField.setText(json.getString("name").toUpperCase(Locale.US) + ", " + json.getJSONObject("sys").getString("country"));
                    currentTemperatureField.setText(String.format("%.2f", main.getDouble("temp")) + "°");
                    weatherIcon.setText(Html.fromHtml(Function.setWeatherIcon(details.getInt("id"),
                            json.getJSONObject("sys").getLong("sunrise") * 1000,
                            json.getJSONObject("sys").getLong("sunset") * 1000)));
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Error, Check City", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class DownloadWeather2 extends AsyncTask< String, Void, String > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected String doInBackground(String...args) {
            String xml = Function.excuteGet("http://api.openweathermap.org/data/2.5/weather?q=" + args[0] +
                    "&units=metric&appid=" + OPEN_WEATHER_MAP_API);
            return xml;
        }
        @Override
        protected void onPostExecute(String xml) {

            try {
                JSONObject json = new JSONObject(xml);
                if (json != null) {
                    JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = json.getJSONObject("main");
                    DateFormat df = DateFormat.getDateTimeInstance();


                    ciudad.setText(json.getString("name").toUpperCase(Locale.US) + ", " + json.getJSONObject("sys").getString("country"));
                    temperatura.setText(String.format("%.2f", main.getDouble("temp")) + "°");
                    icono.setText(Html.fromHtml(Function.setWeatherIcon(details.getInt("id"),
                            json.getJSONObject("sys").getLong("sunrise") * 1000,
                            json.getJSONObject("sys").getLong("sunset") * 1000)));
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Error, Check City", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class DownloadWeather3 extends AsyncTask< String, Void, String > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected String doInBackground(String...args) {
            String xml = Function.excuteGet("http://api.openweathermap.org/data/2.5/weather?q=" + args[0] +
                    "&units=metric&appid=" + OPEN_WEATHER_MAP_API);
            return xml;
        }
        @Override
        protected void onPostExecute(String xml) {

            try {
                JSONObject json = new JSONObject(xml);
                if (json != null) {
                    JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = json.getJSONObject("main");
                    DateFormat df = DateFormat.getDateTimeInstance();


                    ciudad2.setText(json.getString("name").toUpperCase(Locale.US) + ", " + json.getJSONObject("sys").getString("country"));
                    temperatura2.setText(String.format("%.2f", main.getDouble("temp")) + "°");
                    icono2.setText(Html.fromHtml(Function.setWeatherIcon(details.getInt("id"),
                            json.getJSONObject("sys").getLong("sunrise") * 1000,
                            json.getJSONObject("sys").getLong("sunset") * 1000)));
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Error, Check City", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class DownloadWeather4 extends AsyncTask< String, Void, String > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected String doInBackground(String...args) {
            String xml = Function.excuteGet("http://api.openweathermap.org/data/2.5/weather?q=" + args[0] +
                    "&units=metric&appid=" + OPEN_WEATHER_MAP_API);
            return xml;
        }
        @Override
        protected void onPostExecute(String xml) {

            try {
                JSONObject json = new JSONObject(xml);
                if (json != null) {
                    JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = json.getJSONObject("main");
                    DateFormat df = DateFormat.getDateTimeInstance();


                    ciudad3.setText(json.getString("name").toUpperCase(Locale.US) + ", " + json.getJSONObject("sys").getString("country"));
                    temperatura3.setText(String.format("%.2f", main.getDouble("temp")) + "°");
                    icono3.setText(Html.fromHtml(Function.setWeatherIcon(details.getInt("id"),
                            json.getJSONObject("sys").getLong("sunrise") * 1000,
                            json.getJSONObject("sys").getLong("sunset") * 1000)));
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Error, Check City", Toast.LENGTH_SHORT).show();
            }
        }
    }

    ImageListener OBJimglisten = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(CVimagenes[position]);
        }
    };

    public void btnActividades(View vista) {
        Intent intent = new Intent(Menu.this, Actividades.class);
        startActivity(intent);
    }
    public void btnRecomendaciones(View vista) {
        Intent intent = new Intent(Menu.this, Recomendaciones.class);
        startActivity(intent);
    }
    public void btnCodigo(View vista) {
        Intent intent = new Intent(Menu.this, Codigoqr.class);
        startActivity(intent);
    }
    public void btnSendero(View vista) {
        Intent intent = new Intent(Menu.this, Sendero.class);
        startActivity(intent);
    }
    public void btnIdentidad(View vista) {
        Intent intent = new Intent(Menu.this,Servicios.class);
        startActivity(intent);
    }
    public void btnExperiencias(View vista) {
        Intent intent = new Intent(Menu.this, Experiencias.class);
        startActivity(intent);
    }

    private void solicitarPermisos()
    {
        int permisosCamara = ActivityCompat.checkSelfPermission(Menu.this, Manifest.permission.CAMERA);

        if(permisosCamara!= PackageManager.PERMISSION_GRANTED)
        {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                requestPermissions(new String[]{Manifest.permission.CAMERA},REQUEST_CODE_ASK_PERMISSION);
            }
        }
    }


}
