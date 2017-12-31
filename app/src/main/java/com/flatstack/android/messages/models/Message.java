package com.flatstack.android.messages.models;

import java.util.Comparator;

public class Message {

    public final long threadId;
    public String message;
    public String name;
    public long datetime;
    public String phoneNumber;
    public Type type;

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (threadId != message1.threadId) return false;
        if (datetime != message1.datetime) return false;
        if (message != null ? !message.equals(message1.message) : message1.message != null)
            return false;
        if (name != null ? !name.equals(message1.name) : message1.name != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(message1.phoneNumber) : message1.phoneNumber != null)
            return false;
        return type == message1.type;
    }

    @Override public int hashCode() {
        int result = (int) (threadId ^ (threadId >>> 32));
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (datetime ^ (datetime >>> 32));
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public Message(long threadId, String message, String name, String phoneNumber, Type type, long datetime) {
        this.threadId = threadId;

        this.message = message;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.datetime = datetime;
    }

    public String getDisplayName() {
        return name == null ? phoneNumber : name;
    }

    public enum Type {
        INBOX, SENT
    }

    public static Comparator<Message> BY_TIME() {
        return (message, message2) -> (int)(message.datetime - message2.datetime);
    }
}
