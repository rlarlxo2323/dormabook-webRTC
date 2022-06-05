package io.github.dorma.webrtc.payload;

import lombok.Getter;

@Getter
public class UploadFileRes {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadFileRes(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
}
