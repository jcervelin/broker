package io.jcervelin.broker.message.usecases;

import io.jcervelin.broker.message.domains.Action;
import io.jcervelin.broker.message.domains.Sale;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public final class MessageCounter {

    private static MessageCounter INSTANCE;

    private static List<Action> actions;

    private static int numMessages;

    private static List<Sale> sales;

    private MessageCounter() {
    }

    public static MessageCounter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MessageCounter();
            sales = new ArrayList<>();
            actions = new ArrayList<>();
        }

        return INSTANCE;
    }

    public static int getNumMessages() {
        return numMessages;
    }

    public static void nullify() {
        INSTANCE = null;
        numMessages = 0;
        sales = null;
        actions = null;
    }

    public void addNumMessage() {
        numMessages++;
        if (numMessages % 10 == 0) {
            System.out.println("Sale details:");
            sales.forEach(System.out::println);
            final double sum = sales.stream().mapToDouble(Sale::getPrice).sum();
            System.out.printf("\nAmount of sales: %s\n\n", sum);
        }

        if (numMessages == 50) {
            numMessages = 0;
            System.out.println("Broker is pausing...");
            System.out.println("Stop accepting messages");
            System.out.println("All actions at this moment:");
            actions.forEach(System.out::println);
            System.out.println();
            System.out.println("Report of the adjustments that have been made:");
            actions
                    .stream()
                    .map(Action::getType)
                    .collect(Collectors.toSet())
                    .forEach(System.out::println);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Sale> getSales() {
        return sales;
    }

    public List<Action> getActions() {
        return actions;
    }

}