package bin.commands;


import bin.Main;
import bin.command;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;

public class joinvccommand implements command {
    private final String HELP = Main.prefix+ "summon :: d.va joins the voicechat you're in";
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getGuild().getMemberById(event.getMessage().getAuthor().getId()).getVoiceState().inVoiceChannel()) {
            
           VoiceChannel vc = event.getGuild().getMemberById(event.getMessage().getAuthor().getId()).getVoiceState().getChannel();
           AudioManager aum = event.getGuild().getAudioManager();
           aum.openAudioConnection(vc);
           
         }else{
        event.getTextChannel().sendMessage("You're not in a channel!").queue();
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
