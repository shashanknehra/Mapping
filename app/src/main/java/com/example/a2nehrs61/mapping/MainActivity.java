package com.example.a2nehrs61.mapping;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MapView mv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);

        Button b = (Button)findViewById(R.id.btn);
        b.setOnClickListener(this);

        mv = (MapView)findViewById(R.id.map1);
        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(2);
        mv.getController().setCenter(new GeoPoint(50.9097,1.4044));

    }
    public void onClick(View view)
    {
        EditText a = (EditText)findViewById(R.id.et1);
        EditText b = (EditText)findViewById(R.id.et2);
        double latitude = Double.parseDouble(a.getText().toString());
        double longitude = Double.parseDouble(b.getText().toString());
        if(latitude < -90.0 || latitude > 90.0){
            new AlertDialog.Builder(this).
                    setPositiveButton("OK",null).
                    setMessage(getResources().getString(R.string.alertLat)).show();
        }
        else if(longitude < -180.0 || longitude > 180.0){
            new AlertDialog.Builder(this).
                    setPositiveButton("OK",null).
                    setMessage(getResources().getString(R.string.alertLong)).show();
        }
        else{
            mv.getController().setZoom(12);
            mv.getController().setCenter(new GeoPoint(latitude,longitude));
        }

    }

}
