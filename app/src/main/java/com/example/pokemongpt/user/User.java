package com.example.pokemongpt.user;

import com.example.pokemongpt.Pokemon;

import java.util.List;
import java.util.ListIterator;

public class User {
    private String nickname;
    private String email;
    private static int currentId;
    private int user_id;
    private Inventory inventory = new Inventory();
    private List<Pokemon> userPokemonList;
    public User(String nickname_parameter){
        String nickname = nickname_parameter;
        this.user_id = getId();
    }
    private int getId(){
        currentId ++;
        return currentId;
    }

    public void addPokemon(Pokemon pokemon){
        this.userPokemonList.add(pokemon);
    }

    public void releasePokemon(Pokemon pokemonToRemove){
        ListIterator<Pokemon> iterator = userPokemonList.listIterator();
        Pokemon currentPokemon;
        while(iterator.hasNext()) {
            currentPokemon = iterator.next();
            if (currentPokemon.equals(pokemonToRemove)) {
                iterator.remove();
            }
        }
    }
}
