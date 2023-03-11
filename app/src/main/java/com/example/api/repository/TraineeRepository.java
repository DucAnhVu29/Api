package com.example.api.repository;

import com.example.api.client.APIClient;
import com.example.api.services.TraineeService;

public class TraineeRepository {
    public static TraineeService getTraineeService(){
        return APIClient.getClient().create(TraineeService.class);
    }
}
