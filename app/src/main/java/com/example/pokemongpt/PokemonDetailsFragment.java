package com.example.pokemongpt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pokemongpt.databinding.PokedexFragmentBinding;
import com.example.pokemongpt.databinding.PokemonDetailsBinding;

public class PokemonDetailsFragment extends Fragment {
    Pokemon pokemon;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        PokemonDetailsBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.pokemon_details, container, false);

        return binding.getRoot();
    }
}
