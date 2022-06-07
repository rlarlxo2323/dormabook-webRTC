package io.github.dorma.webrtc.controller;

import io.github.dorma.webrtc.repository.ChatRoomRepository;
import io.github.dorma.webrtc.security.JwtTokenProvider;
import io.github.dorma.webrtc.service.MainService;
import lombok.val;
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

import javax.servlet.http.HttpServletRequest;

@Controller
@ControllerAdvice
public class MainController {
    private final MainService mainService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ChatRoomRepository chatRoomRepository;

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

    @GetMapping("/study-room/{roomId}/{roomAddr}")
    public String showStudyRoom(@PathVariable String roomId, @PathVariable String roomAddr, HttpServletRequest request, Model model){
        String jwt = jwtTokenProvider.resolveToken(request);
        val jwtToken = jwt.substring(7);
        chatRoomRepository.createChatRoom(roomAddr);
        model.addAttribute("roomNo",roomId);
        model.addAttribute("roomAddr",roomAddr);
        model.addAttribute("sub",jwtTokenProvider.getAuthentication(jwtToken).getName());
        model.addAttribute("accessToken", jwt);
        return "chat_room";
    }
}
