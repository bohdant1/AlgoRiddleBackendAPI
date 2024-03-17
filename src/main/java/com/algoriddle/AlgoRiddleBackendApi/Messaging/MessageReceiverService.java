package com.algoriddle.AlgoRiddleBackendApi.Messaging;

import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.Services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;



@SpringBootApplication
@EnableJms
public class MessageReceiverService  {
    private static final String QUEUE_NAME = "userregistrationqueue";

    private final JmsTemplate jmsTemplate;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public MessageReceiverService(JmsTemplate jmsTemplate, UserService userService, ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.userService = userService;
        this.objectMapper = objectMapper;
    }


    @JmsListener(destination = QUEUE_NAME, containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String message) {
        try {
            UserRequestDTO user = objectMapper.readValue(message, UserRequestDTO.class);
            userService.createUser(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



}