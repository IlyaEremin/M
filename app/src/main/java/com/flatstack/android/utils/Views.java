package com.flatstack.android.utils;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Views {
    public static View inflate(@LayoutRes int layoutId, ViewGroup vg) {
        return LayoutInflater.from(vg.getContext()).inflate(layoutId, vg, false);
    }
}
