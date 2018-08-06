package io.jcervelin.broker.message.usecases;

public enum Type {

    TYPE_1("type_1"), TYPE_2("type_2"), TYPE_3("type_3");

    private String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
