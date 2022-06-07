package io.github.dorma.webrtc.domain.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChatReq {
    private String chatMemberid;
    private String chatContent;
    private Long studyroomNo;
}
