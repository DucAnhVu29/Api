package com.example.api.entity;

import java.util.ArrayList;

public class TraineeList {

    private ArrayList<Trainee> traineeArrayList;

    public TraineeList(ArrayList<Trainee> traineeArrayList) {
        this.traineeArrayList = traineeArrayList;
    }

    public ArrayList<Trainee> getTraineeArrayList() {
        return traineeArrayList;
    }

    public void setTraineeArrayList(ArrayList<Trainee> traineeArrayList) {
        this.traineeArrayList = traineeArrayList;
    }
}
