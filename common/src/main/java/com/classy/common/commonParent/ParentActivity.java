package com.classy.common.commonParent;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.classy.common.R;
import com.classy.common.controller.GarageController;
import com.classy.common.controller.ScreenTimeManager;
import com.classy.common.entity.Garage;

public abstract class ParentActivity extends AppCompatActivity {
    private TextView parent_LBL_title;
    private TextView parent_LBL_isOpen;
    private TextView parent_LBL_address;
    private TextView parent_LBL_cars;
    private TextView parent_LBL_time;

    private String applicationName;
    private long startTimeStamp = 0;
    private final int MINUTE = 60;
    private ScreenTimeManager screenTimeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
    }

    protected void initializeVariables(String applicationName) {
        this.applicationName = applicationName;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
        parent_LBL_title = findViewById(R.id.parent_LBL_title);
        parent_LBL_isOpen = findViewById(R.id.parent_LBL_isOpen);
        parent_LBL_address = findViewById(R.id.parent_LBL_address);
        parent_LBL_cars = findViewById(R.id.parent_LBL_cars);
        parent_LBL_time = findViewById(R.id.parent_LBL_time);
        screenTimeManager = ScreenTimeManager.initHelper(this.getApplicationContext());
    }

    protected void listeners() {

        downloadInfoFromData(garage -> {
            parent_LBL_title.setText(String.format("Garage Name: %s", garage.getName()));
            String status = garage.isOpen() ? "Open" : "Close";
            parent_LBL_isOpen.setText(String.format("Status: %s", status));
            parent_LBL_address.setText(String.format("Address: %s", garage.getAddress()));
            for (String car : garage.getCars()) {
                String carTxt = parent_LBL_cars.getText().toString();
                carTxt += "\n" + car;
                parent_LBL_cars.setText(carTxt);
            }
        });
    }

    public interface CallBack_Data {
        void dataReady(Garage garage);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimeStamp = System.currentTimeMillis();
        screenTime();
    }

    @Override
    protected void onPause() {
        super.onPause();
        long duration = (System.currentTimeMillis() - startTimeStamp) / 1000;
        screenTimeManager.saveScreenTime(duration, applicationName);
    }

    private void downloadInfoFromData(CallBack_Data callBack) {
        new GarageController().fetchAllGarage(garage -> {
            if (callBack != null)
                callBack.dataReady(garage);
        });
    }

    private void screenTime() {
        ScreenTimeManager.getInstance().getTotalScreenTime(applicationName, time -> {
            new Handler(Looper.getMainLooper()).post(() -> {
                String minute = "",second = "";
                if (time >= MINUTE) {
                    long min = time / MINUTE;
                    if (min >= 10)
                        minute = "" + min;
                    else
                        minute = "0" + min;

                    long sec = time % MINUTE;
                    if (sec >= 10)
                        second = "" + sec;
                    else
                        second = "0" + sec;

                    parent_LBL_time.setText(String.format("Total Screen Time: \n%s : %s", minute, second));
                } else
                    parent_LBL_time.setText(String.format("Total Screen Time: %d Seconds", time));
            });
        });
    }
}
