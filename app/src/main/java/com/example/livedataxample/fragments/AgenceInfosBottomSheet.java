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


/*
    private static final String ARG_ITEM_COUNT = "item_count";
    private FragmentAgenceInfosBottomSheetListDialogBinding binding;

    // TODO: Customize parameters
    public static AgenceInfosBottomSheet newInstance(int itemCount) {
        final AgenceInfosBottomSheet fragment = new AgenceInfosBottomSheet();
        final Bundle args = new Bundle();
        args.putInt(ARG_ITEM_COUNT, itemCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

      binding = FragmentAgenceInfosBottomSheetListDialogBinding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ItemAdapter(getArguments().getInt(ARG_ITEM_COUNT)));
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        final TextView text;

    ViewHolder(FragmentAgenceInfosBottomSheetListDialogItemBinding binding) {
      super(binding.getRoot());
      text = binding.text;
    }

    }

    private class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final int mItemCount;

        ItemAdapter(int itemCount) {
            mItemCount = itemCount;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    return new ViewHolder(FragmentAgenceInfosBottomSheetListDialogItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.text.setText(String.valueOf(position));
        }

        @Override
        public int getItemCount() {
            return mItemCount;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }*/

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