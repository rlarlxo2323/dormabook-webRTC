package io.github.dorma.webrtc.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import io.github.dorma.webrtc.domain.file.report.Report;
import io.github.dorma.webrtc.service.MainService;
import io.github.dorma.webrtc.service.ReportProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@ControllerAdvice
public class MainController {
    private final MainService mainService;

    @Autowired
    private ReportProvider reportProvider;
    @Autowired
    public MainController(final MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping({"/video-chat"})
    public ModelAndView displayMainPage(final Long id, final String uuid) {
        return this.mainService.displayMainPage(id, uuid);
    }

    @PostMapping(value = "/room", params = "action=create")
    public ModelAndView processRoomSelection(@ModelAttribute("id") final String sid, @ModelAttribute("uuid") final String uuid, final BindingResult binding) {
        return this.mainService.processRoomSelection(sid, uuid, binding);
    }

    @GetMapping("/room/{sid}/user/{uuid}")
    public ModelAndView displaySelectedRoom(@PathVariable("sid") final String sid, @PathVariable("uuid") final String uuid) {
        return this.mainService.displaySelectedRoom(sid, uuid);
    }

    @GetMapping("/room/{sid}/user/{uuid}/exit")
    public ModelAndView processRoomExit(@PathVariable("sid") final String sid, @PathVariable("uuid") final String uuid) {
        return this.mainService.processRoomExit(sid, uuid);
    }

    @GetMapping("/room/random")
    public ModelAndView requestRandomRoomNumber(@ModelAttribute("uuid") final String uuid) {
        return mainService.requestRandomRoomNumber(uuid);
    }

    @GetMapping("/offer")
    public ModelAndView displaySampleSdpOffer() {
        return new ModelAndView("sdp_offer");
    }

    @GetMapping("/stream")
    public ModelAndView displaySampleStreaming() {
        return new ModelAndView("streaming");
    }

    @GetMapping("/study-room/{roomId}/mentee-ts")
    public ModelAndView displayMenteeTranscript(@PathVariable Long roomId, Model model){
        model.addAttribute("roomNo", roomId);
        return new ModelAndView("mentee_transcript");}

    @GetMapping("/study-room/{roomId}/report")
    public ModelAndView displayReport(@PathVariable Long roomId, Model model){
        model.addAttribute("roomNo", roomId);
        List<Report> reportList = new ArrayList<>();
        reportList.addAll(reportProvider.findReportByRoomNo(roomId));
        model.addAttribute("reportList", reportList);
        return new ModelAndView("report");
    }
}
