package com.example.proyectorobotgarcia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

public class AccesoGPS extends AppCompatActivity {
    private TextView textView;
    private ProgressBar progressBar;
    private View myLayout;
    private EditText usuarioEditText;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        MapView mapView = findViewById((R.id.mapView));
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        double latitudRobot = -33.4493141;
        double longitudRobot = -70.6624069;

        GeoPoint RobotPoint = new GeoPoint(latitudRobot, longitudRobot);
        mapView.getController().setZoom(15.0);
        mapView.getController().setCenter(RobotPoint);

        Marker marcadorRobot = new Marker(mapView);
        marcadorRobot.setPosition(RobotPoint);
        marcadorRobot.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marcadorRobot.setTitle("Robot García Point ID56P");
        marcadorRobot.setSnippet("Estado: Activo");

        mapView.getOverlays().add(marcadorRobot);

        double latitudTeam = -33.4625076;
        double longitudTeam = -70.6600286;
        GeoPoint teamPoint = new GeoPoint(latitudTeam, longitudTeam);
        Marker marcadorTeam = new Marker(mapView);
        marcadorTeam.setPosition(teamPoint);
        marcadorTeam.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marcadorTeam.setTitle("Team García");
        marcadorTeam.setSnippet("Estado: Ocupado");
        mapView.getOverlays().add(marcadorTeam);

        Polyline linea = new Polyline();
        linea.addPoint(RobotPoint);
        linea.addPoint(teamPoint);
        linea.setColor(0xFF0000FF);
        linea.setWidth(5);
        mapView.getOverlayManager().add(linea);

        Spinner mapTypeSpinner = findViewById(R.id.mapTypeSpinner);
        String[] mapTypes = {"Mapa Normal", "Mapa de Transporte", "Mapa Topografico"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mapTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mapTypeSpinner.setAdapter(adapter);

        mapTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {   switch (position) {
                case 0:
                    mapView.setTileSource(TileSourceFactory.MAPNIK);
                    break;
                case 1:
                    mapView.setTileSource(new XYTileSource(
                            "PublicTransport",
                            0, 18, 256, ".png", new String[]{
                            "https://tile.memomaps.de/tilegen/"}));
                    break;
                case 2:
                    mapView.setTileSource(new XYTileSource(
                            "USGS_Satellite", 0, 18, 256, ".png", new String[]{
                            "https://a.tile.opentopomap.org/",
                            "https://b.tile.opentopomap.org/",
                            "https://c.tile.opentopomap.org/"}));
                    break;
            }
            }

            // No se hace nada cuando no se selecciona ningún elemento.
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        findViewById(R.id.botonReg).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MediaPlayer mediaPlayer = MediaPlayer.create(AccesoGPS.this, R.raw.clickeffect);
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();

                        Intent intent = new Intent(AccesoGPS.this, AccesoActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

        findViewById(R.id.botonVol).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MediaPlayer mediaPlayer = MediaPlayer.create(AccesoGPS.this, R.raw.clickeffect);
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();

                        Intent intent = new Intent(AccesoGPS.this, AccesoRevisar.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    public void onClickRevisar(View view) {
        Intent intent = new Intent(this, AccesoRevisar.class);
        startActivity(intent);
    }

}