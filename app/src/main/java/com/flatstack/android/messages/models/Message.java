package com.flatstack.android.messages.models;

public class Message {
    public String message;
    public String name;
    public String datetime;

    public Message(String message, String name, String datetime) {
        this.message = message;
        this.name = name;
        this.datetime = datetime;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (message != null ? !message.equals(message1.message) : message1.message != null)
            return false;
        if (name != null ? !name.equals(message1.name) : message1.name != null) return false;
        return datetime != null ? datetime.equals(message1.datetime) : message1.datetime == null;
    }

    @Override public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (datetime != null ? datetime.hashCode() : 0);
        return result;
    }
}
