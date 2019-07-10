package com.example.myapplication.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements ITaskDAO {

    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public TaskDAO(Context context) {
        DbHelper db = new DbHelper(context);
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    @Override
    public boolean save(Task task) {
        ContentValues cv = new ContentValues();
        cv.put("name", task.getName());

        try {
            write.insert(DbHelper.TASK_TABLE, null, cv);
            Log.i("INFO_DB", "VALOR SALVO");
        } catch (Exception e) {
            Log.i("INFO_DB", e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Task task) {

        try {
            String[] args = {task.getId().toString()};
            write.delete(DbHelper.TASK_TABLE, "id=?", args);
            Log.i("INFO_DB", "Sucesso ao atualizar:");
        } catch (Exception e) {
            Log.i("INFO_DB", "Erro ao deletar: " + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean update(Task task) {
        ContentValues cv = new ContentValues();
        cv.put("name", task.getName());
        try {
            String[] args = {task.getId().toString()};
            write.update(DbHelper.TASK_TABLE, cv, "id=?", args);
            Log.i("INFO_DB", "Sucesso ao atualizar");

        } catch (Exception e) {
            Log.i("INFO_DB", "Erro ao atualizar: " + e.getMessage());
        }
        return true;
    }

    @Override
    public List<Task> list() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM " + DbHelper.TASK_TABLE + " ;";
        Cursor cursor = read.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Task task = new Task();

            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));

            task.setId(id);
            task.setName(name);
            tasks.add(task);
        }

        return tasks;
    }
}
