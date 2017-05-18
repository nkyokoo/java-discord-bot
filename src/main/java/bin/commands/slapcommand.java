
package bin.commands;

import bin.Main;
import bin.command;
import java.io.File;
import java.util.List;
import java.util.Random;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;


public class slapcommand implements command {
private final String HELP = Main.prefix+ "slap {@username} :: slaps mentioned user. don't include brackets"; //for the help command. telling users that this command exist

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
          List<User> mentioneduser = event.getMessage().getMentionedUsers();
           String[] randslap;
    Random rand;

        rand = new Random();


        randslap = new String[]{
                "./resources/slapimg/slap.gif",
                "./resources/slapimg/slap1.gif",
                "./resources/slapimg/slap2.gif",
                "./resources/slapimg/slap3.gif",
                "./resources/slapimg/slap4.gif",
                "./resources/slapimg/slap5.gif",
                "./resources/slapimg/slap6.gif",
                "./resources/slapimg/slap7.gif",
                "./resources/slapimg/slap8.gif",
                "./resources/slapimg/slap9.gif",
                "./resources/slapimg/slap10.gif",
                "./resources/slapimg/slap12.gif",
                "./resources/slapimg/slap13.gif",
                "./resources/slapimg/slap14.gif",
                "./resources/slapimg/slap15.gif",
                "./resources/slapimg/slap16.gif",
                "./resources/slapimg/slap17.gif",
                "./resources/slapimg/slap18.gif",
                "./resources/slapimg/slap19.gif"
      
       };

      
       if(mentioneduser.size() == 1){
        for (int i=0; i<mentioneduser.size(); i++){
        int slapgifindex = rand.nextInt(randslap.length);
        String slapgiftopost = randslap[slapgifindex];
        File slapgiffile = new File(slapgiftopost);  
         String message = event.getAuthor().getAsMention() + " slapped " + mentioneduser.get(0).getAsMention();
         try{
             
         event.getTextChannel().sendMessage(message).queue();    
         event.getTextChannel().sendFile(slapgiffile, null).queue();
         
         }catch(Exception e){
            
             e.printStackTrace();
             
         }
      }
             
           

         }
          
      if(mentioneduser.size() == 0){
      
           event.getTextChannel().sendMessage("Nani!?").queue();
           
          }
       }
      
    
    

    @Override
    public String help() {
      return HELP;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
      return; //we don't need to return anything here
    }
    
}
