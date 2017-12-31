package com.flatstack.android.messages;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.flatstack.android.messages.models.Dialog;

import java.util.List;

import javax.inject.Inject;

public class MessagesVM extends ViewModel {

    private final MessageInteractor messageInteractor;

    public MutableLiveData<Boolean> showProgress = new MutableLiveData<>();
    public MutableLiveData<List<Dialog>> messages = new MutableLiveData<>();

    @Inject
    public MessagesVM(MessageInteractor messageInteractor) {
        this.messageInteractor = messageInteractor;
    }

    MessageInteractor getMessageInteractor() {
        return messageInteractor;
    }

    void attach() {
        showProgress.postValue(true);
        messages.postValue(messageInteractor.getMessages());
    }
}
