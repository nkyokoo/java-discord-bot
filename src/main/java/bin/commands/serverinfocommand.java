/*
    command template
 */
package bin.commands;

import bin.Main;
import bin.command;
import java.awt.Color;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;


public class serverinfocommand implements command {
private final String HELP = Main.prefix+ "server :: returns info of the server you executed the command in";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        String totalusers = String.valueOf(event.getGuild().getMembers().size());
        
        embed.addField("guild name",event.getGuild().getName(),true);
        embed.addField("guild owner: ", event.getGuild().getOwner().getEffectiveName(),true);
        embed.addField("Total members",totalusers,true);
        embed.addField("date created", event.getGuild().getCreationTime().toString() ,true);
        embed.addField("region",event.getGuild().getRegion().toString() ,true);
        try{
        embed.addField("afk channel",event.getGuild().getAfkChannel().toString() ,true);
        }catch(NullPointerException e){
            embed.addField("afk channel","no afk channel" ,true);
        }
        embed.setColor(Color.green);
        embed.setTitle("Server info", null);
        embed.setDescription("Information about: " + event.getGuild().getName());
        embed.setAuthor(event.getJDA().getSelfUser().getName(), event.getJDA().getSelfUser().getAvatarUrl(), null);
        event.getTextChannel().sendMessage(embed.build()).queue();
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
