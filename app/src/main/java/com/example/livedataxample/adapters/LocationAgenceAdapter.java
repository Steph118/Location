package com.example.livedataxample.adapters;

import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.RecyclerView;

import com.example.livedataxample.R;
import com.example.livedataxample.classes.Agence;
import com.example.livedataxample.classes.LocationAgence;
import com.example.livedataxample.classes.ViewModelLocation;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import java.util.List;
import java.util.Random;

public class LocationAgenceAdapter extends RecyclerView.Adapter<LocationAgenceAdapter.LocationViewHolder>{
    private Context context;
    private List<LocationAgence> locations;
    private Location locationUser;

    private LiveData<List<LocationAgence>> locationsLiveData;
    //private ViewModelLocation viewModelLocation;

    public interface InterfaceOnClick{
        void onClickItemConstraint(View v , int position);
        void onClickItemVoirPlus(View v , int position);
    }
    private InterfaceOnClick interfaceOnClick;

    public void setInterfaceOnClick(InterfaceOnClick interfaceOnClick) {
        this.interfaceOnClick = interfaceOnClick;
    }

    public LocationAgenceAdapter(Context context, List<LocationAgence> locations) {
        this.context = context;
        this.locations = locations;
    }

    public LocationAgenceAdapter(Context context, List<LocationAgence> locations,
                                 LiveData<List<LocationAgence>> locationsLiveData) {
        this.context = context;
        this.locations = locations;
        this.locationsLiveData = locationsLiveData;
    }

    public LocationAgenceAdapter(Context context, List<LocationAgence> locations,
                                  LiveData<List<LocationAgence>> locationsLiveData,Location locationUser) {
        this.context = context;
        this.locations = locations;
        this.locationUser = locationUser;
        this.locationsLiveData = locationsLiveData;
    }

    public String distance(Location startLocation, LatLng end){
        if (startLocation!=null){
            Location endLocation = new Location("");
            endLocation.setLatitude(end.latitude);
            endLocation.setLongitude(end.longitude);
            return distance(startLocation.distanceTo(endLocation) / 1000 );
        }
        else{
            return " ";
        }
    }
/*********/
    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.agence_localisation_item,parent,false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        Agence agence = locations.get(position).getAgence();
        holder.libelle.setText(agence.getLibelle());
        holder.addresse.setText(agence.getLocalite());
        String distanceOf = distance(locationUser,locations.get(position).getLatLng());
        distanceOf += " Km";
        holder.distance.setText(distanceOf);
        locationsLiveData.observeForever(new Observer<List<LocationAgence>>() {
            @Override
            public void onChanged(List<LocationAgence> list) {
                String distanceOf = ""+ distance(list.get(holder.getAdapterPosition()).getDistance()) + " Km";
                holder.distance.setText(distanceOf);
                //Toast.makeText(context, "test", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder{
    TextView libelle, distance, addresse, voirPlus;
    ConstraintLayout constraintLayout;
        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            libelle = itemView.findViewById(R.id.libelleAgence);
            distance = itemView.findViewById(R.id.distanceKm);
            addresse = itemView.findViewById(R.id.addresse);
            voirPlus=itemView.findViewById(R.id.voirPlus);
            constraintLayout =  itemView.findViewById(R.id.locationConstraint);

            constraintLayout.setOnClickListener(view -> {
                if (interfaceOnClick!=null){
                    interfaceOnClick.onClickItemConstraint(view,getLayoutPosition());
                }
            });

            voirPlus.setOnClickListener(view -> {
                if (interfaceOnClick!=null){
                    interfaceOnClick.onClickItemVoirPlus(view,getLayoutPosition());
                }
            });
        }
    }
    public String distance(double x){
        return String.format("%.2f", x);
    }
    public void updateUserList(int position) {
        notifyDataSetChanged();
    }
}
