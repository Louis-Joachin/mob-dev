package com.example.pokemongpt;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import org.osmdroid.config.Configuration;

public class MapFragment extends Fragment {
  //  MapFragmentBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.pokedex_fragment, container, false);

        Context context = binding.getRoot().getContext();
        Configuration.getInstance().load(context,
                PreferenceManager.getDefaultSharedPreferences(context));

        return null;
    }
}
