package io.jcervelin.broker.message.usecases;

import io.jcervelin.broker.message.config.UnitTestingSupport;
import io.jcervelin.broker.message.domains.Sale;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static br.com.six2six.fixturefactory.Fixture.from;
import static io.jcervelin.broker.message.templates.SaleTemplate.APPLE_20;

public class MessageType2Test extends UnitTestingSupport {

    @InjectMocks
    private MessageType2 target;

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
    public void executeShouldSet1Message() throws IOException {
        //GIVEN 5 apple sales type 2
        final Sale sale = from(Sale.class).gimme(APPLE_20);
        final Map<Long, Sale> map = new HashMap<>();
        map.put(5L, sale);

        //WHEN the message is sent
        target.execute(map);

        //THEN
        // one message is count
        Assertions.assertThat(MessageCounter.getNumMessages()).isEqualTo(1);

        final MessageCounter messageCounter = MessageCounter.getInstance();

        // sale is recorded
        Assertions.assertThat(messageCounter.getSales().get(0)).isEqualTo(sale);

        // no log
        Assertions.assertThat(outContent.toString()).isEqualToIgnoringWhitespace("");

    }

    @Test
    public void executeShouldSet10Messages() throws IOException {
        //GIVEN 10 apple sales type 2
        final Sale sale = from(Sale.class).gimme(APPLE_20);
        final Map<Long, Sale> map = new HashMap<>();
        map.put(10L, sale);

        //AND the message is sent 10 times
        for (int i = 0; i < 10; i++) {
            target.execute(map);
        }

        //THEN
        // 10 messages is count
        Assertions.assertThat(MessageCounter.getNumMessages()).isEqualTo(10);

        final MessageCounter messageCounter = MessageCounter.getInstance();

        // 100 sales are registrated (10 messages with 10 sales each)
        Assertions.assertThat(messageCounter.getSales().size()).isEqualTo(100);

        // log output is 100 sales and only one log with total, because just 10 messages are sent
        Assertions.assertThat(outContent.toString()).isEqualToIgnoringWhitespace("Sale details:\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "Sale{product='apple', price=20.0}\n" +
                "\n" +
                "Amount of sales: 2000.0\n" +
                "\n");
    }

    @Test
    public void executeShouldSet50Messages() throws IOException {
        // GIVEN 2 sales
        final Sale sale = from(Sale.class).gimme(APPLE_20);
        final Map<Long, Sale> map = new HashMap<>();
        map.put(2L, sale);

        //WHEN 50 messages with 2 sales each are sent
        for (int i = 0; i < 50; i++) {
            target.execute(map);
        }
        final MessageCounter messageCounter = MessageCounter.getInstance();

        // THEN
        // a 3 sec timeout starts to represent the broker pause and stop
        // message counting is 0, because 50 are sent
        Assertions.assertThat(MessageCounter.getNumMessages()).isEqualTo(0);

        // 100 sales are made
        Assertions.assertThat(messageCounter.getSales().size()).isEqualTo(100);

        // log output is 100 sales and all logs about pause and stop broker
        Assertions.assertThat(outContent.toString())
                .isEqualToIgnoringWhitespace("Sale details:\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "\n" +
                        "Amount of sales: 400.0\n" +
                        "\n" +
                        "Sale details:\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "\n" +
                        "Amount of sales: 800.0\n" +
                        "\n" +
                        "Sale details:\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "\n" +
                        "Amount of sales: 1200.0\n" +
                        "\n" +
                        "Sale details:\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "\n" +
                        "Amount of sales: 1600.0\n" +
                        "\n" +
                        "Sale details:\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "Sale{product='apple', price=20.0}\n" +
                        "\n" +
                        "Amount of sales: 2000.0\n" +
                        "\n" +
                        "Broker is pausing...\n" +
                        "Stop accepting messages\n" +
                        "All actions at this moment:\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "Action{action='Sale a apple for 20.0 2 times\n" +
                        "\n" +
                        "', sale=Sale{product='apple', price=20.0}, amount=40.0, type='type_2'}\n" +
                        "\n" +
                        "Report of the adjustments that have been made:\n" +
                        "type_2\n");
    }

    @Test(expected = NullPointerException.class)
    public void shouldReturnException() throws IOException {
        target.execute(null);
    }

}