/*
    command template
 */
package bin.commands;

import bin.Main;
import bin.command;
import java.awt.Color;
import java.util.List;
import net.dv8tion.jda.core.EmbedBuilder;
import static net.dv8tion.jda.core.Permission.KICK_MEMBERS;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.requests.Route;


public class findcommand implements command {
private final String HELP = Main.prefix+ "find {@user} :: finds user on server and returns info about them";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
               List<User> mentioneduser = event.getMessage().getMentionedUsers();
  
      if(mentioneduser.size() == 1){
         
         for (int i=0; i<mentioneduser.size(); i++){

           try{
          
             String username = mentioneduser.get(0).getName();
             String date  = String.valueOf(event.getGuild().getMember(mentioneduser.get(0)).getJoinDate().getDayOfMonth());
             String month = String.valueOf(event.getGuild().getMember(mentioneduser.get(0)).getJoinDate().getMonth());
             String year  = String.valueOf(event.getGuild().getMember(mentioneduser.get(0)).getJoinDate().getYear());
             String datejoined = date + "-" + month + "-" + year;
             EmbedBuilder embed = new EmbedBuilder();
               
        embed.addField("user name",username,true);
     try{
        String nickname = event.getGuild().getMember(mentioneduser.get(0)).getNickname();
        
        embed.addField("Nick name",nickname,true);
       }catch(Exception ex){
         embed.addField("Nick name","no nickname",true);
       }
       List<Role> memberroles = event.getGuild().getMember(mentioneduser.get(0)).getRoles();
       if (memberroles.size() != 0) {

           String roles = "";
           for (int x = 0; x < memberroles.size(); x++) {

               roles += memberroles.get(x).getName() + ", ";

           }
           embed.addField("roles", roles, true);
       }else{

           embed.addField("roles","no roles assigned",true);
       }

        embed.addField("Date joined",datejoined ,true);
        embed.addField("status",event.getGuild().getMember(mentioneduser.get(0)).getOnlineStatus().name(),true);
       try{
        String membergame = event.getGuild().getMember(mentioneduser.get(0)).getGame().toString();   
        embed.addField("game",membergame,true);
       }catch(Exception ex){
         embed.addField("game","not playing a game",true);
       }
        embed.setColor(Color.RED);
        embed.setTitle(mentioneduser.get(0).getName(), null);
        embed.setDescription("user info");
        embed.setAuthor(event.getJDA().getSelfUser().getName(), null, null);
        event.getTextChannel().sendMessage(embed.build()).queue();

           
            }catch(Exception e){
               System.out.println(e);
               e.printStackTrace();
                 }
              }
      }else{
               event.getTextChannel().sendMessage("please mention a user, baka!").queue();
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
