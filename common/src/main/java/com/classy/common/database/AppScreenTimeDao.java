package com.classy.common.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.classy.common.entity.ScreenTime;

import java.util.List;

@Dao
public interface AppScreenTimeDao {
    @Insert
    void SaveAppTime(ScreenTime time);

    @Query("SELECT * FROM ScreenTime WHERE appName LIKE :appName")
    List<ScreenTime> getScreenTime(String appName);
}
