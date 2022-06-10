package com.example.apppulequeriapeinados;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.apppulequeriapeinados.databinding.ActivityUbicacionBinding;

/*
    Clase que muestra la ubicación del local desde Google Maps
    Implementa un OnMapReadyCallback para el evento de llamar al Google Maps
 */
public class Activity_Ubicacion extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityUbicacionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUbicacionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Obtiene el Fragmento de soporte del mapa cuando el mapa está listo para usarse
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Recoge la ubicación de la peluqueria para usarla en el Google Maps
        LatLng Peluqueria = new LatLng(40.29934131556553, -3.794324704347265);
        mMap.addMarker(new MarkerOptions().position(Peluqueria).title("Peinados Peluqueros")); //Define el marcador
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Peluqueria)); //Centra la vista en las coordenadas indicadas en "Peluqueria"
    }
}