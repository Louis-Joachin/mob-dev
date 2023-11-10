package com.example.pokemongpt;


import android.content.Context;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class MyThread extends Thread {
    private MyThreadEventListener listener;
    private AppDatabase db;
    public MyThread(MyThreadEventListener listener, Context context) {
        this.listener = listener;
        db = AppDatabase.getInstance(context);
    }

    public void run() {
        // Informer l'auditeur que le traitement est terminé
        listener.onEventInMyThread("Finished");
    }
    public List<Pokemon> getAll(){
        PokemonDAO pokemonDao = db.pokemonDao();
        // Récupérer tous les utilisateurs
        List<Pokemon> pokemons = pokemonDao.getAll();
        System.out.println(pokemons.size());
        listener.onEventInMyThread("Finished");
        return pokemons;
    }
    public void insertPokemons (List<Pokemon> listPokemon){
        PokemonDAO pokemonDao = db.pokemonDao();
        // Récupérer tous les utilisateurs
        pokemonDao.insertAll(listPokemon);
        System.out.println("pokemons inséré");
        listener.onEventInMyThread("Finished");
    }
}
