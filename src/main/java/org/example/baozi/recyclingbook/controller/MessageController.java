package org.example.baozi.recyclingbook.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.example.baozi.recyclingbook.ResponseMessage.ResponseMessage;
import org.example.baozi.recyclingbook.model.DTO.MessageDTO;
import org.example.baozi.recyclingbook.service.MessageService.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/send")
    public ResponseMessage<Message> createMessage(@RequestBody @Valid MessageDTO messageDTO) {
         boolean result = messageService.sendMessage(messageDTO);
         if (result) {
             return ResponseMessage.success(200,"发送成功");
         }
         else
             return ResponseMessage.error("发送失败");
    }
}
