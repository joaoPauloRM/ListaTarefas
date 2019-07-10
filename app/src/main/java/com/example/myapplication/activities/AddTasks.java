package com.example.myapplication.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.helper.TaskDAO;
import com.example.myapplication.model.Task;

public class AddTasks extends AppCompatActivity {

    private TextInputEditText inputTask;
    private Task currentTaks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);

        inputTask = findViewById(R.id.imputTaskId);

        currentTaks = (Task) getIntent().getSerializableExtra("selectedTask");
        if (currentTaks != null) {
            inputTask.setText(currentTaks.getName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.item_saveId:

                TaskDAO taskDAO = new TaskDAO(getApplicationContext());

                if (currentTaks != null) {//Editar tarefas
                    String taskName = inputTask.getText().toString();
                    if (!taskName.isEmpty()) {
                        Task task = new Task();
                        task.setId(currentTaks.getId());
                        task.setName(taskName);
                        if (taskDAO.update(task)) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Atualizado",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Digite tarefa",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {//Salvando tarefas

                    String taskName = inputTask.getText().toString();

                    if (!taskName.isEmpty()) {
                        Task task = new Task();
                        task.setName(taskName);
                        if (taskDAO.save(task)) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Salvo",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Digite tarefa",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
