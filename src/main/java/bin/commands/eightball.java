
package bin.commands;

import bin.Main;
import bin.command;
import java.util.Random;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

 
public class eightball implements command {

    private final String HELP = Main.prefix+ "ask :: ask d.va a question, only takes yes and no questions";
 

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
       return true;
        
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
    String[] randmessages;
    Random rand;

    
        rand = new Random();


        randmessages = new String[]{
                "How should I know? ",
                "Maybe in the near future",
                "No, never",
                "no",
                "yes",
                "of course!",
                "that is classified information",
                "You should enjoy the little detours. To the fullest. Because that's where you'll find the things more important than what you want",
                "Why?",
                "...",
                "I have been paid keep my mouth shut ",
                "Stop asking me",
                "Ask cortana!"

      
       };
      int errormessageindex = rand.nextInt(randmessages.length);
       String messagetopost = randmessages[errormessageindex]; 
       event.getTextChannel().sendMessage(messagetopost).queue();
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
