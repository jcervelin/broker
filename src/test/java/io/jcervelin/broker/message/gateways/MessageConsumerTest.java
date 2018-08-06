package io.jcervelin.broker.message.gateways;

import io.jcervelin.broker.message.config.UnitTestingSupport;
import io.jcervelin.broker.message.domains.Event;
import io.jcervelin.broker.message.domains.Sale;
import io.jcervelin.broker.message.usecases.MessageCounter;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static br.com.six2six.fixturefactory.Fixture.from;
import static io.jcervelin.broker.message.templates.EventTemplate.*;

public class MessageConsumerTest extends UnitTestingSupport {

    @InjectMocks
    private MessageConsumer target;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        MessageCounter.nullify();
    }

    @Test
    public void executeAllTypesTogether() throws IOException {
        //GIVEN all types
        final Event eventType1 = from(Event.class).gimme(EVENT_TYPE_1_APPLE_20);
        final Event eventType2 = from(Event.class).gimme(EVENT_TYPE_2_ORANGE_65);
        final Event eventType3 = from(Event.class).gimme(EVENT_TYPE_3_APPLE_20);

        //WHEN all types are sent
        target.execute(eventType1);
        target.execute(eventType2);
        target.execute(eventType3);

        //THEN
        // 3 message is count
        // 1 apple by type 1
        // 3 orange by type 2
        // 1 apple by type 3
        Assertions.assertThat(MessageCounter.getNumMessages()).isEqualTo(3);

        final MessageCounter messageCounter = MessageCounter.getInstance();

        // sales are recorded
        // grouping sales by product
        final Map<String, List<Sale>> salesGroupedByProducts = messageCounter.getSales()
                .stream()
                .collect(Collectors.groupingBy(Sale::getProduct));

        // list of 3 oranges
        final List<Sale> oranges = salesGroupedByProducts.get("orange");

        // list of 2 apples
        final List<Sale> apples = salesGroupedByProducts.get("apple");

        // all of oranges for 65 each
        oranges.forEach(sale -> Assertions.assertThat(sale.getPrice()).isEqualTo(65d));

        // all of apples for 23 each, because type 3 changed price for all apples
        apples.forEach(sale -> Assertions.assertThat(sale.getPrice()).isEqualTo(23d));

        // no log
        Assertions.assertThat(outContent.toString()).isEqualToIgnoringWhitespace("");
    }
}