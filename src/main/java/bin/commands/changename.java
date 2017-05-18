package bin.commands;


import bin.Main;
import bin.command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class changename implements command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
       try {
           if (event.getMessage().getAuthor().getId().equals("169465957146820608")) {
               String name = event.getMessage().getContent().replaceAll(Main.prefix + "changename", "").trim();
               event.getJDA().getSelfUser().getManager().setName(name).queue();
               event.getTextChannel().sendMessage("changed name to: " + name).queue();
           }
       }catch(Exception e){
           e.printStackTrace();
       }
    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        return;
    }
}
