package com.flatstack.android.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class Contacts {

    public Context context;

    private final Map<String, String> cache = new HashMap<>();

    public Contacts(Context context) {
        this.context = context;
    }

    public String getContactName(@NonNull final String phoneNumber) {
        if (!cache.containsKey(phoneNumber)) {
            Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
            String[] projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME};
            String contactName = null;
            Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    contactName = cursor.getString(0);
                }
                cursor.close();
            }
            cache.put(phoneNumber, contactName);
        }
        return cache.get(phoneNumber);
    }
}
