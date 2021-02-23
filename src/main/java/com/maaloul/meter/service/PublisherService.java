package com.maaloul.meter.service;

import com.maaloul.meter.message.ResponseMessage;
import com.maaloul.meter.model.Meter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.jms.Queue;

@Service
public class PublisherService {

    @Autowired
    private Queue queue;

    @Autowired
    private JmsTemplate jmsTemplate;

    public ResponseEntity<ResponseMessage> publish(@RequestBody Meter meter) {

        ObjectMapper mapper = new ObjectMapper();
        String meterAsJson;
        try {
            meterAsJson = mapper.writeValueAsString(meter);
            jmsTemplate.convertAndSend(queue, meterAsJson);
            return new ResponseEntity(meterAsJson, HttpStatus.OK);

        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResponseEntity("Failed to publish the counter", HttpStatus.EXPECTATION_FAILED);
        }

    }
    
}
