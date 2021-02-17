package com.classy.common.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.classy.common.entity.ScreenTime;

@Database(entities = {ScreenTime.class},version = 1)
public abstract class ApplicationDB extends RoomDatabase {
    public abstract AppScreenTimeDao screenTimeDao();
}
