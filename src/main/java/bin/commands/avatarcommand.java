/*
    command template
 */
package bin.commands;

import bin.Main;
import bin.command;
import java.util.List;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;


public class avatarcommand implements command {
private final String HELP = Main.prefix+ "avatar {@user} :: returns the mentioned user's avatar url, d.va avatar returns your own avatar url";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) { 
          List<User> mentioneduser = event.getMessage().getMentionedUsers();
     
      if(mentioneduser.size() == 1){
         
         for (int i=0; i<mentioneduser.size(); i++){
           
            String avatarurl =  mentioneduser.get(0).getAvatarUrl();
            
            if(avatarurl != null){
              event.getTextChannel().sendMessage(avatarurl).queue();
              }else{
                
           event.getTextChannel().sendMessage("This user doesn't have an avatar, what a pleb!").queue();
           
       }
         }
          
      }if(mentioneduser.size() == 0){
  
       String avatarurl = event.getMessage().getAuthor().getAvatarUrl();
       
        if(avatarurl != null){
              event.getTextChannel().sendMessage(avatarurl).queue();
              }else{
           event.getTextChannel().sendMessage("you don't have an avatar, what a pleb!").queue();
             }
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
