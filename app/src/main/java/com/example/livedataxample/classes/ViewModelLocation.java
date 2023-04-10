package com.example.livedataxample.classes;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
public class ViewModelLocation extends ViewModel {
    private MutableLiveData<List<LocationAgence>> locationsAgencesMut = new MutableLiveData<>();
    private List<LocationAgence> locationAgences = new ArrayList<>();
    private final Sfd sfd = new Sfd("S1", "COCEC");
    public MutableLiveData<List<LocationAgence>> getLocationsAgencesMut() {
        if (locationsAgencesMut ==null){
            return locationsAgencesMut = new MutableLiveData<>();
        }
        return locationsAgencesMut;
    }
    public void setLocationsAgencesMut(MutableLiveData<List<LocationAgence>> locationsAgencesMut) {
        this.locationsAgencesMut = locationsAgencesMut;
    }
    public List<LocationAgence> getLocationAgences() {
        return locationAgences;
    }
    public void setLocationAgences(List<LocationAgence> locationAgences) {
        this.locationAgences = locationAgences;
    }
    public List<LocationAgence> getAllLocations() {
        List<LocationAgence> list = new ArrayList<>();
        Agence agence1 = new Agence(
                "HOTEL ECOLE AVENIDA", "Souza Netimé, Lomé, Togo", "+228 79 44 41 01", "avenida@venida.com",
                6.1322159, 1.2371659, sfd
        );

        Agence agence2 = new Agence(
                "BAR METRO", "BAR METRO", " 90 71 64 45", "mediasofthome@yahoo.fr",
                6.2392645,1.226526, sfd
        );

        Agence agence3 = new Agence(
                "AKATO PLAGE", "AKATO PLAGE", " 90 71 64 45", "mediasofthome@yahoo.fr",
                6.1877619, 1.1033047, sfd
        );

        LocationAgence locationAgence1 = new LocationAgence(agence1, 1);
        LocationAgence locationAgence2 = new LocationAgence(agence2, 2);
        LocationAgence locationAgence3 = new LocationAgence(agence3, 3);
        list.add(locationAgence1);
        list.add(locationAgence2);
        list.add(locationAgence3);
        return list;
    }
    public void updateLocationsAgences(List<LocationAgence> list) {
        this.locationsAgencesMut.setValue(list);
        this.locationAgences = list;
    }
    public void init(){
        this.setLocationAgences(getAllLocations());
    }
}
