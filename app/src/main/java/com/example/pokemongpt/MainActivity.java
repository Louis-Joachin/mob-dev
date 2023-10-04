package com.example.pokemongpt;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.BoolRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pokemongpt.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView
        .OnItemSelectedListener {
    private ActivityMainBinding binding;
    PokedexFragment pokedexfragment = new PokedexFragment();
    MapFragment mapfragment = new MapFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.bottomNavigation
                .setOnItemSelectedListener(this);
        binding.bottomNavigation.setSelectedItemId(R.id.pokedex);
        this.showStartup();
    }

    public void showDetails() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        PokemonDetailsFragment fragment = new PokemonDetailsFragment();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }
    public void showStartup() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        OnClickOnPokemonListener listener = new OnClickOnPokemonListener(){

            @Override
            public void onClickOnNote(long noteId, String number){
                showNoteDetail(noteId, number);
            }
        };
        pokedexfragment.setOnClickOnNoteListener(listener);
        transaction.replace(R.id.fragment_container,pokedexfragment);
        transaction.commit();
    }

    private void showNoteDetail(long noteId,String number) {
        System.out.println(number);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.pokedex){
            this.showStartup();
            /*getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, pokedexfragment)
                    .commit();*/
            return true;
        } else if (item.getItemId() == R.id.map) {
            System.out.println("appuie Map");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, mapfragment )
                    .commit();
            return true;
        }
        return false;
    }
}