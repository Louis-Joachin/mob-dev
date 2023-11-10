package com.example.pokemongpt.user;

import android.graphics.drawable.Drawable;

import com.example.pokemongpt.Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class User {
    private String nickname;
    private String email;
    private static int currentId;
    private int user_id;
    private Inventory inventory = new Inventory();
    private ArrayList<Pokemon> userPokemonList = new ArrayList<Pokemon>();
    public User(String nickname_parameter, String email){
        this.nickname = nickname_parameter;
        this.email = email;
        this.user_id = getId();
        this.addPokemon(new Pokemon());
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

    public ArrayList<Pokemon> getUserPokemonList() {
        ArrayList<Pokemon> result = (ArrayList<Pokemon>) userPokemonList.clone();
        return result;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail(){
        return email;
    }


}
