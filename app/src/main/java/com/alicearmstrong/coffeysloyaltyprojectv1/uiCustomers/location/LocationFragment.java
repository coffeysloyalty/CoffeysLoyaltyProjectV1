package com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.location;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alicearmstrong.coffeysloyaltyprojectv1.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationFragment extends Fragment implements OnMapReadyCallback {
    private LocationViewModel locationViewModel;
    private GoogleMap gMap;
    private MapView mapView;
    private Location currentLocation;
    private int LOCATION_PERMISSION_REQUEST_CODE = 1234;




    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        locationViewModel = ViewModelProviders.of( this ).get( LocationViewModel.class );
        View view = inflater.inflate( R.layout.fragment_location_customer, container, false );

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );



        mapView = (MapView) view.findViewById( R.id.map );
        if (mapView != null)
        {
            mapView.onCreate( null );
            mapView.onResume();
            mapView.getMapAsync( this );

        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        UiSettings uiSettings = googleMap.getUiSettings();
        gMap = googleMap;
        enableLocation();
        LatLng coffeys = new LatLng( 54.572720, -5.959151 );
        gMap.addMarker( new MarkerOptions().position( coffeys ).title( "Coffey's Butchers" ) );
        gMap.moveCamera( CameraUpdateFactory.newLatLngZoom( coffeys, 12 ) );
        uiSettings.setZoomControlsEnabled(true);

    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            // GPS may be turned off
            if (location == null)
            {
                return;
            }

            Double lat = location.getLatitude();
            Double lng = location.getLongitude();

            currentLocation = location;
            Toast.makeText( getActivity(), "Updated Location: " + lat + lng, Toast.LENGTH_SHORT ).show();

        }
    };



    public void enableLocation()
    {
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
        if(permissionCheck == PackageManager.PERMISSION_GRANTED)
        {

            gMap.setMyLocationEnabled( true );
        }
    }

}

