package com.example.pokemongpt.user;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.pokemongpt.Pokemon;

public class UserViewModel extends BaseObservable {
    private User user;

    public void setUser(User user) {
        this.user = user;
        notifyChange();
    }

    @Bindable
    public String getNickname() {
        return user.getNickname();
    }

    @Bindable
    public String getEmail() {
        return user.getEmail();
    }
}
