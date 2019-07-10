package com.example.myapplication.helper;

import com.example.myapplication.model.Task;

import java.util.List;

public interface ITaskDAO {
    public boolean save(Task task);
    public boolean delete(Task task);
    public boolean update(Task task);
    public List<Task> list();


}
