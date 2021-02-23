package com.maaloul.meter.controller;
import com.maaloul.meter.message.ResponseMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.jms.Queue;


@RestController
@RequestMapping("/api")
public class PublisherController {

    @Autowired
    private Queue queue;

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping("/message")
    public ResponseEntity<ResponseMessage> publish(@RequestBody String message){
        jmsTemplate.convertAndSend(queue, message);
        return new ResponseEntity(message, HttpStatus.OK);

        //return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

    }
    
}
