package com.example.Munasabati;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.Munasabati.databinding.ActivityMapServicesBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

import android.Manifest;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapServicesActivity extends FragmentActivity implements OnMapReadyCallback {
    public static String op= "";

    private GoogleMap mMap;
    private ActivityMapServicesBinding binding;
    private String TAG = "so47492459";
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    Location currentLocation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapServicesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLocation();




            @SuppressLint("MissingPermission") Task<Location> task = fusedLocationProviderClient.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        currentLocation = location;
                       // Toast.makeText(getApplicationContext(), currentLocation.getLatitude() + "" + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                        assert supportMapFragment != null;
                        supportMapFragment.getMapAsync(MapServicesActivity.this);
                    }
                }
            });


    }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
      /*  SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

*/

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("I am here!");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
        googleMap.addMarker(markerOptions);


        if (op.equals("getEvent")){

        mMap.addMarker(new MarkerOptions().position(new LatLng(EventActivity.selectedEvent.getLatitude(), EventActivity.selectedEvent.getLongitude())).title("Event Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        getDirections(currentLocation.getLatitude(), currentLocation.getLongitude(), EventActivity.selectedEvent.getLatitude(), EventActivity.selectedEvent.getLongitude());

        }

        else if (op.equals("newEvent")) {

         mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng arg0) {


                    android.util.Log.i("onMapClick", "Choose event location");
                    mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.latitude, arg0.longitude)).title("Event Location"));
                    newEventActivity.eventLatitude= String.valueOf(arg0.latitude);
                    newEventActivity.eventLongitude= String.valueOf(arg0.longitude);
                    Toast.makeText(getApplicationContext(), "Location picked!", Toast.LENGTH_SHORT).show();
                    Geocoder geocoder;
                    List<Address> addresses = null;
                    geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                    try {
                        addresses = geocoder.getFromLocation(arg0.latitude, arg0.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String address = addresses.get(0).getAddressLine(0);
                    String reducedAddress= address.split("\\,")[2];
                    newEventActivity.address=reducedAddress;
                    newEventActivity.locationString=reducedAddress;
                    finish();
                   // getDirections(currentLocation.getLatitude(), currentLocation.getLongitude(), arg0.latitude, arg0.longitude);

            }
         });
        }




        mMap.getUiSettings().setZoomControlsEnabled(true);


      // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 6));

       // Toast.makeText(this, "path size = ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLocation();
                }
                break;
        }
    }

        private void fetchLocation () {
            if (ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
                return;
            }

        }

        private void getDirections(double latitudeOrg, double longitudeOrg, double latitudeDis, double longitudeDis){
            List<LatLng> path = new ArrayList();
            String originlocation= latitudeOrg+","+longitudeOrg;
            String disLocation= latitudeDis+","+longitudeDis;
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey("AIzaSyBh--rFPgR5lD7_TRKKPniexU2Mb8MNGdI")
                    .build();
            DirectionsApiRequest req = DirectionsApi.getDirections(context, originlocation, disLocation);
            try {
                DirectionsResult res = req.await();

                //Loop through legs and steps to get encoded polylines of each step
                if (res.routes != null && res.routes.length > 0) {
                    DirectionsRoute route = res.routes[0];

                    if (route.legs !=null) {
                        for(int i=0; i<route.legs.length; i++) {
                            DirectionsLeg leg = route.legs[i];
                            if (leg.steps != null) {
                                for (int j=0; j<leg.steps.length;j++){
                                    DirectionsStep step = leg.steps[j];
                                    if (step.steps != null && step.steps.length >0) {
                                        for (int k=0; k<step.steps.length;k++){
                                            DirectionsStep step1 = step.steps[k];
                                            EncodedPolyline points1 = step1.polyline;
                                            if (points1 != null) {
                                                //Decode polyline and add points to list of route coordinates
                                                List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                for (com.google.maps.model.LatLng coord1 : coords1) {
                                                    path.add(new LatLng(coord1.lat, coord1.lng));
                                                }
                                            }
                                        }
                                    } else {
                                        EncodedPolyline points = step.polyline;
                                        if (points != null) {
                                            //Decode polyline and add points to list of route coordinates
                                            List<com.google.maps.model.LatLng> coords = points.decodePath();
                                            for (com.google.maps.model.LatLng coord : coords) {
                                                path.add(new LatLng(coord.lat, coord.lng));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch(Exception ex) {
                Log.e(TAG, ex.getLocalizedMessage());
            }

            //Draw the polyline
            if (path.size() > 0) {
                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.RED).width(7);
                mMap.addPolyline(opts);
            }
        }
}


/*
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        MarkerOptions marker = new MarkerOptions().position(sydney).title("Marker in Sydney");
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));


        mMap.addMarker(new MarkerOptions().position(new LatLng(-04, 151)).title(
                "fsdfsdf sdfsdf")).showInfoWindow();

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-14, 151))
                .title("Favoris")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.locationpng))
        );

        LatLng pos = marker.getPosition();
        String latitude = String.valueOf(pos.latitude);
        String longitude = String.valueOf(pos.longitude);

        LatLng markerLocation = marker.getPosition();


        String address = "";

        try {
            Geocoder geo = new Geocoder(this.getApplicationContext(), Locale.getDefault());
            List<Address> addresses = geo.getFromLocation(pos.latitude, pos.longitude, 1);
            if (addresses.isEmpty()) {
                address = "Waiting for Location";
            } else {
                if (addresses.size() > 0) {
                    address = addresses.get(0).getFeatureName() + ", " + addresses.get(0).getLocality() + ", " + addresses.get(0).getAdminArea() + ", " + addresses.get(0).getCountryName();
                    //Toast.makeText(getApplicationContext(), "Address:- " + addresses.get(0).getFeatureName() + addresses.get(0).getAdminArea() + addresses.get(0).getLocality(), Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // getFromLocation() may sometimes fail
        }


*/



/* LatLng barcelona = new LatLng(41.385064, 2.173403);
        mMap.addMarker(new MarkerOptions().position(barcelona).title("Marker in Barcelona"));
        mMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng madrid = new LatLng(40.416775, -3.70379);
        mMap.addMarker(new MarkerOptions().position(madrid).title("Marker in Madrid"));

        LatLng zaragoza = new LatLng(41.648823, -0.889085);

        //Define list to get all latlng for the route
        List<LatLng> path = new ArrayList();*/

        /*

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);


/*
        Location location=  myLastLocation.getResult();

        marker= new MarkerOptions().position(new LatLng( location.getLongitude(), location.getLatitude())).title("Marker in Sydney");
        MarkerOptions   marker2= new MarkerOptions().position(new LatLng( location.getLongitude()-10, location.getLatitude())).title("Marker in Sydney");


        String originlocation= location.getLatitude()+","+location.getLongitude();
        String disLocation= (location.getLatitude()-10)+","+location.getLongitude();

        //Execute Directions API request

         */