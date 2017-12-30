package com.flatstack.android.messages.models;

public class Dialog {

    public String name;
    public String avatar;
    public String lastMessage;
    public String lastMessageTime;

    public Dialog(String name, String avatar, String lastMessage, String lastMessageTime) {
        this.name = name;
        this.avatar = avatar;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dialog dialog = (Dialog) o;

        if (name != null ? !name.equals(dialog.name) : dialog.name != null) return false;
        if (avatar != null ? !avatar.equals(dialog.avatar) : dialog.avatar != null) return false;
        if (lastMessage != null ? !lastMessage.equals(dialog.lastMessage) : dialog.lastMessage != null)
            return false;
        return lastMessageTime != null ? lastMessageTime.equals(dialog.lastMessageTime) : dialog.lastMessageTime == null;
    }

    @Override public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (lastMessage != null ? lastMessage.hashCode() : 0);
        result = 31 * result + (lastMessageTime != null ? lastMessageTime.hashCode() : 0);
        return result;
    }
}
