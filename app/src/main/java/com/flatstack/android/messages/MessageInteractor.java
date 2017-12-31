package com.flatstack.android.messages;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.flatstack.android.messages.models.Dialog;
import com.flatstack.android.messages.models.Message;
import com.flatstack.android.utils.Contacts;
import com.flatstack.android.utils.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MessageInteractor {

    private static final String KEY_SMS_MESSAGES = "content://sms/";
    private static final String KEY_CONVERSATIONS_LIST = "content://sms/conversations";

    private static final String KEY_THREAD_ID = "thread_id";
    private static final String KEY_MESSAGE_BODY = "body";
    private static final String KEY_MESSAGE_PHONE = "address";
    private static final String KEY_MESSAGE_TYPE = "type";
    private static final String KEY_MESSAGE_DATE = "date";

    final Context context;
    final Contacts contacts;

    public MessageInteractor(Context context, Contacts mockContacts) {
        this.context = context;
        this.contacts = mockContacts;
    }

    public List<Dialog> getMessages() {
        List<Message> inbox = retrieveMessages();
        return Lists.map(inbox, message -> new Dialog(message.getDisplayName(), ":)", message.message, "lol"));
    }

    List<Message> retrieveMessages() {
        Cursor cursor = context.getContentResolver().query(Uri.parse(KEY_SMS_MESSAGES), null, null, null, null);

        List<Message> messages = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                messages.add(extractMessageFromCursor(cursor));
            } while (cursor.moveToNext());
        }
        Map<Long, List<Message>> conversations = Lists.groupBy(messages, message -> message.threadId);
        return cutOnlyFirstMessageFromEachDialog(conversations);
    }

    @NonNull private Message extractMessageFromCursor(Cursor cursor) {
        long threadId = cursor.getLong(cursor.getColumnIndexOrThrow(KEY_THREAD_ID));
        String messageText = cursor.getString(cursor.getColumnIndexOrThrow(KEY_MESSAGE_BODY));
        long timestamp = cursor.getLong(cursor.getColumnIndexOrThrow(KEY_MESSAGE_DATE));
        String phone = cursor.getString(cursor.getColumnIndexOrThrow(KEY_MESSAGE_PHONE));
        String contactName = contacts.getContactName(phone);
        Message.Type type = cursor.getString(cursor.getColumnIndexOrThrow(KEY_MESSAGE_TYPE))
                .contains("1") ? Message.Type.INBOX : Message.Type.SENT;
        return new Message(threadId, messageText, contactName, phone, type, timestamp);
    }

    private List<Message> cutOnlyFirstMessageFromEachDialog(Map<Long, List<Message>> conversations) {
        List<Message> firstMessageFromEachConversation = new ArrayList<>();
        for (Long threadId : conversations.keySet()) {
            List<Message> messages = conversations.get(threadId);
            firstMessageFromEachConversation.add(messages.get(0));
        }
        Collections.sort(firstMessageFromEachConversation, Message.BY_TIME());
        Collections.reverse(firstMessageFromEachConversation);
        return firstMessageFromEachConversation;
    }
}
