package com.algoriddle.AlgoRiddleBackendApi.Messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;


@SpringBootApplication
@EnableJms
public class MessageReceiverService  {
    private static final String QUEUE_NAME = "userregistrationqueue";

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = QUEUE_NAME, containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String message) {
        System.out.println(message);
    }



}