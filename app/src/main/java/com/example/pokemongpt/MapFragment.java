package com.example.pokemongpt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.pokemongpt.databinding.MapFragmentBinding;

public class MapFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        MapFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.map_fragment,container,false);
        return binding.getRoot();
    }
}
