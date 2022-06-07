package io.github.dorma.webrtc.domain.file.report;

import io.github.dorma.webrtc.domain.team.StudyRoom;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
public class ReportSaveReqDTO {
    private StudyRoom studyroom;
    private String reportFilename;
    private String reportFileroute;
    private String reportSavefilename;
    private String reportDownload;

    @Builder
    public ReportSaveReqDTO(StudyRoom studyroom, String reportFilename, String reportFileroute, String reportSavefilename, String reportDownload) {
        this.studyroom = studyroom;
        this.reportFilename = reportFilename;
        this.reportFileroute = reportFileroute;
        this.reportSavefilename = reportSavefilename;
        this.reportDownload = reportDownload;
    }

    public Report toEntity(){
        return Report.builder()
                .studyroom(studyroom)
                .reportFilename(reportFilename)
                .reportFileroute(reportFileroute)
                .reportSavefilename(reportSavefilename)
                .reportDownload(reportDownload)
                .build();
    }
}
