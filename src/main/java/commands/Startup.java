package commands;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Startup extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent event) {

        System.out.println(event.getJDA().getSelfUser() + " is Ready"); //gibt den Namen des Bots aus
    }
}
