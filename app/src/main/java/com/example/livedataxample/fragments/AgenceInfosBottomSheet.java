package com.example.livedataxample.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.livedataxample.classes.Agence;
import com.example.livedataxample.databinding.FragmentAgenceInfosBottomSheetListDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AgenceInfosBottomSheet extends BottomSheetDialogFragment {
    private FragmentAgenceInfosBottomSheetListDialogBinding binding;
    public static String TAG = "agenceInfosBottomSheet";
    private TextView libelle, localite, mail, tel, horaire;

    public static AgenceInfosBottomSheet newInstance() {
        /*final Bundle args = new Bundle();
        args.putInt(ARG_ITEM_COUNT, itemCount);
        fragment.setArguments(args);*/
        return new AgenceInfosBottomSheet();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAgenceInfosBottomSheetListDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
     init();
    }
    public void init() {
        Bundle bundle = getArguments();
        if (bundle!=null){
            Agence agence =  (Agence) bundle.get("agenceInfos");
            binding.agenceLibelle.setText(agence.getLibelle());
            binding.agenceAdresse.setText(agence.getLocalite());
            binding.emailAgence.setText(agence.getEmail());
            binding.telNumAgence.setText(agence.getTelephone());
        }
    }

}