package com.example.pokemongpt.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.pokemongpt.R;
import com.example.pokemongpt.databinding.MapFragmentBinding;
import com.example.pokemongpt.databinding.UserFragmentBinding;

public class UserFragment extends Fragment {
    UserFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater,
                R.layout.user_fragment, container, false);
        return null;
    }
}
