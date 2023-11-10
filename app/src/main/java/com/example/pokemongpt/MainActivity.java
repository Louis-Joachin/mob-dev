package com.example.pokemongpt;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pokemongpt.databinding.ActivityMainBinding;
import com.example.pokemongpt.map.CaptureListener;
import com.example.pokemongpt.map.MapFragment;
import com.example.pokemongpt.user.User;
import com.example.pokemongpt.user.UserFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView
        .OnItemSelectedListener {
    private ActivityMainBinding binding;
    PokedexFragment pokedexfragment = new PokedexFragment();

    LocationManager locationManager;
    MapFragment mapfragment;
    User user = new User("John Doe","john.doe@gmail.com");
    UserFragment userfragment = new UserFragment(user);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mapfragment = new MapFragment();
        this.mapfragment.setPokemonList(this.pokedexfragment.pokemonList);
        this.askForPermission();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.bottomNavigation
                .setOnItemSelectedListener(this);
        binding.bottomNavigation.setSelectedItemId(R.id.pokedex);

    }

    public void showDetails(long noteId, String number) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        PokemonDetailsFragment fragment = new PokemonDetailsFragment(number);
        transaction.replace(R.id.fragment_container,fragment);
        transaction.addToBackStack("fragment");
        transaction.commit();
    }
    public void showStartup() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        OnClickOnPokemonListener listener = new OnClickOnPokemonListener(){

            @Override
            public void onClickOnNote(long noteId, String number){
                showDetails(noteId, number);
            }
        };
        pokedexfragment.setOnClickOnNoteListener(listener);
        transaction.replace(R.id.fragment_container,pokedexfragment);
        transaction.commit();
    }

    /*public void showMap(LocationManager locationManager) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction= manager.beginTransaction();
        MapFragment fragment = new MapFragment(locationManager);
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();

    }*/

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
            if(ActivityCompat.checkSelfPermission( this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED) {
                System.out.println("appuie Map");

                CaptureListener listener = new CaptureListener() {
                    @Override
                    public void onClickOnMarker(Pokemon pokemon) {
                        user.addPokemon(pokemon);
                    }
                };
                mapfragment.setCaptureListener(listener);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, mapfragment)
                        .commit();
                return true;
            }

            else {
                System.out.println("Permission denied");
                return false;
            }

        }
        else if (item.getItemId() == R.id.user) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, userfragment)
                    .commit();
            return true;
        }
        return false;
    }

    public void askForPermission(){
        if (ActivityCompat.checkSelfPermission( this,
        android.Manifest.permission.ACCESS_FINE_LOCATION) !=
        PackageManager.PERMISSION_GRANTED) {
            String[] permissions =
                    {android.Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this,
                    permissions,1);
        }
        else{
            this.createManager();
            mapfragment.setLocationManager(this.locationManager);
            //System.exit(0);
        }
    }
    @Override
    public void onRequestPermissionsResult(int
                                                   requestCode, @NonNull final String[]
                                                   permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,
                permissions, grantResults);
        if(grantResults[0] ==
                PackageManager.PERMISSION_GRANTED) {//on a la permission
            System.out.println("Autorisation effectuée");
            this.createManager();
            mapfragment.setLocationManager(this.locationManager);
        } else {//afficher un message d’erreur
            System.out.println("Erreur autorisation"); //TODO ajouter popup
            System.exit(0);
        }
    }

    @SuppressLint("MissingPermission")
    public void createManager(){
        LocationListener myLocationListener = new
                LocationListener(){
                    @Override
                    public void onLocationChanged(Location newLocation){
                        if(mapfragment!=null){mapfragment.updateMap();}
                    }
                    @Override
                    public void onStatusChanged(String provider, int
                            status, Bundle extras){
                    }
                    @Override
                    public void onProviderEnabled(String provider){
                    }
                    @Override
                    public void onProviderDisabled(String provider){
                    }
                };
        this.locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 120, 100,
                myLocationListener);
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 120, 100,
                myLocationListener);
    }
}