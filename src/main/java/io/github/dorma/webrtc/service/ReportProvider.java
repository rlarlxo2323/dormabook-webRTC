package io.github.dorma.webrtc.service;

import io.github.dorma.webrtc.domain.file.report.Report;
import io.github.dorma.webrtc.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportProvider {

    @Autowired
    private final ReportRepository reportRepository;

    public ReportProvider(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Report> findReportByRoomNo(Long roomNo){
        return reportRepository.findByStudyroom_StudyroomNo(roomNo);
    }
}
