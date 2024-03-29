package com.example.espeletiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class FiestasMongui extends AppCompatActivity {

    Button imagen;
    ImageButton atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiestas_mongui);

        imagen = findViewById(R.id.imagen);
        atras = findViewById(R.id.atras);

        DisplayMetrics medidaspantalla= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidaspantalla);

        int ancho=medidaspantalla.widthPixels;
        int alto=medidaspantalla.heightPixels;

        getWindow().setLayout((int)(ancho*0.85),(int)(alto-300));

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FiestasMongui.this,Mongui.class);
                startActivity(intent);
            }
        });

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FiestasMongui.this,GaleriaMongui3.class);
                startActivity(intent);
            }
        });
    }
}
