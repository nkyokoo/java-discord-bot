/*
    command template
 */
package bin.commands;

import bin.Main;
import bin.command;
import java.io.FileReader;
import java.util.List;
import static net.dv8tion.jda.core.Permission.MESSAGE_MANAGE;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.requests.RestAction;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class prunecommand implements command {
private final String HELP = Main.prefix+ "prune {ammount} :: mass deletes messages by given ammount, only usable by server owner and bot owner";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override 
    
    public void action(String[] args, MessageReceivedEvent event) {
        String prunestr = event.getMessage().getContent().replaceAll("[\\.a-z]", "").trim();
     if(event.getGuild().getMember(event.getMessage().getAuthor()).isOwner()){
         
         if(event.getGuild().getMemberById(event.getJDA().getSelfUser().getId()).hasPermission(MESSAGE_MANAGE)){  
       
         int pruneammount = Integer.parseInt(prunestr);
     
            try{
                event.getChannel().getHistory().retrievePast(pruneammount).queue((messages) -> {
                event.getTextChannel().deleteMessages(messages.subList(1, messages.size())).queue();
                event.getTextChannel().sendMessage("deleted " + prunestr + " messages").queue();
            });
            }catch(IllegalArgumentException ex){
               
            }
           
      }else{
          event.getTextChannel().sendMessage("I don't have permissions to manage messages..").queue();
      }
     
        }else{
        event.getTextChannel().sendMessage(event.getMessage().getAuthor().getAsMention() +" You don't have access to this command!").queue();
      }
    }

    @Override
    public String help() {
      return HELP;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
      return; 
    }
    
}
