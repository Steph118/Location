package com.example.livedataxample.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.livedataxample.R;
import com.example.livedataxample.adapters.LocationAgenceAdapter;
import com.example.livedataxample.classes.Agence;
import com.example.livedataxample.classes.LocationAgence;
import com.example.livedataxample.classes.Sfd;
import com.example.livedataxample.classes.ViewModelLocation;
import com.example.livedataxample.databinding.FragmentLocationMapsBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LocalisationAgencesFragment extends Fragment {

    private FragmentLocationMapsBinding binding;
    private ViewModelLocation viewModelLocation;
    private Context fragContext;
    private LocationAgenceAdapter adapter;
    private List<LocationAgence> locations = new ArrayList<>();
    private OnMapReadyCallback callback;
    private LocationCallback locationCallback;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;
    private final Sfd sfd = new Sfd("S1", "COCEC");
    private FusedLocationProviderClient fusedLocationClient;
    private Location mCurrentLocation = new Location("currentPosition") ;

    double i = 0;
    double latU,longU;

    public static LocalisationAgencesFragment newInstance() {
        return new LocalisationAgencesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(fragContext);
        getPositionCurrent();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLocationMapsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            callBackMethod();
            mapFragment.getMapAsync(callback);
        }
        init();
    }

    public void init() {
        viewModelLocation = new ViewModelProvider(this).get(ViewModelLocation.class);
        binding.sfdNom.setText(sfd.getLibelle());
        binding.recyclerViewAgences.setLayoutManager(new LinearLayoutManager(getContext()));
        /*adapter = new LocationAgenceAdapter(fragContext, locations,
                viewModelLocation.getLocationsAgencesMut(), mCurrentLocation);*/

        /* ** */
        Toast.makeText(fragContext, " "+longU, Toast.LENGTH_SHORT).show();
        /* ** */

        adapter = new LocationAgenceAdapter(fragContext, locations, latU, longU,
                viewModelLocation.getLocationsAgencesMut());
        onRecyclerViewClick(adapter);
        binding.recyclerViewAgences.setAdapter(adapter);
        getLocationPermission();
    }

    public void callBackMethod() {
        locations = getAllLocations();
            /*
            Google maps callback call
             */
        callback = new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                if (!locations.isEmpty()) {
                    for (LocationAgence location : locations) {
                        //Add a marker for location
                        location.setMarker(
                                googleMap.addMarker(new MarkerOptions()
                                        .position(location.getLatLng())
                                        .title(location.getAgence().getLibelle())
                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                                        .snippet(String.valueOf(location.getNumero()))
                                )
                        );
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location.getLatLng()));
                    }
                }

            }
        };
    }

    /**
     * RecyclerView click interface
     * @param adapter
     */
    public void onRecyclerViewClick(LocationAgenceAdapter adapter) {
        adapter.setInterfaceOnClick(new LocationAgenceAdapter.InterfaceOnClick() {
            @Override
            public void onClickItemConstraint(View v, int position) {
                for (LocationAgence lg : locations) {
                    lg.getMarker().setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                }
                Marker marker = locations.get(position).getMarker();
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            }

            @Override
            public void onClickItemVoirPlus(View v, int position) {
            }
        });
    }


    /**
     * Create a list
     * @return
     */
    public List<LocationAgence> getAllLocations() {
        List<LocationAgence> list = new ArrayList<>();
        Agence agence1 = new Agence(
                "libelle", "localite", "telephone", "email",
                6.167712, 1.296551, sfd
        );
        Agence agence2 = new Agence(
                "libelle", "localite", "telephone", "email",
                6.171701, 1.313855, sfd
        );

        Agence agence3 = new Agence(
                "libelle", "localite", "telephone", "email",
                6.25458583, 1.28283964, sfd
        );

        LocationAgence locationAgence1 = new LocationAgence(agence1, 1);
        LocationAgence locationAgence2 = new LocationAgence(agence2, 2);
        LocationAgence locationAgence3 = new LocationAgence(agence3, 3);

        list.add(locationAgence1);
        list.add(locationAgence2);
        list.add(locationAgence3);

        return list;
    }

    //location of user
    protected void startLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    //mCurrentLocation = location;
                    //viewModelLocation.updateLocationsAgences(locations);
                    //Toast.makeText(fragContext, "++", Toast.LENGTH_SHORT).show();
                }
            }
        };
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    @Override
    public void onResume() {
        super.onResume();
        startLocationUpdates();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.fragContext = context;
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        this.mLocationPermissionGranted = false;
        if (ContextCompat.checkSelfPermission(this.fragContext.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            this.mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
            }
        }
    }

    public void getPositionCurrent() {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(),
                        new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location != null) {
                                    latU = location.getLatitude();
                                    longU = location.getLongitude();
                                    //Toast.makeText(fragContext, " "+longU, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
    }
}