package bin.commands;

import bin.Main;
import bin.command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;


public class pingcommand implements command {
private final String HELPPING = Main.prefix+ "ping :: returns pong and ms";
 

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
       return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        String ms = String.valueOf(event.getJDA().getPing());
       event.getTextChannel().sendMessage("PONG!  "  + ms + "ms").queue();
       
    }
  
    @Override
    public String help() {
        return HELPPING;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
       return;
    }
    
}
