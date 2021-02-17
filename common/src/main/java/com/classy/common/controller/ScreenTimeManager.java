package com.classy.common.controller;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Room;

import com.classy.common.database.ApplicationDB;
import com.classy.common.entity.ScreenTime;

import java.util.List;

public class ScreenTimeManager {
    private static ScreenTimeManager instance;
    private static ApplicationDB appDatabase;

    private ScreenTimeManager(Context context) {
        appDatabase = Room.databaseBuilder(context.getApplicationContext(), ApplicationDB.class, "timeDB.db").build();
    }

    public static ScreenTimeManager getInstance() {
        return instance;
    }

    public static ScreenTimeManager initHelper(Context context) {
        if (instance == null) {
            instance = new ScreenTimeManager(context);
        }
        return instance;
    }

    public interface CallBack_TotalTime {
        void dataReady(long time);
    }

    public void getTotalScreenTime(String appName, CallBack_TotalTime callBack){
        new Thread(() -> {
            long totalScreenTime = 0;
            List<ScreenTime> screenTimeList = appDatabase.screenTimeDao().getScreenTime(appName);
            for (ScreenTime time:screenTimeList) {
                totalScreenTime += time.getDuration();
            }
            if (callBack!= null)
                callBack.dataReady(totalScreenTime);
        }).start();
    }

    public void saveScreenTime(long duration, String appName) {
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                appDatabase.screenTimeDao().SaveAppTime(new ScreenTime(duration,appName));
            }
        }).start();
    }
}
