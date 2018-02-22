package com.example.a2nehrs61.mapping;

import android.app.AlertDialog;
import android.content.SharedPreferences;
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
    double lastLat, lastLon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);

        Button b = (Button)findViewById(R.id.btn);
        b.setOnClickListener(this);

        mv = (MapView)findViewById(R.id.map1);
        mv.setBuiltInZoomControls(true);
    }
    public void onStart()
    {
        super.onStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        double lat = Double.parseDouble ( prefs.getString("lat", "50.9") );
        double lon = Double.parseDouble ( prefs.getString("lon", "-1.4") );

        if (Math.abs(lat-lastLat)> 0.00001 || Math.abs(lon-lastLon) > 0.00001) {
            mv.getController().setZoom(12);
            mv.getController().setCenter(new GeoPoint(lat, lon));
        }
    }

    public void onStop()
    {
        super.onStop();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        lastLat = Double.parseDouble ( prefs.getString("lat", "50.9") );
        lastLon = Double.parseDouble ( prefs.getString("lon", "-1.4") );
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.choosemap)
        {
            Intent intent = new Intent(this,MapChooseActivity.class);
            startActivityForResult(intent,0);
            return true;
        }
        else if(item.getItemId() == R.id.preferences)
        {
            Intent intent = new Intent(this,PrefActivity.class);
            startActivityForResult(intent,1);
            return true;
        }
        else if(item.getItemId() == R.id.list)
        {
            Intent intent = new Intent(this,MapChooseListActivity.class);
            startActivityForResult(intent,2);
            return true;
        }
        return false;
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
    protected void onActivityResult(int requestCode,int resultCode,Intent intent)
    {

        if(requestCode==0)
        {

            if (resultCode==RESULT_OK)
            {
                Bundle extras=intent.getExtras();
                boolean hikebikemap = extras.getBoolean("com.example.hikebikemap");
                if(hikebikemap==true)
                {
                    mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
                }
                else
                {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }
            }
        }
        if(requestCode==2)
        {

            if (resultCode==RESULT_OK)
            {
                Bundle extras=intent.getExtras();
                boolean hikebikemap = extras.getBoolean("com.example.hikebikemap");
                if(hikebikemap==true)
                {
                    mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
                }
                else
                {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }
            }
        }
    }


}
