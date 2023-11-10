package com.example.pokemongpt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pokemongpt.databinding.MapFragmentBinding;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class MapFragment extends Fragment {
    MapFragmentBinding binding;
    Context context;    //  MapFragmentBinding binding;
    private MyLocationNewOverlay mLocationOverlay;
    private LocationManager locationManager;
    private List<Pokemon> pokemonList;
    private static int markerId = 0;
    private List<Pokemon_on_map> listPokemonOnMap = new ArrayList<Pokemon_on_map>();;
    Marker playerPosition;

    public MapFragment(){}

    public MapFragment(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater,
                R.layout.map_fragment, container, false);
        this.context = binding.getRoot().getContext();
        Configuration.getInstance().load(context,
                PreferenceManager.
                        getDefaultSharedPreferences(context));
        binding.mapView.setTileSource(
                TileSourceFactory.MAPNIK);


        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        GeoPoint point = new GeoPoint(location);
        binding.mapView.getController().setCenter(point);
        binding.mapView.getController().setZoom(18);
        binding.mapView.setMultiTouchControls(true);
        binding.mapView.setBuiltInZoomControls(true);

        this.playerPosition = new Marker(binding.mapView);
        playerPosition.setPosition(point);
        playerPosition.setAnchor(Marker.ANCHOR_BOTTOM,Marker.ANCHOR_BOTTOM);
        playerPosition.setIcon(getResources().getDrawable(R.mipmap.player_position_foreground));
        playerPosition.setTitle("Player");
        binding.mapView.getOverlays().add(playerPosition);

        this.generatePokemonsOnMap(50);
        this.updateMap();
        return binding.getRoot();
    }

    public void setLocationManager(LocationManager locationManager){
        this.locationManager = locationManager;
    }
    public LocationManager getLocationManager(){
        return this.locationManager;
    }

    public void updateMap() {
        System.out.println("UPDATE !");
        if (this.locationManager==null || this.binding==null){return;}
        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        GeoPoint point = new GeoPoint(location);

        binding.mapView.getOverlays().remove(playerPosition);

        this.playerPosition.setPosition(point);
        binding.mapView.getOverlays().add(playerPosition);

        binding.mapView.getController().setCenter(point);

        binding.mapView.invalidate();

        this.managePokemonsOnMap();

    }
    public void drawPokemonsOnMap(){
        ListIterator<Pokemon_on_map> iterator = listPokemonOnMap.listIterator();
        Pokemon_on_map pokemonOnMap;

        while (iterator.hasNext()){
            pokemonOnMap = iterator.next();
            binding.mapView.getOverlays().add(pokemonOnMap.marker);
        }
    }

    public void erasePokemonsOnMap(){
        ListIterator<Pokemon_on_map> iterator = listPokemonOnMap.listIterator();
        Pokemon_on_map pokemonOnMap;

        while (iterator.hasNext()){
            pokemonOnMap = iterator.next();
            binding.mapView.getOverlays().remove(pokemonOnMap.marker);
        }
    }

    public void generatePokemonsOnMap(int pokemonsToGenerate){
        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        GeoPoint playerPoint = new GeoPoint(location);

        for(int i=0; i<pokemonsToGenerate; i++){
            GeoPoint pokemonPosition;
            Marker pokemonMarker;

            pokemonMarker = new Marker(binding.mapView);
            pokemonPosition = generatePoint(playerPoint.getLatitude(),playerPoint.getLongitude());

            Random rand = new Random();
            Pokemon pokemon = this.pokemonList.get(rand.nextInt(pokemonList.size()));

            pokemonMarker.setPosition(pokemonPosition);
            pokemonMarker.setId(String.valueOf(getMarkerId()));
            pokemonMarker.setAnchor(Marker.ANCHOR_CENTER,Marker.ANCHOR_CENTER);
            pokemonMarker.setIcon(getResources().getDrawable(pokemon.getFrontResource()));
            pokemonMarker.setTitle(pokemon.getName());
            pokemonMarker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker, MapView mapView) {
                    System.out.println("Que le combat commence !!!");
                    return true;
                }
            });

            listPokemonOnMap.add(new Pokemon_on_map(pokemon,pokemonMarker));
        }
    }

    public void managePokemonsOnMap(){
        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        erasePokemonsOnMap();

        double coordinatesGap = 0.01;
        ListIterator<Pokemon_on_map> iterator = listPokemonOnMap.listIterator();
        Pokemon_on_map pokemonOnMap;
        while(iterator.hasNext()){
            pokemonOnMap = iterator.next();

            if(Math.abs(pokemonOnMap.marker.getPosition().getLatitude() - location.getLatitude()) > coordinatesGap ||
                    Math.abs(pokemonOnMap.marker.getPosition().getLongitude() - location.getLongitude())> coordinatesGap){
                iterator.remove();
            }

        }

        if(50 - listPokemonOnMap.size() > 0){
            generatePokemonsOnMap(50 - listPokemonOnMap.size());
        }
        drawPokemonsOnMap();
    }

    public Drawable getImage(Context context, int res) {
        if(res != -1)
            return ResourcesCompat.getDrawable(context.getResources(),
                    res, context.getTheme());
        else
            return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.mapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        binding.mapView.onPause();
    }

    public void setPokemonList(List<Pokemon> pokemonList){
        this.pokemonList = pokemonList;
    }

    public static GeoPoint generatePoint(double latitude, double longitude) {
        double coordinatesGap = 0.01;
        double randomLat = latitude - coordinatesGap + Math.random() * (2 * coordinatesGap);
        double randomLon = longitude - coordinatesGap + Math.random() * (2 * coordinatesGap);

        return new GeoPoint(randomLat,randomLon);
    }

    private int getMarkerId(){
        markerId ++;
        return markerId;
    }
}
