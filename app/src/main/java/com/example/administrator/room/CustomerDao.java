package com.example.administrator.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Administrator on 2020/1/17.
 */

@Dao
public interface CustomerDao {
    @Query("SELECT * FROM customer")
    List<Customer> getAll();

    @Query("SELECT * FROM customer WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
    Customer findByFirstandLastName(String first, String last);

    @Query("SELECT * FROM customer WHERE uid = :customerId LIMIT 1")
    Customer findByID(int customerId);

    @Insert
    void insertAll(Customer... customers);

    @Insert
    long insert(Customer customer);

    @Delete
    void delete(Customer customer);

    @Update(onConflict = REPLACE)
    public void updateUsers(Customer... customers);

    @Query("DELETE FROM customer")
    void deleteAll();
}
