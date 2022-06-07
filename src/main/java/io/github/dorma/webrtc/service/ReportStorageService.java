package io.github.dorma.webrtc.service;

import io.github.dorma.webrtc.domain.file.report.Report;
import io.github.dorma.webrtc.domain.file.report.ReportResDTO;
import io.github.dorma.webrtc.domain.file.report.ReportSaveReqDTO;
import io.github.dorma.webrtc.exception.FileStorageException;
import io.github.dorma.webrtc.exception.MyFileNotFoundException;
import io.github.dorma.webrtc.property.FileStorageProperties;
import io.github.dorma.webrtc.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReportStorageService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    ReportRepository reportRepository;
    private final Path fileStorageLocation;

    @Autowired
    public ReportStorageService(FileStorageProperties fileStorageProperties){
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Transactional
    public ReportResDTO UploadReport(ReportSaveReqDTO reqDTO){
        return new ReportResDTO(reportRepository.save(reqDTO.toEntity()));
    }

    public String storeReport(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        UUID uuid = UUID.randomUUID();

        String saveName = uuid + "_" + fileName;
        try {
            if (fileName.contains("..")){
                throw new FileStorageException("Sorry! Filename contain invalid path sequence "+ fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(saveName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return saveName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadReportAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()){
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found" + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found "+ fileName, ex);
        }
    }
}
