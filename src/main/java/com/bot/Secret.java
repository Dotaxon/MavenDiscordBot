package com.bot;

import io.github.cdimascio.dotenv.Dotenv;

public class Secret {

    private static final Dotenv dotenv = Dotenv.configure()
            .filename("env")
            .load(); //lädt die .env

    public static String get(String key) {		//funktion um aus der dotenv datei etwas zu laden
        return dotenv.get(key.toUpperCase());	//Mit einem bestimmten schlüssel wort
    }


}
