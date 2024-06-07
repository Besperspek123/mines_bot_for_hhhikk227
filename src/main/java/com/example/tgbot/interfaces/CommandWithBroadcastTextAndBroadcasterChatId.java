package com.example.tgbot.interfaces;

import java.io.InputStream;

public interface CommandWithBroadcastTextAndBroadcasterChatId {
    void execute(String broadcastText, long broadcasterChatID);
    void execute(long broadcasterChatID, String broadcastText,  byte[] photoBytes);
}
