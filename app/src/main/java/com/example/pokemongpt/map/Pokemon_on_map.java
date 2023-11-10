package com.example.pokemongpt.map;

import com.example.pokemongpt.Pokemon;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Marker;

public class Pokemon_on_map extends Pokemon {
    Marker marker;

    public Pokemon_on_map(Pokemon pokemon, Marker pokemonMarker){
        super(pokemon.getOrder(),pokemon.getName(),pokemon.getFrontResource(), pokemon.getFrontResourceNotFound(), pokemon.getType1(),pokemon.getType2());
        this.marker = pokemonMarker;
    }
}
