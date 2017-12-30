package com.flatstack.android.utils.mvvm;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.flatstack.android.messages.MessagesVM;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private MessagesVM messagesVM;

    @Inject
    public ViewModelFactory(MessagesVM messagesVM) {
        this.messagesVM = messagesVM;
    }

    @NonNull @Override public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MessagesVM.class)) {
            return (T) messagesVM;
        }
        throw new IllegalArgumentException("Unknown class");
    }
}
