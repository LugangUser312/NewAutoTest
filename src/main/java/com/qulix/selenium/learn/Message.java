package com.qulix.selenium.learn;

/**
 * Created by Starovoytovdv on 25.03.2018.
 */

//TODO Все классы стоит размещать все-таки в пакете. А-ля com.qulix.selenium.learn.*
public class Message {

    private String headline;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String haedline) {
        this.headline = haedline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public Message(String headline, String description){
        this.headline = headline;
        this.description = description;
    }
}