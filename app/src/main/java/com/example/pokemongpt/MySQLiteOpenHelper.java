package com.example.pokemongpt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String tablePokedex = "pokedex";
    public static final String tablePokemonCapture = "pokemonCapture";
    public static final String columnIdPokedex ="number";

    public static final String columnName ="name";
    public static final String columnType1 ="type1";
    public static final String columnType2 ="type2";
    public static final String columnHeight ="height";
    public static final String columnWeight ="weight";
    public static final String columnVisibility ="isVisible";
    public static final String columnIdPokemonCapture ="id";
    public static final String columnLevel ="level";
    public static final String columnAttack ="attack";
    public static final String columnDefense ="defense";
    public static final String columnPV ="pv";


    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_CREATE = "create table"+ tablePokedex +"("+ columnIdPokedex +"integer primary key," + columnName + "text not null," + columnType1 + "text not null," + columnType2 + "text," + columnHeight + "integer not null," + columnWeight + " integer not null," + columnVisibility+ " integer not null);create table " + tablePokemonCapture+ "("+ columnIdPokemonCapture + " integer primary key autoincrement,"+ columnIdPokedex+" integer not null,"+ columnLevel+" integer not null,"+ columnAttack+" integer not null,"+columnDefense+" integer not null,"+columnPV+" integer not null)";
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
