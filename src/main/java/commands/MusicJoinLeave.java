package commands;



import com.bot.Secret;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

public class MusicJoinLeave extends ListenerAdapter{

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(event.getAuthor().isBot()) return;	//check for Bot


        String msg = event.getMessage().getContentRaw();  //gets the Message
        TextChannel channel = event.getChannel();		//gets Channel
        VoiceChannel connectedChannel ;					//voice channel to connect to / or current channel
        AudioManager audioManager;						//Audio Manager to handel ...



        if(msg.equalsIgnoreCase(Secret.get("PREFIX") + "join")
                || msg.equalsIgnoreCase(Secret.get("prefix") + "j")) 	//checks command for "join"
        {
            if(!event.getGuild().getSelfMember().hasPermission(channel, Permission.VOICE_CONNECT))	//Checks for Permission to join voice channel
            {
                channel.sendMessage("I do not have permissions to join a voice channel").queue();
                return;
            }

            connectedChannel = event.getMember().getVoiceState().getChannel();	//Initzializes connectetd Channel

            if(connectedChannel == null) {				//If the Person is in no channel
                channel.sendMessage("You are not in a Voice Channel").queue();
                return;
            }


            audioManager = event.getGuild().getAudioManager(); //Initzializes audioManager

            if(event.getGuild().getSelfMember().getVoiceState().getChannel() != null )	//Wenn schon in einem Voice Channel
            {
                channel.sendMessage("I am already connected to a voice channel!").queue();
                return;
            }

            if(audioManager.isAttemptingToConnect()) 		//If Bot is already trying to connect to voice channel
            {
                channel.sendMessage("Chill, I am already trying to connect").queue();
                return;
            }

            audioManager.openAudioConnection(connectedChannel);  //connects to voice Channel
            event.getChannel().sendMessage("Guten Tag allerseits!").queue();

        }


        //Leave channel

        else if(msg.equalsIgnoreCase(Secret.get("prefix") + "leave") 				//checks command for "leave"
                || msg.equalsIgnoreCase(Secret.get("prefix") + "dc")
                || msg.equalsIgnoreCase(Secret.get("prefix") + "piss dich"))
        {
            connectedChannel = event.getGuild().getSelfMember().getVoiceState().getChannel();   //current Channel

            if(connectedChannel == null)	//if not connectet to a voice channel
            {
                channel.sendMessage("I am not connected to a voice channel!!").queue();
                return;
            }


            event.getGuild().getAudioManager().closeAudioConnection();  //Disconnects

            channel.sendMessage("Disconnected!").queue();

        }
    }
}
