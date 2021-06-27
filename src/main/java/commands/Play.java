package commands;

import java.awt.*;
import java.io.File;

import com.bot.Path;
import com.bot.Secret;
import lavaPlayer.PlayerManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Play extends ListenerAdapter{
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(event.getAuthor().isBot()) return;	//check for Bot

        String msg[] = event.getMessage().getContentRaw().split("\\s+"); //erstellt ein Array splitet bei leer zeichen
        //erstellt also Argumente args

        if(msg[0].equalsIgnoreCase(Secret.get("prefix") + "play")
                || msg[0].equalsIgnoreCase(Secret.get("prefix") + "p" ))
        {

            final TextChannel channel = event.getChannel();
            final Member self = event.getGuild().getSelfMember();
            final Member user = event.getMember();
            final String prefix = Secret.get("prefix");
            boolean youtubeMode = false;

            channel.sendTyping().complete();	//zeigt im chat an das Memer schreibt


            //Guckt ob es ein zweites argument gibt
            if(msg.length <= 1) {
                channel.sendMessage("Du musst schon sagen was du willst.\n\n"
                        + "Guck mal bei: " + prefix + "play help").queue();
                return;
            }
            if (msg.length >= 3 ){
                if (msg[1].equalsIgnoreCase("yt")){
                    youtubeMode = true;
                }
            }



            //HELP EMBED


            //Teste ob command (prefix + "play help") war
            if(msg[1].equalsIgnoreCase("help")
                    || msg[1].equalsIgnoreCase("h"))
            {

                File file = new File(Path.get("command_block"));
                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle("Alle Commands für " + prefix + "Play")
                        .setDescription("Listet alle Sounds für " + prefix + "play auf!")
                        .setColor(new Color(8649061))
                        .setThumbnail("attachment://block.jpg")	//block.jpg wird bei send Message hinzugefügt
                        .addBlankField(false)
                        .addField("Wow ", "```" + prefix + "play wow```", false)
                        .addField("OK ", "```" + prefix + "play ok```" , false)
                        .addField("Trommelwirbel", "```" + prefix + "play trommelwirbel```" +
                                "```"+ prefix + "play tw```" , false)

                        .addField("Du bist einfach ein Dummkopf" ,"```" + prefix + "play Dummkopf```",false)
                        .addField("Leute die sich an die Stirn schlagen" , "```" + prefix + "play HandGegenKopf```" +
                                "```" +prefix + "play HGK```",false)
                        .addField("Wilhelm scream", "```" + prefix + "play Wilhelm```",false)
                        .addField("2000 years later","```" + prefix + "play 2000yearslater```",false)
                        .addField("Das ist genau die Scheiße auf die ich kein bock habe",
                                "```" + prefix + "play keinBock```",false);

                channel.sendMessage(eb.build()).addFile(file, "block.jpg").queue();


                return;
            }



            //Abfrage ob in voice channel und so

            if(!self.getVoiceState().inVoiceChannel())
            {// Bot nicht im channel
                channel.sendMessage("I need to be in a voice channel!").queue();
                return;
            }

            if(!user.getVoiceState().inVoiceChannel())
            {// user nicht im voice channel
                channel.sendMessage("You need to be in a voice channel!").queue();
                return;
            }

            if(!user.getVoiceState().getChannel().equals(self.getVoiceState().getChannel()))
            {//beide nicht im gleichen voice channel
                channel.sendMessage("We need to be in the same voice channel!").queue();
                return;
            }



            //Play sound

            boolean writeQueue = true;
            String path;
            if (youtubeMode)
            {
                 path = msg[2];  //gets argument 2 a link
            }
            else
            {
                path = Path.get(msg[1]);  //gets argument 1 out of paths/paths-linux file
            }

            if(path == null  ) {
                channel.sendMessage(":grimacing: Da ist was schief gelaufen, guck mal bei " + Secret.get("prefix") + "play help").queue();
                return;
            }
            if (msg[1].equalsIgnoreCase("notify")){
                event.getMessage().delete().queue();
                writeQueue = false;
            }


            PlayerManager.getINSTANCE().loadAndPlay(channel, path, writeQueue);




        }
    }
}
