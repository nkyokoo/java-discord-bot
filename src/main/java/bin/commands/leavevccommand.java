
package bin.commands;

import bin.Main;
import bin.command;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;

 
public class leavevccommand implements command {
   
    private final String HELP = Main.prefix+ "leave :: make d.va leave voicechat";
 

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
       return true;
        
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getGuild().getMemberById(event.getJDA().getSelfUser().getId()).getVoiceState().inVoiceChannel()) {
           
           AudioManager aum = event.getGuild().getAudioManager();
           aum.closeAudioConnection(); 
        }else{
        event.getTextChannel().sendMessage("I'm not in a channel").queue();
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
