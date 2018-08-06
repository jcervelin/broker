package io.jcervelin.broker.message.usecases;

import io.jcervelin.broker.message.domains.Action;
import io.jcervelin.broker.message.domains.Message;
import io.jcervelin.broker.message.domains.Sale;

import java.io.IOException;

public class MessageType1 implements Message {

    private Sale sale;

    @Override
    public void execute(Object content) throws IOException {
        this.sale = (Sale) content;
        final MessageCounter instance = MessageCounter.getInstance();
        instance.getSales().add(sale);
        Action action = new Action();
        action.setAction(String.format("Sale a %s for %s", sale.getProduct(), sale.getPrice()));
        action.setSale(sale);
        action.setType("type_1");
        instance.getActions().add(action);
        instance.addNumMessage();
    }
}
