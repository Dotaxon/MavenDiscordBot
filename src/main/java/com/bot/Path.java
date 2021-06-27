package com.bot;

import io.github.cdimascio.dotenv.Dotenv;

public class Path {
    private final static Dotenv dotenv = Dotenv.configure()
            .filename(Bot.pathFile)
            .load();

    public static String get(String key) {
        return dotenv.get(key.toUpperCase());
    }
}
