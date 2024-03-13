package com.algoriddle.AlgoRiddleBackendApi.Messaging;

import com.azure.messaging.servicebus.ServiceBusReceiverClient;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiverService {

    private final ServiceBusProcessorClient processorClient;

    public MessageReceiverService(@Value("${azure.servicebus.connection-string}") String connectionString,
                                  @Value("${azure.servicebus.queue-name}") String queueName) {
        this.processorClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .processor()
                .queueName(queueName)
                .processMessage(context -> {
                    // Process received message here
                    System.out.printf("Received message: %s%n", context.getMessage().getBody().toString());
                })
                .processError(errorContext -> {
                    // Handle any errors that occur during message processing
                    System.err.printf("Error occurred while processing message: %s%n", errorContext.getException());
                })
                .buildProcessorClient();
    }

    public void startReceivingMessages() {
        processorClient.start();
    }

    public void stopReceivingMessages() {
        processorClient.close();
    }
}
