package com.example.pokemongpt;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Marker;

public class Pokemon_on_map extends Pokemon{
    Marker marker;

    public Pokemon_on_map(Pokemon pokemon, Marker pokemonMarker){
        super(pokemon.getOrder(),pokemon.getName(),pokemon.getFrontResource(),pokemon.getType1(),pokemon.getType2());
        this.marker = pokemonMarker;
    }
}
