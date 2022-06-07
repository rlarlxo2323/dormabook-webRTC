package io.github.dorma.webrtc.domain.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dorma.webrtc.repository.ChatRoomRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ChatRoom {
    private String roomId;
    private Set<WebSocketSession> sessions = new HashSet<>();

    public static ChatRoom create(String roomId) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = roomId;
        return chatRoom;
    }

    public void handleMessage(WebSocketSession session, ChatMessage chatMessage,
                              ObjectMapper objectMapper) throws IOException {
        if(chatMessage.getType() == MessageType.ENTER){
            sessions.add(session);
            chatMessage.setChatContent(chatMessage.getChatMemberid() + "님이 접속하셨습니다.");
        }
        else if(chatMessage.getType() == MessageType.LEAVE){
            sessions.remove(session);
            chatMessage.setChatContent(chatMessage.getChatMemberid() + "님임 퇴장하셨습니다.");
        }
        else{
            chatMessage.setChatMemberid(chatMessage.getChatMemberid());
            chatMessage.setChatContent(chatMessage.getChatContent());
        }
        chatMessage.setChatCreatedat(LocalDateTime.now());
        send(chatMessage,objectMapper);
    }

    private void send(ChatMessage chatMessage, ObjectMapper objectMapper) throws IOException {
        TextMessage textMessage = new TextMessage(objectMapper.
                writeValueAsString(chatMessage));
        for(WebSocketSession sess : sessions){
            sess.sendMessage(textMessage);
        }
    }
}
