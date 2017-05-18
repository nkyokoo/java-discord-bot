
package bin.commands;

import bin.Main;
import bin.command;
import java.util.List;
import static net.dv8tion.jda.core.Permission.KICK_MEMBERS;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

 
public class kickcommand implements command {
    private final String HELP = Main.prefix+ "kick {@USER} :: kicks metioned user.(only useable by members with kick permission)";
 

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
       return true;
        
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
          List<User> mentioneduser = event.getMessage().getMentionedUsers();
    if(event.getGuild().getMember(event.getMessage().getAuthor()).isOwner() || event.getGuild().getMember(event.getMessage().getAuthor()).hasPermission(KICK_MEMBERS) ){ 
      if(event.getGuild().getMemberById(event.getJDA().getSelfUser().getId()).hasPermission(KICK_MEMBERS)){  
      if(mentioneduser.size() == 1){
         
         for (int i=0; i<mentioneduser.size(); i++){

           try{
          
         Member member = event.getGuild().getMemberById(mentioneduser.get(0).getId());
                member.getUser().openPrivateChannel().complete();
            member.getUser().getPrivateChannel().sendMessage("You have been **KICKED** by: " + event.getMessage().getAuthor().getName() + " from: " + event.getGuild().getName()).queue();  
               
             event.getGuild().getController().kick(event.getGuild().getMemberById(mentioneduser.get(0).getId())).queue();
             event.getTextChannel().sendMessage(mentioneduser.get(0).getAsMention() + " got **KILLED** " + " by " + event.getMessage().getAuthor().getAsMention()).queue();
           
            }catch(Exception e){
               System.out.println(e);
               e.printStackTrace();
         }
       }
          
      }if(mentioneduser.size() == 0){
          
           event.getTextChannel().sendMessage("please mention a user").queue();
        
         }
      }else{
          event.getTextChannel().sendMessage("I don't have kick permission " + event.getMessage().getAuthor().getAsMention() + ", please give me kick permission").queue();
      }
       
    }else{
           event.getTextChannel().sendMessage(event.getMessage().getAuthor().getAsMention()+" You don't have access to this command!").queue();
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
