package com.example.api;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.api.entity.Trainee;
import com.example.api.repository.TraineeRepository;
import com.example.api.services.TraineeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    TextView textName, textEmail, textPhone, textGender;
    Button editTrainee, deleteTrainee;
    ImageView imageBack;

    TraineeService traineeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textName= findViewById(R.id.text_name);
        textEmail = findViewById(R.id.text_email);
        textPhone = findViewById(R.id.text_phone_number);
        textGender = findViewById(R.id.text_gender);
        editTrainee = findViewById(R.id.edit_trainee);
        deleteTrainee = findViewById(R.id.delete_trainee);
        imageBack = findViewById(R.id.back_image);

        traineeService = TraineeRepository.getTraineeService();

        Intent reciveIntent = getIntent();

        Bundle reciveBundle = reciveIntent.getExtras();

        Trainee trainee = reciveBundle.getParcelable("traineeDetail");

        textName.setText(trainee.getName());
        textEmail.setText(trainee.getEmail());
        textPhone.setText(trainee.getPhone());
        textGender.setText(trainee.getGender());


        imageBack.setOnClickListener(v -> {

            Intent intent = new Intent(DetailActivity.this, MainActivity.class);

            startActivity(intent);
        });

        editTrainee.setOnClickListener(v -> {

            Intent sendIntent = new Intent(this, EditActivity.class);

            Bundle sendBundle = new Bundle();
            sendBundle.putParcelable("traineeEdit", trainee);

            sendIntent.putExtras(sendBundle);

            startActivity(sendIntent);

            finish();
        });

        deleteTrainee.setOnClickListener(v -> {
            dialogDeleteTask(trainee.getName(), trainee.getId());
        });
    }

    public void dialogDeleteTask(String name, long id){
        AlertDialog.Builder deleteDialog = new AlertDialog.Builder(this);
        deleteDialog.setMessage("Are you sure you want to delete " + name + " task?");
        deleteDialog.setPositiveButton("Yes", (dialog, which) -> {
            Call<Trainee> call = traineeService.deleteTrainee(id);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    Toast.makeText(DetailActivity.this, "You deleted " + name, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(DetailActivity.this, MainActivity.class);

                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(DetailActivity.this, "Fail deleted " + name, Toast.LENGTH_SHORT).show();
                }
            });
        });

        deleteDialog.setNegativeButton("No", (dialog, which) -> {

        });

        deleteDialog.show();
    }
}
