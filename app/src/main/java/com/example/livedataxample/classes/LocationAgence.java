package com.example.livedataxample.classes;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class LocationAgence {
    private Agence agence;
    private LatLng latLng ;
    private int numero;
    private Marker marker;
    private Double distance;
    public LocationAgence() {
    }
    public LocationAgence(Agence agence, LatLng latLng,
                          int numero, Marker marker) {
        this.agence = agence;
        this.latLng = latLng;
        this.numero = numero;
        this.marker = marker;
    }
    public LocationAgence(Agence agence, int numero) {
        this.agence = agence;
        this.numero = numero;
        this.latLng = new LatLng(agence.getLatitude(),agence.getLongitude());
    }
    public LocationAgence(Agence agence) {
        this.agence = agence;
        this.latLng = new LatLng(agence.getLatitude(),agence.getLongitude());
    }
    public Agence getAgence() {
        return agence;
    }
    public void setAgence(Agence agence) {
        this.agence = agence;
    }
    public LatLng getLatLng() {
        return latLng;
    }
    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public Marker getMarker() {
        return marker;
    }
    public void setMarker(Marker marker) {
        this.marker = marker;
    }
    public Double getDistance() {
        return distance;
    }
    public void setDistance(Double distance) {
        this.distance = distance;
    }
    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
