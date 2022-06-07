package io.github.dorma.webrtc.payload;

import lombok.Getter;

@Getter
public class UploadReportFileRes {
    private String fileName;
    private String saveRoute;
    private String saveName;
    private Long roomNo;
    private String fileDownloadUri;

    public UploadReportFileRes(String fileName, String saveRoute, String saveName, Long roomNo, String fileDownloadUri) {
        this.fileName = fileName;
        this.saveRoute = saveRoute;
        this.saveName = saveName;
        this.roomNo = roomNo;
        this.fileDownloadUri = fileDownloadUri;
    }
}
