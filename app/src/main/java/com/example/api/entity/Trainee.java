package com.example.api.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Trainee implements Parcelable {

    private long id;
    private String name;
    private String email;
    private String phone;
    private String gender;

    public Trainee(String name, String email, String phone, String gender) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }

    public Trainee(long id, String name, String email, String phone, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }

    protected Trainee(Parcel in) {
        id = in.readLong();
        name = in.readString();
        email = in.readString();
        phone = in.readString();
        gender = in.readString();
    }

    public static final Creator<Trainee> CREATOR = new Creator<Trainee>() {
        @Override
        public Trainee createFromParcel(Parcel in) {
            return new Trainee(in);
        }

        @Override
        public Trainee[] newArray(int size) {
            return new Trainee[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(gender);
    }
}
