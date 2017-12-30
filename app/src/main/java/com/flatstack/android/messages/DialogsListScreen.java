package com.flatstack.android.messages;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.flatstack.android.R;
import com.flatstack.android.utils.mvvm.Injector;
import com.flatstack.android.utils.mvvm.ViewModelFactory;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class DialogsListScreen extends AppCompatActivity {

    private MessagesVM messagesVM;
    @Inject ViewModelFactory viewModelFactory;

    @Bind(R.id.list) RecyclerView uiList;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_dialogs);
        Injector.inject(this);
        ButterKnife.bind(this);
        DialogsListScreenPermissionsDispatcher.createVMWithPermissionCheck(this);
    }

    @NeedsPermission({Manifest.permission.READ_SMS, Manifest.permission.READ_CONTACTS})
    void createVM() {
        messagesVM = ViewModelProviders.of(this, viewModelFactory).get(MessagesVM.class);
        messagesVM.messages.observe(this, dialogs -> uiList.setAdapter(new DialogAdapter(dialogs)));
        messagesVM.attach();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        DialogsListScreenPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
