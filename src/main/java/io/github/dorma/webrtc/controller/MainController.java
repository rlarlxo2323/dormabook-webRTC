package io.github.dorma.webrtc.controller;

import io.github.dorma.webrtc.repository.ChatRoomRepository;
import io.github.dorma.webrtc.security.JwtTokenProvider;
import io.github.dorma.webrtc.domain.file.report.Report;
import io.github.dorma.webrtc.service.MainService;
import io.github.dorma.webrtc.service.ReportProvider;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@ControllerAdvice
public class MainController {
    private final MainService mainService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ChatRoomRepository chatRoomRepository;


    @Autowired
    private ReportProvider reportProvider;
    @Autowired
    public MainController(final MainService mainService, JwtTokenProvider jwtTokenProvider, ChatRoomRepository chatRoomRepository) {
        this.mainService = mainService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.chatRoomRepository = chatRoomRepository;
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

    @PostMapping("/study-room/{roomId}/{roomAddr}")
    public String showStudyRoom(@PathVariable String roomId, @PathVariable String roomAddr, Model model, @RequestBody String jwt){
        val jwtToken = jwt.substring(11);
        val tokenRes = jwt.substring(4);

        chatRoomRepository.createChatRoom(roomAddr);
        model.addAttribute("roomNo",roomId);
        model.addAttribute("roomAddr",roomAddr);
        model.addAttribute("sub",jwtTokenProvider.getAuthentication(jwtToken).getName());
        model.addAttribute("accessToken", tokenRes);
        return "chat_room";
    }

    @GetMapping("/study-room/{roomId}/mentee-ts")
    public ModelAndView displayMenteeTranscript(@PathVariable Long roomId, Model model){
        model.addAttribute("roomNo", roomId);
        return new ModelAndView("mentee_transcript");
    }

    @GetMapping("/study-room/{roomId}/report")
    public ModelAndView displayReport(@PathVariable Long roomId, Model model){
        model.addAttribute("roomNo", roomId);
        List<Report> reportList = new ArrayList<>();
        reportList.addAll(reportProvider.findReportByRoomNo(roomId));
        model.addAttribute("reportList", reportList);
        return new ModelAndView("report");
    }
}
