package com.krinotech.jokeprovider;

import java.util.Random;

public class Joker {

    private String[] jokes = {
            "My friend thinks he is smart. He told me an onion is the only food that makes you cry, so I threw a coconut at his face.",
            "What happens to a frog's car when it breaks down?\n" +
                    "It gets toad away.",
            "Q: Is Google male or female?\n" +
                    "A: Female, because it doesn't let you finish a sentence before making a suggestion.",
            "Q: What did the duck say when he bought lipstick?\n" +
                    "A: \"Put it on my bill.\"",
            "A teacher asked her students to use the word \"beans\" in a sentence. \"My father grows beans,\" said one girl. \"My mother cooks beans,\" said a boy. A third student spoke up, \"We are all human beans.\"",
            "Q: Why was six scared of seven?\n" +
                    "A: Because seven \"ate\" nine."};

    private String joke;

    public String tellJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke + getRandomJoke();
    }

    public String getRandomJoke() {
        Random random = new Random();
        int randomJoke = random.nextInt(this.jokes.length);
        return jokes[randomJoke];
    }
}
