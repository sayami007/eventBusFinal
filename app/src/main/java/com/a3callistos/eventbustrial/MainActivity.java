package com.a3callistos.eventbustrial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.a3callistos.eventbustrial.model.UserEntity;
import com.a3callistos.eventbustrial.rest.ApiCall;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    ApiCall apiCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiCall.getInstance().getUsers();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UserEntity eventCall) {
        Log.v("VALUES", eventCall.getBio());
        Toast.makeText(this, eventCall.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
