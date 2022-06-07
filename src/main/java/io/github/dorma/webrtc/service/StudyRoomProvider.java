package io.github.dorma.webrtc.service;

import io.github.dorma.webrtc.domain.team.StudyRoom;
import io.github.dorma.webrtc.repository.StudyRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudyRoomProvider {
    @Autowired
    private final StudyRoomRepository studyRoomRepository;

    public StudyRoomProvider(StudyRoomRepository studyRoomRepository) {
        this.studyRoomRepository = studyRoomRepository;
    }

    public Optional<StudyRoom> getStudyRoom(Long no) {
        return studyRoomRepository.findById(no);
    }
}
