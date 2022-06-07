package io.github.dorma.webrtc.service;

import io.github.dorma.webrtc.domain.chat.Chat;
import io.github.dorma.webrtc.domain.chat.ChatReq;
import io.github.dorma.webrtc.domain.team.StudyRoom;
import io.github.dorma.webrtc.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ChatService {
    @Autowired
    private final ChatRepository chatRepository;
    public final StudyRoomProvider studyRoomProvider;

    public ChatService(ChatRepository chatRepository, StudyRoomProvider studyRoomProvider) {
        this.chatRepository = chatRepository;
        this.studyRoomProvider = studyRoomProvider;
    }

    public void createChatLog(ChatReq chatReq) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Optional<StudyRoom> studyRoom = studyRoomProvider.getStudyRoom(chatReq.getStudyroomNo());
        StudyRoom room = new StudyRoom(studyRoom.get().getStudyroomNo(), studyRoom.get().getTeam(),
                studyRoom.get().getStudyroomdomainaddr(), studyRoom.get().getStudyroomMenteegrade(),
                studyRoom.get().getStudyroomSubject());
        Chat chat = new Chat(null, room, chatReq.getChatMemberid(), Timestamp.valueOf(localDateTime), chatReq.getChatContent());
        chatRepository.save(chat);
    }
}
