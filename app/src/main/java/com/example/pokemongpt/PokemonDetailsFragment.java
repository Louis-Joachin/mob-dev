package com.example.pokemongpt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemongpt.databinding.PokemonDetailsBinding;
import com.example.pokemongpt.databinding.PokemonItemBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PokemonDetailsFragment extends Fragment{
    Pokemon pokemon;
    String number;
    PokemonDetailsFragment(String varNumber){
        number = varNumber;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        PokemonDetailsBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.pokemon_details, container, false);
        this.importPokemon(binding,number);
        PokemonViewModel viewmodel = new PokemonViewModel();
        viewmodel.setPokemon(pokemon);
        binding.setPokemonViewModel(viewmodel);

         return binding.getRoot();
    }
    public void importPokemon(PokemonDetailsBinding binding, String number){
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
            int intNumber = Integer.parseInt(number.substring(1));
            JSONObject object = array.getJSONObject(intNumber);
            String name = object.getString("name");
            String image = object.getString("image");
            String type1 = object.getString("type1");
            String type2 = null;
            int id = getResources().getIdentifier(image,"drawable",
                    binding.getRoot().getContext().getPackageName());
            int idNotFound=getResources().getIdentifier(image+"_n","drawable",
                    binding.getRoot().getContext().getPackageName());
            if (object.has("type2")) {
                type2 = object.getString("type2");
                this.pokemon = new Pokemon(intNumber, name, id,idNotFound, POKEMON_TYPE.valueOf(type1), POKEMON_TYPE.valueOf(type2));
            }
            else{
                this.pokemon = new Pokemon(intNumber,name,id,idNotFound,POKEMON_TYPE.valueOf(type1),null);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private PokemonItemBinding binding;
        private PokemonViewModel viewModel = new PokemonViewModel();

        ViewHolder(PokemonItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.setPokemonViewModel(viewModel);
        }
    }
}
