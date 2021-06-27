package commands;

import com.bot.Bot;
import com.bot.Secret;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/*
 * Gibt den Discriminator in den Chat aus  Triggert bei id
 *
 * Funktioniert nicht richtig Discriminator wird ganz oft ausgegeben
 */


public class Id extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(event.getAuthor().isBot()) return;


        String raw = event.getMessage().getContentRaw();  		//Inhalt der Nachricht
        User user;
        String discriminator;

        if(raw.equalsIgnoreCase(Secret.get("PREFIX") + "id")) 	//triggert wenn prefix + "id"
        {
            long userid = event.getMember().getIdLong();
            user = Bot.getJda().getUserById(userid);		//erstellt einen User von dem man nacher den Discriminator hohlt
            discriminator = user.getDiscriminator();

        }
        else
        {
            return;
        }

        event.getChannel().sendMessage(discriminator).queue();
    }
}
