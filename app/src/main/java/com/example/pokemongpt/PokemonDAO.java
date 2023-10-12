package com.example.pokemongpt;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PokemonDAO {
    @Insert
    void insertAll(List<Pokemon> listPokemon);
    @Query("SELECT * FROM pokemon")
    List<Pokemon> getAll();

    @Query("SELECT * FROM pokemon WHERE order = :number")
    Pokemon findByNumber(int number);

}
