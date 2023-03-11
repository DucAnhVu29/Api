package com.example.api;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.api.entity.Trainee;
import com.example.api.repository.TraineeRepository;
import com.example.api.services.TraineeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {

    TraineeService traineeService;
    EditText editTextName, editTextEmail, editTextPhone, editTextGender;
    Button buttonSave;
    ImageView imageBack;

    private static final String REQUIRE = "Require";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editTextName = findViewById(R.id.edit_name);
        editTextEmail = findViewById(R.id.edit_email);
        editTextPhone = findViewById(R.id.edit_phone_number);
        editTextGender = findViewById(R.id.edit_gender);
        buttonSave = findViewById(R.id.save_trainee);
        imageBack = findViewById(R.id.back_image);

        traineeService = TraineeRepository.getTraineeService();

        buttonSave.setOnClickListener(v -> {
            if(!checkInput()){
                return;
            }
            saveTrainee();
        });

        imageBack.setOnClickListener(v -> {

            Intent intent = new Intent(AddActivity.this, MainActivity.class);

            startActivity(intent);
        });
    }

    private void saveTrainee(){

        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String phone = editTextPhone.getText().toString();
        String gender = editTextGender.getText().toString();

        Trainee trainee = new Trainee(name, email, phone, gender);

        try {

            Call<Trainee> call = traineeService.createTrainee(trainee);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if (response.body() != null){

                        editTextName.setText("");
                        editTextEmail.setText("");
                        editTextPhone.setText("");
                        editTextGender.setText("");

                        Toast.makeText(AddActivity.this, "Save successfully!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(AddActivity.this, MainActivity.class);

                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(AddActivity.this, "Save fail!", Toast.LENGTH_SHORT).show();
                }

            });
        } catch (Exception e){
            Log.d("ERROR", e.getMessage());
        }
    }

    private boolean checkInput() {
        if(TextUtils.isEmpty(editTextName.getText().toString())){
            editTextName.setError(REQUIRE);
            return false;
        }

        if(TextUtils.isEmpty(editTextEmail.getText().toString())){
            editTextEmail.setError(REQUIRE);
            return false;
        }

        if(TextUtils.isEmpty(editTextPhone.getText().toString())){
            editTextPhone.setError(REQUIRE);
            return false;
        }

        if(TextUtils.isEmpty(editTextGender.getText().toString())){
            editTextGender.setError(REQUIRE);
            return false;
        }

        return true;
    }
}
