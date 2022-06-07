package io.github.dorma.webrtc.service;

import io.github.dorma.webrtc.domain.chat.ChatMapping;
import io.github.dorma.webrtc.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatProvider {
    @Autowired
    private final ChatRepository chatRepository;

    public ChatProvider(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public List<ChatMapping> getChatLog(Long roomId) {
        List<ChatMapping> chatRes = chatRepository.findByStudyroom_StudyroomNo(roomId);
        return chatRes;
    }
}
