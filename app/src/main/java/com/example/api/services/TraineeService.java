package com.example.api.services;

import com.example.api.entity.Trainee;
import com.example.api.entity.TraineeList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TraineeService {
    String TRAINEES = "trainee";

    @GET(TRAINEES)
    Call<Trainee[]> getAllTrainees();

    @GET(TRAINEES + "/{id}")
    Call<Trainee> getTrainee(@Path("id") Object id);

    @POST(TRAINEES)
    Call<Trainee> createTrainee(@Body Trainee trainee);

    @PUT(TRAINEES + "/{id}")
    Call<Trainee> updateTrainee(@Path("id") Object id, @Body Trainee trainee);

    @DELETE(TRAINEES + "/{id}")
    Call<Trainee> deleteTrainee(@Path("id") Object id);
}
