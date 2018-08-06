package io.jcervelin.broker.message.usecases;

import io.jcervelin.broker.message.config.UnitTestingSupport;
import io.jcervelin.broker.message.domains.Action;
import io.jcervelin.broker.message.domains.Sale;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.io.IOException;
import java.io.PrintStream;

import static br.com.six2six.fixturefactory.Fixture.from;
import static io.jcervelin.broker.message.templates.ActionTemplate.*;

public class MessageType3Test extends UnitTestingSupport {
    @InjectMocks
    private MessageType3 target;

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
    public void executeAdd() throws IOException {
        //GIVEN 1 apple sales type 3 with add 3
        final Action action = from(Action.class).gimme(ACTION_ADD);

        //WHEN the message is sent
        target.execute(action);

        //THEN
        // one message is count
        Assertions.assertThat(MessageCounter.getNumMessages()).isEqualTo(1);

        final MessageCounter messageCounter = MessageCounter.getInstance();

        // sale is recorded and his price was 3 added
        final Sale stored = messageCounter.getSales().get(0);
        Assertions.assertThat(stored.getPrice()).isEqualTo(23d);

        // no log
        Assertions.assertThat(outContent.toString()).isEqualToIgnoringWhitespace("");

    }

    @Test
    public void executeMultiply() throws IOException {
        //GIVEN  orange sales type 3 with multiply 2
        final Action action = from(Action.class).gimme(ACTION_MULTIPLY);

        //WHEN the message is sent
        target.execute(action);

        //THEN
        // one message is count
        Assertions.assertThat(MessageCounter.getNumMessages()).isEqualTo(1);

        final MessageCounter messageCounter = MessageCounter.getInstance();

        // sale is recorded and his price 65 was multiplied by 2
        final Sale stored = messageCounter.getSales().get(0);
        Assertions.assertThat(stored.getPrice()).isEqualTo(130d);

        // no log
        Assertions.assertThat(outContent.toString()).isEqualToIgnoringWhitespace("");
    }

    @Test
    public void executeSubtract() throws IOException {
        //GIVEN  orange sales type 3 with subtract 4
        final Action action = from(Action.class).gimme(ACTION_SUBTRACT);

        //WHEN the message is sent
        target.execute(action);

        //THEN
        // one message is count
        Assertions.assertThat(MessageCounter.getNumMessages()).isEqualTo(1);

        final MessageCounter messageCounter = MessageCounter.getInstance();

        // sale is recorded and his price 65 was subtracted by 4
        final Sale stored = messageCounter.getSales().get(0);
        Assertions.assertThat(stored.getPrice()).isEqualTo(61d);

        // no log
        Assertions.assertThat(outContent.toString()).isEqualToIgnoringWhitespace("");
    }

}