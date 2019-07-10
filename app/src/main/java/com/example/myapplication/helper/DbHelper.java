package com.example.myapplication.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String DB_NAME = "DB_TAREFAS";
    public static String TASK_TABLE = "tarefas";


    public DbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TASK_TABLE
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL );";
        try {
            db.execSQL(sql);
            Log.i("DB_INFO", "Sucesso ao criar tabela");

        } catch (Exception e) {
            Log.i("DB_INFO", "Erro ao criar" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
