package com.cotede.e2eechatapp.messages;

import com.cotede.e2eechatapp.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private MessageType messageType;
    private Object content;
    private User sender;
    private User receiver;
    private LocalDateTime timestamp;
}
