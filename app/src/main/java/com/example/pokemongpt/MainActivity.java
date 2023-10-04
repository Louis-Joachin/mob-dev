package com.example.pokemongpt;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pokemongpt.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener {
    private ActivityMainBinding binding;
    BottomNavigationView bottomNavigationView;
    PokedexFragment pokedexfragment = new PokedexFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bottomNavigationView
                = findViewById(R.id.bottom_navigation);

        bottomNavigationView
                .setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.page_1);
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
        switch (item.getItemId()) {
            case R.id.page_1:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, pokedexfragment)
                        .commit();
                return true;

            case R.id.page_2:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, secondFragment)
                        .commit();
                return true;
        }
        return false;
        return false;
    }
}