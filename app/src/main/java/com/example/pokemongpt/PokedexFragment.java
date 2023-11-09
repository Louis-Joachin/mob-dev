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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PokedexFragment extends Fragment {
    List<Pokemon> pokemonList = new ArrayList<>();
    PokedexFragmentBinding binding =null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.pokedex_fragment, container, false);
        Bundle args = getArguments();
        if (args != null) {
            AppDatabase datab = args.getParcelable("appDatabase");
            PokemonDAO pokemonDao = datab.pokemonDao();
            List<Pokemon> pokemons = pokemonDao.getAll();
        }
        binding.pokemonList.setLayoutManager(new LinearLayoutManager(
                binding.getRoot().getContext()));
        this.importPokemonList(binding);
        PokemonListAdapter adapter = new PokemonListAdapter(pokemonList);
        binding.pokemonList.setAdapter(adapter);
        adapter.setOnClickOnNoteListener(listener);
        return binding.getRoot();
    }

    public void importPokemonList(PokedexFragmentBinding binding){
        //Ouverture du fichier res/raw
        InputStreamReader isr = new InputStreamReader(getResources().openRawResource(R.raw.poke));
        // Ouverture du fichier dans assets
        // InputStreamReader isr =
        // new InputStreamReader(getResources().getAssets().open("poke.json"));
        BufferedReader reader = new BufferedReader(isr);
        StringBuilder builder = new StringBuilder();
        String data = "";
        //lecture du fichier. data == null => EOF
        while(data != null) {
            try {
                data = reader.readLine();
                builder.append(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Traitement du fichier
        try {
            JSONArray array = new JSONArray(builder.toString());
            Pokemon pokemon;
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String name = object.getString("name");
                String image = object.getString("image");
                String type1 = object.getString("type1");
                String type2 = null;
                int id = getResources().getIdentifier(image,"drawable",
                        binding.getRoot().getContext().getPackageName());
                if (object.has("type2")) {
                    type2 = object.getString("type2");
                    pokemon = new Pokemon(i, name, id, POKEMON_TYPE.valueOf(type1), POKEMON_TYPE.valueOf(type2));
                }
                else{
                    pokemon = new Pokemon(i,name,id,POKEMON_TYPE.valueOf(type1),null);
                }
                pokemonList.add(pokemon);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private OnClickOnPokemonListener listener;
    public void setOnClickOnNoteListener(OnClickOnPokemonListener listener)
    {
        this.listener = listener;
    }


}
