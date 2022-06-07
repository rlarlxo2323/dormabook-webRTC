package io.github.dorma.webrtc.controller.chat;

import io.github.dorma.webrtc.domain.chat.ChatMapping;
import io.github.dorma.webrtc.domain.chat.ChatReq;
import io.github.dorma.webrtc.repository.ChatRoomRepository;
import io.github.dorma.webrtc.service.ChatProvider;
import io.github.dorma.webrtc.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private final ChatService chatService;
    @Autowired
    private final ChatProvider chatProvider;

    public ChatController(ChatService chatService, ChatProvider chatProvider) {
        this.chatService = chatService;
        this.chatProvider = chatProvider;
    }

    @ResponseBody
    @PostMapping("/team-chat")
    public void createChatLog(@RequestBody ChatReq chatReq){
        chatService.createChatLog(chatReq);
    }

    @ResponseBody
    @GetMapping("/team-chat/{roomId}")
    public List<ChatMapping> getChatLog(@PathVariable Long roomId){
        return chatProvider.getChatLog(roomId);
    }

}
