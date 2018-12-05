package com.example.abiel.p14google_maps;

import android.graphics.BitmapRegionDecoder;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
GoogleMap.OnMarkerDragListener, GoogleMap.OnInfoWindowClickListener{

    private GoogleMap mMap;
    private Marker markerPrueba;
    private Marker markerDrag, InfoWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng mexico = new LatLng(32.5291924,-116.9911174);
        mMap.addMarker(new MarkerOptions().position(mexico).draggable(true).title("Mexico").
        snippet("Mexico solicita personal").icon(BitmapDescriptorFactory.fromResource(R.drawable.mexico)));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(mexico));

        LatLng valle = new LatLng(19.1981806,-100.1873905);
        mMap.addMarker(new MarkerOptions().position(valle).title("Valle de Bravo").
        snippet("Valle precioso.").icon(BitmapDescriptorFactory.fromResource(R.drawable.valle)));

        LatLng zacatecas = new LatLng(23.0677013,-104.7920505);
        mMap.addMarker(new MarkerOptions().position(zacatecas).draggable(true).title("Zacatecas").
        snippet("Zacatecas empieza con Z").icon(BitmapDescriptorFactory.fromResource(R.drawable.zacatecas)));

        LatLng hermosillo = new LatLng(29.082082,-111.129417);
        mMap.addMarker(new MarkerOptions().position(hermosillo).title("Hermosillo").
        snippet("illo").icon(BitmapDescriptorFactory.fromResource(R.drawable.hermosillo)));

        //Prueba
        LatLng prueba = new LatLng(19.0400339,-98.2631777);
        markerPrueba = googleMap.addMarker(new MarkerOptions().position(prueba).
        title("Prueba"));

        //Morelos
        LatLng morelos = new LatLng(18.7324794,-99.3438828);
        markerDrag = googleMap.addMarker(new MarkerOptions().position(morelos).title("Morelos").
        draggable(true));

        //Toluca
        LatLng toluca = new LatLng(19.294099,-99.7012544);
        InfoWindow = googleMap.addMarker(new MarkerOptions().position(toluca).title("Toluca").
        icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //Para algo
        mMap.addPolyline(new PolylineOptions()
        .add(mexico,valle,toluca)
        .width(5)
        .color(Color.RED));

        //Zoom de la camara
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mexico, 7));
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnMarkerDragListener(this);
        googleMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker){
        String lat, lang;
        lat = Double.toString(marker.getPosition().latitude);
        lang = Double.toString(marker.getPosition().longitude);
        if (true){
            Toast.makeText(this, lat + lang, Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        if (marker.equals(markerDrag)){
            Toast.makeText(this, "Start",
                    Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        if (marker.equals(markerDrag)){
            String newTitle = String.format(Locale.getDefault(), getString(R.string.marker_detail_latlng),
                    marker.getPosition().latitude,
                    marker.getPosition().longitude);

            setTitle(newTitle);
        }
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        if (marker.equals(markerDrag)){
            Toast.makeText(this, "Finish",
                    Toast.LENGTH_LONG).show();
            setTitle(R.string.sitios);
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (marker.equals(InfoWindow)){
            TolucaFragment.newInstance(marker.getTitle(), getString(R.string.TolucaInfo)).
                    show(getSupportFragmentManager(), null);
        }
    }

//    @Override
//    public boolean onMarkerClick(Marker marker) {
//        if (marker.equals(markerPrueba)){
//            Toast.makeText(this, "Evento click", Toast.LENGTH_SHORT).show();
//        }
//        return false;
//    }
}
