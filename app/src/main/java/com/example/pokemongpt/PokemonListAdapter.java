package com.example.pokemongpt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemongpt.databinding.PokemonItemBinding;

import java.util.List;

public class PokemonListAdapter extends
        RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {
    List<Pokemon> pokemonList;
    private OnClickOnPokemonListener listener;

    public PokemonListAdapter(List<Pokemon> pokemonList) {
        assert pokemonList != null;
        this.pokemonList =pokemonList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokemonItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.pokemon_item, parent, false);
        return new ViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);
        holder.viewModel.setPokemon(pokemon);
    }
    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public void setOnClickOnNoteListener(OnClickOnPokemonListener listener) {
            this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private PokemonItemBinding binding;
        private PokemonViewModel viewModel = new PokemonViewModel();
        ViewHolder(PokemonItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.setPokemonViewModel(viewModel);
            this.binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null)
                        listener.onClickOnNote(0,viewModel.getNumber());
                }
            });

        }
    }
}
