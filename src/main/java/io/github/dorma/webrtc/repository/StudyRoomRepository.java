package io.github.dorma.webrtc.repository;

import io.github.dorma.webrtc.domain.team.StudyRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRoomRepository extends JpaRepository<StudyRoom, Long> {
}
