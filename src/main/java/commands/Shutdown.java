package commands;

import com.bot.Secret;
import me.duncte123.botcommons.BotCommons;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Shutdown extends ListenerAdapter{



    //Fährt den Bot herunter wenn Owner es sagt
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(event.getAuthor().isBot()) return;


        String msg = event.getMessage().getContentRaw();		//speichert inhalt nachricht

        if(msg.equalsIgnoreCase(Secret.get("PREFIX") + "bye") 			//triger wenn prefix + shutdwon und der Author der Owner ist
                && event.getAuthor().getId().equals(Secret.get("OWNER_ID"))) {

            event.getChannel().sendMessage("Bye bye  :sob:").queue();
            System.out.println("Shutdown");
            event.getJDA().shutdown();
            BotCommons.shutdown(event.getJDA());
        }
    }

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        if(event.getAuthor().isBot()) return;

        String msg = event.getMessage().getContentRaw();

        if(msg.equalsIgnoreCase("Shutdown") 			//triger wenn prefix + shutdwon und der Author der Owner ist
                && event.getAuthor().getId().equals(Secret.get("OWNER_ID"))) {

            event.getChannel().sendMessage("Shutdown received").queue();
            System.out.println("Shutdown");
            event.getJDA().shutdown();
            BotCommons.shutdown(event.getJDA());
        }
    }
}
