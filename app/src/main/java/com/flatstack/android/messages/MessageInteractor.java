package com.flatstack.android.messages;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.flatstack.android.messages.models.Dialog;
import com.flatstack.android.messages.models.Message;
import com.flatstack.android.utils.Contacts;
import com.flatstack.android.utils.Lists;

import java.util.ArrayList;
import java.util.List;

public class MessageInteractor {

    private static final int INDEX_MESSAGE = 12;
    private static final int INDEX_TIME = 5;
    private static final int INDEX_PHONE = 13;

    final Context context;
    final Contacts contacts;

    public MessageInteractor(Context context, Contacts mockContacts) {
        this.context = context;
        this.contacts = mockContacts;
    }

    public List<Dialog> getMessages() {
        List<Message> inbox = getInbox();
        getSent();
        return Lists.map(inbox, message -> new Dialog(message.name, ":)", message.message, message.datetime));
    }

    public List<Message> getInbox() {
        Cursor cursor = context.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);

        List<Message> messages = new ArrayList<>();
        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                messages.add(new Message(cursor.getString(INDEX_MESSAGE), contacts.getContactName(cursor.getString(INDEX_PHONE)), cursor.getString(INDEX_TIME)));
            } while (cursor.moveToNext());
        }
        return messages;
    }

    public void getSent() {

    }
}
