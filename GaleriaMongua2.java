package com.example.espeletiapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class GaleriaMongua2 extends AppCompatActivity implements View.OnClickListener {

    ImageView img1;
    final int request_id_write_permission = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria_mongua2);

        img1 = findViewById(R.id.img1);

        img1.setOnClickListener(this);

        if(askPermissionsWriterFile())
        {
            Toast.makeText(this,"El permiso ya esta otorgado",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img1:
                ScreenImageFull(12);
                break;
        }
    }

    public void ScreenImageFull(int imagenSeleccionada)
    {
        Intent imagen = new Intent(GaleriaMongua2.this,ImagenSeleccionada.class);
        imagen.putExtra("imagen",imagenSeleccionada);
        startActivity(imagen);
    }

    public boolean askPermissionsWriterFile()
    {
        boolean canWrite = this.askPermission(request_id_write_permission, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(canWrite)
        {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean askPermission(int requestId,String permissionName)
    {
        if(Build.VERSION.SDK_INT >= 23)
        {
            int permission = ActivityCompat.checkSelfPermission(this,permissionName);
            if(permission != PackageManager.PERMISSION_GRANTED){
                this.requestPermissions(new String[]{permissionName},requestId);
            }
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case request_id_write_permission:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"Permiso otorgado con exito",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this,"Permiso denegado",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
