package io.jcervelin.broker.message.usecases;

import io.jcervelin.broker.message.domains.Action;
import io.jcervelin.broker.message.domains.Message;
import io.jcervelin.broker.message.domains.Sale;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class MessageType3 implements Message {

    public void add(Sale sale, Double amount) {
        sale.setPrice(sale.getPrice() + amount);
    }

    public void subtract(Sale sale, Double amount) {
        if (sale.getPrice() > amount) {
            sale.setPrice(sale.getPrice() - amount);
        } else {
            sale.setPrice(0);
        }
    }

    public void multiply(Sale sale, Double amount) {
        sale.setPrice(sale.getPrice() * amount);
    }

    @Override
    public void execute(Object content) throws IOException {
        Action action = (Action) content;
        final MessageCounter instance = MessageCounter.getInstance();
        final List<Sale> sales = instance.getSales();
        sales.add(action.getSale());
        sales
                .stream()
                .filter(sale -> action.getSale().getProduct().equalsIgnoreCase(sale.getProduct()))
                .forEach(sale -> {
                    final Method declaredMethod;
                    try {
                        declaredMethod = this.getClass().getDeclaredMethod(action.getAction(), Sale.class, Double.class);
                        declaredMethod.invoke(this, sale, action.getAmount());
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
        action.setType("type_3");
        action.setAction(String.format("%s %s to each %s recorded",
                action.getAction(),
                action.getAmount(),
                action.getSale().getProduct()
        ));

        instance.getActions().add(action);
        instance.addNumMessage();
    }
}
