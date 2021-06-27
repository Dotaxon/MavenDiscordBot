package commands;

import com.bot.Secret;
import lavaPlayer.GuildMusicManager;
import lavaPlayer.PlayerManager;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Clear extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if(event.getAuthor().isBot()) return;	//check for Bot



        String msg = event.getMessage().getContentRaw();

        if(msg.equalsIgnoreCase(Secret.get("prefix") + "clear")
                    ||msg.equalsIgnoreCase(Secret.get("prefix") + "stop"))
        {
            final TextChannel channel = event.getChannel();
            final Member self = event.getGuild().getSelfMember();
            final Member user = event.getMember();
            final String prefix = Secret.get("prefix");


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

            final GuildMusicManager musicManager = PlayerManager.getINSTANCE().getMusicManager(event.getGuild());

            musicManager.scheduler.player.stopTrack();
            musicManager.scheduler.queue.clear();

            channel.sendMessage("The queue has been cleared and the track has been stop").queue();
        }

    }
}
