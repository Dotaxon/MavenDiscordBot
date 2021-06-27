package commands;

import com.bot.Secret;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GetPrefix extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(event.getAuthor().isBot()) return;

        String msg = event.getMessage().getContentRaw();

        if (msg.equalsIgnoreCase("getPrefix")) {
            event.getChannel().sendMessage("The prefix is :drum::   " + Secret.get("prefix")).queue();
        }
    }
}
