package io.jcervelin.broker.message.usecases;

import io.jcervelin.broker.message.domains.Message;

public enum MessageType {

    TYPE_1("type_1", new MessageType1()),
    TYPE_2("type_2", new MessageType2()),
    TYPE_3("type_3", new MessageType3());

    private Message message;
    private String type;

    MessageType(String type, Message message) {
        this.type = type;
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
