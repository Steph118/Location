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

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
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
import com.google.android.gms.common.api.internal.LifecycleFragment;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LocalisationAgencesFragment extends Fragment implements LifecycleOwner{

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

    /*private final Observer<List<LocationAgence>> observer
            = new Observer<List<LocationAgence>>() {
        @Override
        public void onChanged(List<LocationAgence> list) {
            adapter.updateUserList(list);
        }
    };*/

    public static LocalisationAgencesFragment newInstance() {
        return new LocalisationAgencesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(fragContext);
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
        permit();
    }

    public void init() {
        binding.recyclerViewAgences.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LocationAgenceAdapter(fragContext, locations,
                viewModelLocation.getLocationsAgencesMut(), mCurrentLocation);
        onRecyclerViewClick(adapter);
        binding.recyclerViewAgences.setAdapter(adapter);
    }

    public void callBackMethod() {
        viewModelLocation = new ViewModelProvider(this).get(ViewModelLocation.class);
        viewModelLocation.init();
        locations = viewModelLocation.getLocationAgences();
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

            /**
             * @param v
             * @param position
             * Change the color of a marker of item when his constraint's layout is clicked
             */
            @Override
            public void onClickItemConstraint(View v, int position) {
                for (LocationAgence lg : locations) {
                    lg.getMarker().setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                }
                Marker marker = locations.get(position).getMarker();
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            }

            /**
             *
             * @param v
             * @param position
             * when a item is clicked
             */
            @Override
            public void onClickItemVoirPlus(View v, int position) {
                AgenceInfosBottomSheet agenceInfosBottomSheet = AgenceInfosBottomSheet.newInstance();
                Bundle bundle = new Bundle();
                bundle.putSerializable("agenceInfos",locations.get(position).getAgence());
                agenceInfosBottomSheet.setArguments(bundle);
                agenceInfosBottomSheet.show(getParentFragmentManager()
                        ,AgenceInfosBottomSheet.TAG);
            }
        });
    }


    /**
     * Create a list
     * @return
     */

    protected void startLocationUpdates() {
        /*
            * update location of user
            * get a current location of user
            * after Any 5 secondes
        */
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
                    mCurrentLocation = location;
                    Random random =new Random();
                    mCurrentLocation.setLongitude(random.nextDouble() + 1);
                    mCurrentLocation.setLatitude(random.nextDouble() + 6);
                    if (locations != null){
                        for (LocationAgence loc : locations){
                            loc.setDistance(distance(mCurrentLocation,loc.getLatLng()));
                        }
                        //viewModelLocation.getLocationsAgencesMut().setValue(locations);
                        viewModelLocation.updateLocationsAgences(locations);
                        //viewModelLocation.getLocationsAgencesMut().observe(LocalisationAgencesFragment.this,observer);
                    }
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
        if (ContextCompat.checkSelfPermission(/*this.fragContext.getApplicationContext()*/fragContext,
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
        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
            }
        }
    }

    /*
    get a current location of user
     */
    public void getPositionCurrent() {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(),
                        new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location != null) {
                                    mCurrentLocation = location;
                                    init();
                                }
                            }
                        });
    }
    public double distance(Location startLocation, LatLng end){
        if (startLocation!=null){
            Location endLocation = new Location("");
            endLocation.setLatitude(end.latitude);
            endLocation.setLongitude(end.longitude);
            return startLocation.distanceTo(endLocation) / 1000 ;
        }
        else{
            return 0;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    public void permit(){
        ActivityResultLauncher<String[]> locationPermissionRequest =
                registerForActivityResult(new ActivityResultContracts
                                .RequestMultiplePermissions(), result -> {
                            Boolean fineLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_FINE_LOCATION, false);
                            Boolean coarseLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_COARSE_LOCATION,false);
                            if (fineLocationGranted != null && fineLocationGranted) {
                                getPositionCurrent();
                            } else if (coarseLocationGranted != null && coarseLocationGranted) {
                                getPositionCurrent();
                            } else {
                                mCurrentLocation = null;
                                init();
                            }
                        }
                );

        locationPermissionRequest.launch(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
    }
}