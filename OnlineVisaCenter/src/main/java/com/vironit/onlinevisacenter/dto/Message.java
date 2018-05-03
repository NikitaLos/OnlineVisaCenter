package com.vironit.onlinevisacenter.dto;

import java.io.Serializable;

public class Message  {
    private String text;

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
