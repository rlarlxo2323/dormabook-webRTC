package io.github.dorma.webrtc.repository;

import io.github.dorma.webrtc.domain.file.MenteeTranscript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenteeTranscriptRepository extends JpaRepository<MenteeTranscript, Long> {
}
