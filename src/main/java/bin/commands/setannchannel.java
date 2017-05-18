package bin.commands;

import bin.Main;
import bin.command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class setannchannel implements command {
        private Connection con;
        private Statement st;
        private ResultSet rs;
    private final String HELP = Main.prefix+ "setannchannel {#channel} :: sets the announcement channel";
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getGuild().getMember(event.getMessage().getAuthor()).isOwner()) {
            if (event.getMessage().getMentionedChannels().size() == 1) {
                try {

                    Class.forName("com.mysql.jdbc.Driver");

                    con = DriverManager.getConnection("jdbc:mysql://localhost/dva?user=root&password=root");
                    st = con.createStatement();
                    String annchannel = event.getMessage().getMentionedChannels().get(0).getId();
                    String guildid = event.getGuild().getId();

                    String insertGuildData = "UPDATE `guilddata` SET `annchannelid`= '" + annchannel + "' WHERE guildid ='" + guildid + "'";

                    st.execute(insertGuildData);
                    con.close();
                    event.getTextChannel().sendMessage("now welcomming users in " + event.getMessage().getMentionedChannels().get(0).getName()).queue();
                } catch (Exception ex) {
                    System.out.println("error: " + ex);
                }
            }
        }else{
            event.getTextChannel().sendMessage(event.getMessage().getAuthor().getAsMention() + " you don't have access to this command! \r\n this command is only available for guild owners").queue();
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
