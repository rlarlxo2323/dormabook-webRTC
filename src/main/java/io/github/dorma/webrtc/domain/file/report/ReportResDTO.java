package io.github.dorma.webrtc.domain.file.report;

import io.github.dorma.webrtc.domain.team.StudyRoom;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ReportResDTO {
    private Long reportNo;
    private StudyRoom studyroom;
    private String reportFilename;
    private String reportFileroute;
    private String reportSavefilename;
    private Timestamp reportSaveat;
    private String reportDownload;

    public ReportResDTO(Report entity){
        this.reportNo = entity.getReportNo();
        this.studyroom = entity.getStudyroom();
        this.reportFilename = entity.getReportFilename();
        this.reportFileroute = entity.getReportFileroute();
        this.reportSavefilename = entity.getReportSavefilename();
        this.reportSaveat = entity.getReportSaveat();
        this.reportDownload = entity.getReportDownload();
    }
}
