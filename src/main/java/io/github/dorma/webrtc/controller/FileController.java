package io.github.dorma.webrtc.controller;

import io.github.dorma.webrtc.domain.file.menteeTranscript.MenteeTranscriptSaveReqDTO;
import io.github.dorma.webrtc.domain.file.report.ReportSaveReqDTO;
import io.github.dorma.webrtc.domain.team.StudyRoom;
import io.github.dorma.webrtc.payload.UploadReportFileRes;
import io.github.dorma.webrtc.payload.UploadTsFileRes;
import io.github.dorma.webrtc.property.FileStorageProperties;
import io.github.dorma.webrtc.service.FileStorageService;
import io.github.dorma.webrtc.service.ReportStorageService;
import io.github.dorma.webrtc.service.StudyRoomProvider;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);



    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private ReportStorageService reportStorageService;
    @Autowired
    private StudyRoomProvider studyRoomProvider;

    @Autowired
    private FileStorageProperties fileStorageProperties;

    @PostMapping("/ts/uploadFile/{roomNo}")
    public UploadTsFileRes uploadFile(@RequestParam("file")MultipartFile file, @PathVariable Long roomNo){
        String saveName = fileStorageService.storeFile(file);
        String fileName = file.getOriginalFilename();
        String fileRoute = fileStorageProperties.getUploadDir();

        Optional<StudyRoom> getNo = studyRoomProvider.getStudyRoom(roomNo);

        StudyRoom studyRoom = getNo.get();

        MenteeTranscriptSaveReqDTO menteeTranscriptSaveReqDTO = MenteeTranscriptSaveReqDTO.builder()
                .menteetsFilename(fileName)
                .menteetsFileroute(fileRoute)
                .menteetsSavefilename(saveName)
                .studyroom(studyRoom)
                .build();

        fileStorageService.UpLoadTs(menteeTranscriptSaveReqDTO);

        return new UploadTsFileRes(fileName, fileRoute, saveName, roomNo);
    }

    @PostMapping("/report/uploadFile/{roomNo}")
    public UploadReportFileRes uploadReportFile(@RequestParam("file")MultipartFile file, @PathVariable Long roomNo){
        String saveName = reportStorageService.storeReport(file);
        String fileName = file.getOriginalFilename();
        String fileRoute = fileStorageProperties.getUploadDir();

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/file/report/downloadFile/")
                .path(saveName)
                .toUriString();

        Optional<StudyRoom> getNo = studyRoomProvider.getStudyRoom(roomNo);

        StudyRoom studyRoom = getNo.get();

        ReportSaveReqDTO reportSaveReqDTO = ReportSaveReqDTO.builder()
                .studyroom(studyRoom)
                .reportFilename(fileName)
                .reportFileroute(fileRoute)
                .reportSavefilename(saveName)
                .reportDownload(fileDownloadUri)
                .build();

        reportStorageService.UploadReport(reportSaveReqDTO);

        return new UploadReportFileRes(fileName, fileRoute, saveName, roomNo, fileDownloadUri);
    }

    @GetMapping("/report/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = reportStorageService.loadReportAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
