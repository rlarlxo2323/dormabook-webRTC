package io.github.dorma.webrtc.domain.file.menteeTranscript;

import io.github.dorma.webrtc.domain.team.StudyRoom;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenteeTranscriptResDTO {
    private Long menteetsNo;
    private StudyRoom studyroom;
    private String menteetsFilename;
    private String menteetsFileroute;
    private String menteetsSavefilename;

    public MenteeTranscriptResDTO(MenteeTranscript entity) {
        this.menteetsNo = entity.getMenteetsNo();
        this.studyroom = entity.getStudyroom();
        this.menteetsFilename = entity.getMenteetsFilename();
        this.menteetsFileroute = entity.getMenteetsFileroute();
        this.menteetsSavefilename = entity.getMenteetsSavefilename();
    }
}
