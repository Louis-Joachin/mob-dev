package com.example.pokemongpt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pokemongpt.databinding.MapFragmentBinding;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class MapFragment extends Fragment {
    MapFragmentBinding binding;
    Context context;    //  MapFragmentBinding binding;
    private MyLocationNewOverlay mLocationOverlay;
    private LocationManager locationManager = null;

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
        binding.mapView.getController().setZoom(13);
        
        return binding.getRoot();
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
}
