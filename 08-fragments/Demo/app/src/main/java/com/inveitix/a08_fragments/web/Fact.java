package com.inveitix.a08_fragments.web;

public class Fact {

    private String type;
    private String text;

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Fact{" +
                "type='" + type + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
