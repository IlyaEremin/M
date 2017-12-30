package com.flatstack.android;

import android.app.Application;

import com.flatstack.android.utils.dagger.AppModule;
import com.flatstack.android.utils.mvvm.AppComponent;
import com.flatstack.android.utils.mvvm.DaggerAppComponent;

public class App extends Application {

    private AppComponent appComponent;

    public AppComponent appComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

}