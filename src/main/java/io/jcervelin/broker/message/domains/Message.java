package io.jcervelin.broker.message.domains;

import java.io.IOException;

public interface Message {
    void execute(Object content) throws IOException;
}
