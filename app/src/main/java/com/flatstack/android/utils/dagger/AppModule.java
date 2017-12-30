package com.flatstack.android.utils.dagger;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;

import com.flatstack.android.messages.MessageInteractor;
import com.flatstack.android.messages.MessagesVM;
import com.flatstack.android.utils.Contacts;
import com.flatstack.android.utils.mvvm.ViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Context appContext;

    public AppModule(@NonNull Context appContext) {
        this.appContext = appContext;
    }

    @Provides @Singleton MessageInteractor messageInteractor(Contacts contacts) {
        return new MessageInteractor(appContext, contacts);
    }

    @Provides @Singleton
    ViewModelProvider.Factory viewModelFactory(MessagesVM messagesVM) {
        return new ViewModelFactory(messagesVM);
    }

    @Provides @Singleton Contacts contacts() {
        return new Contacts(appContext);
    }
}
