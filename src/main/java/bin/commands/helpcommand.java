
package bin.commands;

import bin.Main;
import bin.command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

 
public class helpcommand implements command {
    private final String HELPCOM = Main.prefix+ "help :: returns list of commands";
 

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
       return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
       infocommand info = new infocommand();
       pingcommand ping = new pingcommand();
       eightball ask = new eightball();
       avatarcommand avatar = new avatarcommand();
       slapcommand slap = new slapcommand();
       prunecommand prune = new prunecommand();
       serverinfocommand server = new serverinfocommand();
       kickcommand kick = new kickcommand();
       bancommand ban = new bancommand();
       findcommand find = new findcommand();
       setannchannel setannchannel = new setannchannel();
       String helpstr = "```"
               + help()
               + System.lineSeparator()
               + info.help()
               + System.lineSeparator()
               + ping.help()
               + System.lineSeparator()
               + ask.help()
               + System.lineSeparator()
               + avatar.help()
               + System.lineSeparator()
               + slap.help()
               + System.lineSeparator()
               + prune.help()
               + System.lineSeparator()
               + server.help()
               + System.lineSeparator()
               + kick.help()
               + System.lineSeparator()
               + ban.help()
               + System.lineSeparator()
               + find.help()
               + System.lineSeparator()
               + setannchannel.help()
               + "```"
               + "Found any bug?, let me know at:"
               + " https://github.com/notoriouskyoko/d.va-bot-issuetracker/issues";
        event.getTextChannel().sendMessage(helpstr).queue();
    }
  
    @Override
    public String help() {
        return HELPCOM;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
       return;
    }

    
}
