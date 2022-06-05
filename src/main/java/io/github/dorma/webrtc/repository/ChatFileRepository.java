package io.github.dorma.webrtc.repository;

import io.github.dorma.webrtc.domain.chat.ChatFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatFileRepository extends JpaRepository<ChatFile, Long> {
}
