package com.shradha.javajokes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Joker {

    private static final List<String> jokes;
    private static final Random random = new Random();

    static {
        jokes = new ArrayList<>();
        jokes.add("Did you hear about the crook who stole a calendar? He got twelve months.");
        jokes.add("Q. What's the difference between ignorance and apathy?\nA. I don’t know and I don’t care.");
        jokes.add("Did you hear about the semi-colon that broke the law? He was given two consecutive sentences.");
        jokes.add("Never criticize someone until you've walked a mile in their shoes. That way, when you criticize" +
                " them, they won't be able to hear you from that far away. Plus, you'll have their shoes.");
        jokes.add("The world tongue-twister champion just got arrested. I hear they're gonna give him a really tough sentence.");
        jokes.add("I own the world's worst thesaurus. Not only is it awful, it's awful.");
        jokes.add("So what if I don't know what \"Armageddon\" means? It's not the end of the world.");
        jokes.add("Correct punctuation: the difference between a sentence that's well-written and a sentence that's, well, written.");
        jokes.add("I hate Russian dolls, they're so full of themselves.");
        jokes.add("Don't you hate it when someone answers their own questions? I do.");
    }

    public static String getJoke() {
        return jokes.get(random.nextInt(jokes.size()));
    }
}
