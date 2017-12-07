package com.servico.roomorm;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by João Victor Firmino on 03/12/2017.
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM contatos")
    List<UserOrm> getAll();

//    @Query("SELECT * FROM contatos WHERE nome LIKE :teste LIMIT 1")
//    User findByName(String teste);

    @Insert
    void insertAll(UserOrm... userIn);

    @Update
    void update(UserOrm userUp);

    @Delete
    void delete(UserOrm userDel);
}
