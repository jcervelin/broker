package io.jcervelin.broker.message.usecases;

import io.jcervelin.broker.message.domains.Action;
import io.jcervelin.broker.message.domains.Message;
import io.jcervelin.broker.message.domains.Sale;

import java.io.IOException;
import java.util.Map;

public class MessageType2 implements Message {

    private Map<Long, Sale> sales;

    @Override
    public void execute(Object content) throws IOException {
        this.sales = (Map<Long, Sale>) content;
        final MessageCounter instance = MessageCounter.getInstance();
        final Long numSales = sales.keySet().stream().findFirst().orElse(0L);
        final Sale sale = sales.values().stream().findFirst().orElse(null);

        for (int i = 0; i < numSales; i++) {
            instance.getSales().add(sale);
        }

        Action action = new Action();
        final String report = String.format("Sale a %s for %s %s times\n\n", sale.getProduct(), sale.getPrice(), numSales);

        final double sum = sale.getPrice() * numSales;

        action.setAction(report);
        action.setAmount(sum);
        action.setType("type_2");
        action.setSale(sale);
        instance.getActions().add(action);
        instance.addNumMessage();
    }
}

