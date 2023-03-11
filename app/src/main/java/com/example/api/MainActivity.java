package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.api.adapter.TraineeAdapter;
import com.example.api.entity.Trainee;
import com.example.api.entity.TraineeList;
import com.example.api.repository.TraineeRepository;
import com.example.api.services.TraineeService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TraineeService traineeService;
    ArrayList<Trainee> traineeArrayList;
    Trainee[] traineeList;
    ListView traineeListView;
    Button addTrainee;
    TraineeAdapter traineeAdapter;

    private static final String REQUIRE = "Require";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        traineeListView = findViewById(R.id.trainee_list);
        addTrainee = findViewById(R.id.add_trainee);

        traineeArrayList = new ArrayList<>();
        traineeAdapter = new TraineeAdapter(this, R.layout.trainee_item, traineeArrayList);
        traineeListView.setAdapter(traineeAdapter);

        traineeService = TraineeRepository.getTraineeService();

        getTrainees();

        addTrainee.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
            finish();
        });

    }

    private void getTrainees(){

        traineeArrayList.clear();

        try {
            Call<Trainee[]> call = traineeService.getAllTrainees();
            call.enqueue(new Callback<Trainee[]>() {
                @Override
                public void onResponse(Call<Trainee[]> call, Response<Trainee[]> response) {

                    traineeList = response.body();
                    for (Trainee trainee : traineeList) {
                        long id = trainee.getId();
                        String name = trainee.getName();
                        String email = trainee.getEmail();
                        String phone = trainee.getPhone();
                        String gender = trainee.getGender();

                        traineeArrayList.add(new Trainee(id, name, email, phone, gender));

                    }

                    traineeAdapter.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<Trainee[]> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Get all fail!", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e){
            Log.d("ERROR", e.getMessage());
        }
    }

    public void dialogDeleteTask(String name, long id){
        AlertDialog.Builder deleteDialog = new AlertDialog.Builder(this);
        deleteDialog.setMessage("Are you sure you want to delete " + name + " task?");
        deleteDialog.setPositiveButton("Yes", (dialog, which) -> {
            Call<Trainee> call = traineeService.deleteTrainee(id);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    Toast.makeText(MainActivity.this, "You deleted " + name, Toast.LENGTH_SHORT).show();
                    getTrainees();
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Fail deleted " + name, Toast.LENGTH_SHORT).show();
                }
            });
        });

        deleteDialog.setNegativeButton("No", (dialog, which) -> {

        });

        deleteDialog.show();
    }
}