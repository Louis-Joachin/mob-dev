package com.example.pokemongpt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase {
    private static DataBase instance = null;
    private SQLiteOpenHelper helper;

//private static final String DATABASE_CREATE = "create table pokedex (number integer primary key,name text not null,type1 text not null,type2 text,height integer not null,weight integer not null,isVisible integer not null);create table pokemonCapture (id integer primary key autoincrement,number integer not null,level integer not null,attack integer not null,defense integer not null,pv integer not null)";;
    public static DataBase getInstance(Context context){
        if(instance == null)
            instance = new DataBase(context);
        return instance;
    }
    private DataBase(Context context){
        helper = new SQLiteOpenHelper(context,"Name",null,1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {

            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            }
        };
    }

}
