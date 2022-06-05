package io.github.dorma.webrtc.payload;

import lombok.Getter;

@Getter
public class UploadTsFileRes {
    private String fileName;
    private String saveName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadTsFileRes(String fileName, String saveName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.saveName = saveName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
}
