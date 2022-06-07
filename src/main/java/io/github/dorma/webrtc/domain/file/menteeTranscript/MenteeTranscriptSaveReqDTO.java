package io.github.dorma.webrtc.domain.file.menteeTranscript;

import io.github.dorma.webrtc.domain.team.StudyRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenteeTranscriptSaveReqDTO {
    private StudyRoom studyroom;
    private String menteetsFilename;
    private String menteetsFileroute;
    private String menteetsSavefilename;

    @Builder
    public MenteeTranscriptSaveReqDTO(StudyRoom studyroom, String menteetsFilename, String menteetsFileroute, String menteetsSavefilename) {
        this.studyroom = studyroom;
        this.menteetsFilename = menteetsFilename;
        this.menteetsFileroute = menteetsFileroute;
        this.menteetsSavefilename = menteetsSavefilename;
    }

    public MenteeTranscript toEntity(){
        return MenteeTranscript.builder()
                .studyroom(studyroom)
                .menteetsFilename(menteetsFilename)
                .menteetsFileroute(menteetsFileroute)
                .menteetsSavefilename(menteetsSavefilename)
                .build();
    }
}
