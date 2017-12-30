package com.flatstack.android.utils.mvvm;

import com.flatstack.android.App;
import com.flatstack.android.messages.DialogsListScreen;

public class Injector {
    public static void inject(DialogsListScreen activity) {
        ((App) activity.getApplicationContext()).appComponent().inject(activity);
    }
}
