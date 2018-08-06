package io.jcervelin.broker.message.usecases;

import io.jcervelin.broker.message.config.UnitTestingSupport;
import io.jcervelin.broker.message.domains.Event;
import io.jcervelin.broker.message.domains.Sale;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.io.IOException;
import java.io.PrintStream;

import static br.com.six2six.fixturefactory.Fixture.from;
import static io.jcervelin.broker.message.templates.EventTemplate.EVENT_TYPE_1_APPLE_20;
import static io.jcervelin.broker.message.templates.EventTemplate.EVENT_TYPE_1_ORANGE_65;
import static io.jcervelin.broker.message.templates.SaleTemplate.APPLE_20;
import static io.jcervelin.broker.message.templates.SaleTemplate.ORANGE_65;

public class MessageType1Test extends UnitTestingSupport {

    @InjectMocks
    private MessageType1 target;

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
        //GIVEN a apple sale type 1
        final Event event = from(Event.class).gimme(EVENT_TYPE_1_APPLE_20);
        final Sale sale = from(Sale.class).gimme(APPLE_20);

        //WHEN the message is sent
        target.execute(event.getContent());

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
        final Event event = from(Event.class).gimme(EVENT_TYPE_1_ORANGE_65);
        final Sale sale = from(Sale.class).gimme(ORANGE_65);

        for (int i = 0; i < 10; i++) {
            target.execute(event.getContent());
        }

        final MessageCounter messageCounter = MessageCounter.getInstance();

        //THEN
        // 10 messages is count
        Assertions.assertThat(MessageCounter.getNumMessages()).isEqualTo(10);

        // sale is registrated
        Assertions.assertThat(messageCounter.getSales().get(0)).isEqualTo(sale);

        // 10 sales are registrated
        Assertions.assertThat(messageCounter.getSales().size()).isEqualTo(10);

        // log output is 10 sales and only one log with total, because just 10 messages are sent
        Assertions.assertThat(outContent.toString())
                .isEqualToIgnoringWhitespace("Sale details:\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "\n" +
                        "Amount of sales: 650.0\n" +
                        "\n");
    }

    @Test
    public void executeShouldSet50Messages() throws IOException {
        // GIVEN 50 sales
        final Event event = from(Event.class).gimme(EVENT_TYPE_1_ORANGE_65);
        final Sale sale = from(Sale.class).gimme(ORANGE_65);

        //WHEN 50 messages are sent
        for (int i = 0; i < 50; i++) {
            target.execute(event.getContent());
        }

        final MessageCounter messageCounter = MessageCounter.getInstance();

        // THEN
        // a 3 sec timeout starts to represent the broker pause and stop
        // message counting is 0, because 50 are sent
        Assertions.assertThat(MessageCounter.getNumMessages()).isEqualTo(0);
        Assertions.assertThat(messageCounter.getSales().get(0)).isEqualTo(sale);

        // 50 sales are made
        Assertions.assertThat(messageCounter.getSales().size()).isEqualTo(50);

        // log output is 50 sales and all logs about pause and stop broker
        Assertions.assertThat(outContent.toString())
                .isEqualToIgnoringWhitespace("Sale details:\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "\n" +
                        "Amount of sales: 650.0\n" +
                        "\n" +
                        "Sale details:\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "\n" +
                        "Amount of sales: 1300.0\n" +
                        "\n" +
                        "Sale details:\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "\n" +
                        "Amount of sales: 1950.0\n" +
                        "\n" +
                        "Sale details:\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "\n" +
                        "Amount of sales: 2600.0\n" +
                        "\n" +
                        "Sale details:\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "Sale{product='orange', price=65.0}\n" +
                        "\n" +
                        "Amount of sales: 3250.0\n" +
                        "\n" +
                        "Broker is pausing...\n" +
                        "Stop accepting messages\n" +
                        "All actions at this moment:\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "Action{action='Sale a orange for 65.0', sale=Sale{product='orange', price=65.0}, amount=0.0, type='type_1'}\n" +
                        "\n" +
                        "Report of the adjustments that have been made:\n" +
                        "type_1\n");
    }

    @Test(expected = NullPointerException.class)
    public void shouldReturnException() throws IOException {
        target.execute(null);
    }

}