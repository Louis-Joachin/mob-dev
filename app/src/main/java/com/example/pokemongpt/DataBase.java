package com.example.pokemongpt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    private static final int  version =1;
    private static final String DATABASE_NAME = "pokemomGPT.db";

    private static final String DATABASE_CREATE = "create table pokedex (number integer primary key,name text not null,type1 text not null,type2 text,height integer not null,weight integer not null,;";
    public DataBase(Context context){
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int OldNumberVersion, int NewNumberVersion) {

    }
}
