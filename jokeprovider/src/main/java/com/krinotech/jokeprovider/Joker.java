package com.krinotech.jokeprovider;

public class Joker {

    public static final String FIRST_JOKE = "No jokes in my repository. Boo hoo!";

    private String joke;

    public String tellJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
