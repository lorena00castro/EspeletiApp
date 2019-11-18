package com.example.espeletiapp;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImagenSeleccionada extends AppCompatActivity {

    int imagenSeleccionada;
    ImageView img;
    Bitmap bitmap;
    Drawable drawable;

    int[] drawables = {
            R.drawable.iglesia1,
            R.drawable.iglesia2,
            R.drawable.iglesia3,
            R.drawable.iglesia4,
            R.drawable.parque1,
            R.drawable.parque2,
            R.drawable.parque3,
            R.drawable.parque4,
            R.drawable.balon1,
            R.drawable.balon2,
            R.drawable.cruces1,
            R.drawable.cruces2,
            R.drawable.cultivosmax,
            R.drawable.monumento1,
            R.drawable.monumento2,
            R.drawable.monumento3,
            R.drawable.fiestamongua1,
            R.drawable.fiestamongua2,
            R.drawable.lago1,
            R.drawable.lago2,
            R.drawable.lago3,
            R.drawable.cultivosaquitania1,
            R.drawable.cultivosaquitania2,
            R.drawable.cultivosaquitania3,
            R.drawable.cultivosaquitania4,
            R.drawable.cultivosaquitania5,
            R.drawable.cultivosaquitania6,
            R.drawable.iglesia3,
            R.drawable.iglesia4,
            R.drawable.iglesia1,
            R.drawable.iglesia2,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen_seleccionada);

        img = findViewById(R.id.img);

        obtenerImagenSeleccionada();
    }

    public void obtenerImagenSeleccionada()
    {
        Bundle recibirValor = getIntent().getExtras();
        if(recibirValor != null)
        {
            imagenSeleccionada = recibirValor.getInt("imagen");
            mostrarImagen(imagenSeleccionada);
        }
    }

    public void mostrarImagen(int posicion)
    {
        img.setImageResource(drawables[posicion]);
    }

}
