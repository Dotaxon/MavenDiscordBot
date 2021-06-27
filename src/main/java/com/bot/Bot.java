package com.bot;


import commands.Shutdown;
import commands.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Bot {
    private static JDA jda;
    private static String tokenVariable = "TOKENMEMER_BETA";
    public static String pathFile = "paths-linux";
    private Bot() throws LoginException, InterruptedException
    {

        jda = new JDABuilder()
//					.setChunkingFilter(ChunkingFilter.ALL)
//					.setMemberCachePolicy(MemberCachePolicy.ALL)
//					.enableIntents(GatewayIntent.GUILD_MESSAGES)
//					.enableIntents(GatewayIntent.GUILD_MESSAGE_REACTIONS)
//					.enableIntents(GatewayIntent.GUILD_MESSAGE_TYPING)
//					.setEventManager(new InterfacedEventManager())

                .enableCache(CacheFlag.VOICE_STATE)
                .addEventListeners(new Shutdown())
                .addEventListeners(new Startup())
                .addEventListeners(new Id())
                .addEventListeners(new MusicJoinLeave())
                .addEventListeners(new Play())
                .addEventListeners(new Help())
                .addEventListeners(new GetPrefix())
                .addEventListeners(new Clear())
                .setActivity(Activity.playing("Gott"))	//setzt die Activität
                .setToken(Secret.get(tokenVariable))   //setzt den TOKEN aus der .env
                .build().awaitReady();

    }

    public static void main(String[] args) throws LoginException, InterruptedException
    {
        new Bot();
    }

    public static JDA getJda() {
        return jda;
    }


}
