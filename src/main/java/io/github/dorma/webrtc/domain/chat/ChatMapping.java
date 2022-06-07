package io.github.dorma.webrtc.domain.chat;

import java.sql.Timestamp;

public interface ChatMapping {
    String getChatMemberid();
    Timestamp getChatCreatedat();
    String getChatContent();
}
