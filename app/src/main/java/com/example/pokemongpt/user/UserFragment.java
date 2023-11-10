package com.example.pokemongpt.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pokemongpt.PokemonListAdapter;
import com.example.pokemongpt.PokemonViewModel;
import com.example.pokemongpt.R;
import com.example.pokemongpt.databinding.MapFragmentBinding;
import com.example.pokemongpt.databinding.UserFragmentBinding;

public class UserFragment extends Fragment {
    UserFragmentBinding binding;
    User user;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater,
                R.layout.user_fragment, container, false);

        UserViewModel userViewModel = new UserViewModel();
        userViewModel.setUser(user);
        binding.setUserViewModel(userViewModel);

        binding.pokemonInventory.setLayoutManager(new LinearLayoutManager(
                binding.getRoot().getContext()));
        PokemonListAdapter adapter = new PokemonListAdapter(user.getUserPokemonList());
        binding.pokemonInventory.setAdapter(adapter);

        return binding.getRoot();
    }

    public UserFragment(User user){
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
