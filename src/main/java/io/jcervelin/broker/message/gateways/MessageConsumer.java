package io.jcervelin.broker.message.gateways;

import io.jcervelin.broker.message.domains.Event;
import io.jcervelin.broker.message.domains.Message;
import io.jcervelin.broker.message.usecases.MessageType;

import java.io.IOException;

public class MessageConsumer {


    public void execute(Event event) throws IOException {

        final MessageType messageType = MessageType.valueOf(event.getType().toUpperCase());

        Message message = messageType.getMessage();

        message.execute(event.getContent());
    }

}
