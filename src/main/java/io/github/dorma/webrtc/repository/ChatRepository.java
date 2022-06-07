package io.github.dorma.webrtc.repository;

import io.github.dorma.webrtc.domain.chat.Chat;
import io.github.dorma.webrtc.domain.chat.ChatMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<ChatMapping> findByStudyroom_StudyroomNo(Long roomId);
}
