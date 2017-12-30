package com.flatstack.android.utils.mvvm;

import com.flatstack.android.messages.DialogsListScreen;
import com.flatstack.android.utils.dagger.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class
})
public interface AppComponent {

    void inject(DialogsListScreen activity);
}
