package commands;

import java.awt.Color;

import com.bot.Secret;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Help extends ListenerAdapter{
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(event.getAuthor().isBot()) return;	//check for Bot

        String msg = event.getMessage().getContentRaw();

        if(msg.equalsIgnoreCase(Secret.get("prefix") + "help")
                || msg.equalsIgnoreCase(Secret.get("prefix") + "h" )) {
            event.getChannel().sendTyping().complete();

            String prefix = Secret.get("prefix");

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Hilfe!! Hilfe!! Hilfe!! Hilfe!!")
                    .setThumbnail("https://cdn.pixabay.com/photo/2012/04/02/16/13/question-24851_960_720.png")
                    .setDescription("Hier sind alle Befehle aufgelistet!\n" +
                                    "Groß und kleinschreibung ist Egal!")
                    .setColor(new Color(8649061))
                    .addBlankField(false)
                    .addField("Allgemein",
                            "```" + prefix + "ID```" +
                                    "Gibt dir die 4 Stellige zahl hinter deinem Nicknamen aus\n" +
                                    "```" + prefix + "Help```" +
                                    "Gibt dir das Hier aus\n" +
                                    "```getPrefix```" +
                                    "Gibt das Prefix dieses Bottes", false)
                    .addBlankField(false)
                    .addField("Für Voice Channel",
                            ("```" + prefix +"join``` lässt den Bot dein Voice Channel joinen\n"
                                    + "```"  + prefix + "leave``` Disconnectet den Bot von deinem Voice Channel"), false)
                    .addBlankField(false)
                    .addField("Für Lustige Sounds",
//                            ("```" + prefix +"play wow``` Spielt wow meme\n"
//                                    + "```"  + prefix + "play ok``` Spielt ok meme"
//                                    + "```"  + prefix + "play tw```"
//                                    + "```"  + prefix + "play Tormmelwirbel``` spielt ein Trommelwirbel")
                            "Guck am besten unter ```" + prefix + "play help```"
                            , false)
                    .addBlankField(false)
                    .addField("Play", "Auflistung von allen Sounds```" + prefix +"play help```"
                            + "Spielt Musik von einem Link ab```" + prefix + "play yt LINK``` " +

                            "Stopt den Track und leert die Queue```" + prefix + "clear```" +
                            "oder " +
                            "```" + prefix + "stop```"  , false);


            event.getChannel().sendMessage(eb.build()).queue();





        }
    }
}
