package com.example.administrator.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Administrator on 2020/1/17.
 */

@Entity
public class Customer {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "first_name")
    public String firstName;
    @ColumnInfo(name = "last_name")
    public String lastName;
    @ColumnInfo(name = "weight")
    public double weight;

    public Customer(String firstName, String lastName, double weight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
    }

    public int getId() {
        return uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getweight() {
        return weight;
    }

    public void setweight(double weight) {
        this.weight = weight;
    }
}