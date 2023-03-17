package com.example.livedataxample.classes;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ViewModelLocation extends ViewModel {
    private MutableLiveData<List<LocationAgence>> locationsAgencesMut = new MutableLiveData<>();
    private MutableLiveData<LocationAgence> locationAgenceMut;


    public MutableLiveData<List<LocationAgence>> getLocationsAgencesMut() {
        if (locationsAgencesMut ==null){
            return locationsAgencesMut = new MutableLiveData<>();
        }
        return locationsAgencesMut;
    }

    public void setLocationsAgencesMut(MutableLiveData<List<LocationAgence>> locationsAgencesMut) {
        this.locationsAgencesMut = locationsAgencesMut;
    }

    public MutableLiveData<LocationAgence> getLocationAgenceMut() {
        if (locationsAgencesMut ==null){
            return locationAgenceMut = new MutableLiveData<>();
        }
        return locationAgenceMut;
    }

    public void setLocationAgenceMut(MutableLiveData<LocationAgence> locationAgenceMut) {
        this.locationAgenceMut = locationAgenceMut;
    }

    public void updateLocationsAgences(List<LocationAgence> list) {
        locationsAgencesMut.setValue(list);
    }
}
