package io.github.dorma.webrtc.domain.file.report;

import io.github.dorma.webrtc.domain.team.StudyRoom;
import lombok.*;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class ReportReqDTO {
    private Long reportNo;
    private StudyRoom studyroom;
    private String reportFilename;
    private String reportFileroute;
    private String reportSavefilename;
    private Timestamp reportSaveat;
    private String reportDownload;

    @Builder
    public ReportReqDTO(Long reportNo, StudyRoom studyroom, String reportFilename, String reportFileroute, String reportSavefilename, Timestamp reportSaveat, String reportDownload) {
        this.reportNo = reportNo;
        this.studyroom = studyroom;
        this.reportFilename = reportFilename;
        this.reportFileroute = reportFileroute;
        this.reportSavefilename = reportSavefilename;
        this.reportSaveat = reportSaveat;
        this.reportDownload = reportDownload;
    }

    public Report toEntity(){
        return Report.builder()
                .reportNo(reportNo)
                .studyroom(studyroom)
                .reportFilename(reportFilename)
                .reportFileroute(reportFileroute)
                .reportSavefilename(reportSavefilename)
                .reportSaveat(reportSaveat)
                .reportDownload(reportDownload)
                .build();
    }
}
