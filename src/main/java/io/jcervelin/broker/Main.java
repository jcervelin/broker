package io.jcervelin.broker;


import io.jcervelin.broker.message.domains.Action;
import io.jcervelin.broker.message.domains.Event;
import io.jcervelin.broker.message.domains.Sale;
import io.jcervelin.broker.message.gateways.MessageConsumer;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {

        MessageConsumer messageConsumer = new MessageConsumer();

        Sale sale1 = new Sale("apple", 20d);
        Sale sale2 = new Sale("orange", 40d);
        Sale sale3 = new Sale("peach", 30d);

        Event event = new Event("type_1", sale1);
        Event event1 = new Event("type_1", sale2);
        Event event2 = new Event("type_1", sale3);

        Map<Long, Sale> sales = new HashMap<>();
        sales.put(20L, sale2);

        Event event3 = new Event("type_2", sales);

        Action action = new Action();
        action.setAction("add");
        action.setSale(sale2);
        action.setAmount(3d);

        Event event4 = new Event("type_3", action);

        messageConsumer.execute(event4);
        messageConsumer.execute(event3);
        messageConsumer.execute(event);
        messageConsumer.execute(event1);
        for (int i = 0; 100 > i; i++) {
            messageConsumer.execute(event2);
        }
        for (int i = 0; 100 > i; i++) {
            messageConsumer.execute(event2);
        }
        for (int i = 0; 100 > i; i++) {
            messageConsumer.execute(event2);
        }


    }

}
