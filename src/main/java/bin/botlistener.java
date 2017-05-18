
package bin;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class botlistener extends ListenerAdapter{
    private Connection con;
    private Statement st;
    private ResultSet rs;
    @Override
    public void onGuildJoin(GuildJoinEvent event){

        try{

            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost/dva?user=root&password=root");
            st = con.createStatement();
            String guildname = event.getGuild().getName();
            String guildid = event.getGuild().getId();
            String guildnamesafe = guildname.replaceAll("[']","");
            String insertGuildData = "INSERT INTO `guilddata`(`guildid`,`guildname`, `annchannelid`) VALUES ('"+guildid+"','"+guildnamesafe+"','')";

            st.execute(insertGuildData);
            con.close();
        }catch(Exception ex){
            System.out.println("error: " + ex);
        }


    }

    @Override
    public void onGuildMemberJoin (GuildMemberJoinEvent event) {
        try {

            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost/dva?user=root&password=root");
            st = con.createStatement();

            String guildid = event.getGuild().getId();
            String query = "SELECT * FROM guilddata WHERE guildid = '" + guildid + "'";
            rs = st.executeQuery(query);

            while (rs.next()) {

                String guildid1 = rs.getString("guildid");
                String annchannelid = rs.getString("annchannelid");

                if (guildid.equals(guildid1)) {
                    if (!annchannelid.equals(null)) {
                        event.getGuild().getTextChannelById(annchannelid).sendMessage("Welcome to " + event.getGuild().getName() + " " + event.getMember().getAsMention() + "!").queue();
                        }
                    }

                }
                con.close();
            } catch(Exception ex){
                System.out.println("error: " + ex);


            }

     }

    @Override
    public void onGuildMemberLeave (GuildMemberLeaveEvent event) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost/dva?user=root&password=");
            st = con.createStatement();
            String guildid = event.getGuild().getId();
            String query = "SELECT * FROM guilddata WHERE guildid = '"+guildid+"'";
            rs = st.executeQuery(query);

            while(rs.next()){

                String guildid1 = rs.getString("guildid");
                String annchannelid = rs.getString("annchannelid");

                if (guildid.equals(guildid1)) {
                    if (!annchannelid.equals(null)) {
                        event.getGuild().getTextChannelById(annchannelid).sendMessage(event.getMember().getAsMention() + " left " + event.getGuild().getName() + "!").queue();
                    }
                }

            }
            con.close();

        }catch (Exception e){

        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
      try{
         JSONParser parser = new JSONParser();

            Object obj = parser.parse(new FileReader("./config/config.json"));
 
            JSONObject jsonObject = (JSONObject) obj;
 
           String  prefix = (String) jsonObject.get("prefix");
     
        
        if(event.getMessage().getContent().startsWith(prefix) && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()){
           try{
            Main.handleCommand(Main.parser.parse(event.getMessage().getContent().toLowerCase(), event));
           }catch(Exception ex){
                        System.out.println(ex);
                        ex.printStackTrace();

                     }
           System.out.println(event.getMessage().toString());
      }
        }catch(Exception ex){
            System.out.println(ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void onReady(ReadyEvent e){
        
        System.out.println("Status :: All systems checked out.");  
    }

    
}
