package io.github.dorma.webrtc.repository;

import io.github.dorma.webrtc.domain.chat.ChatRoom;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository {
    private Map<String, ChatRoom> chatRoomMap;

    @PostConstruct
    private void init(){
        chatRoomMap = new LinkedHashMap<>();
    }

    public ChatRoom findRoomById(String id){
        return chatRoomMap.get(id);
    }

    public void createChatRoom(String roomId){
        ChatRoom chatRoom = ChatRoom.create(roomId);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
    }
}
