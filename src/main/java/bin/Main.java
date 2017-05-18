package bin;

import bin.commands.*;

import java.io.FileReader;
import java.util.HashMap;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {

    public static final commandparser parser = new commandparser();

    public static HashMap<String, command> commands = new HashMap<String, command>();
    public static String prefix = "";

    public static void main(String[] args) {
        try {

            JSONParser parser = new JSONParser();

            Object obj = parser.parse(new FileReader("./config/config.json"));

            JSONObject jsonObject = (JSONObject) obj;

            String token = (String) jsonObject.get("token");
            prefix = (String) jsonObject.get("prefix");
                
            JDA bot = new JDABuilder(AccountType.BOT).addListener(new botlistener()).setToken(token).setGame(Game.of("'"+ prefix +"help' for a list of commands!")).buildAsync();
            bot.setAutoReconnect(true);
            
            commands.put("bot", new infocommand());
            commands.put("help", new helpcommand());
            commands.put("ping", new pingcommand());
            commands.put("shutdown", new shutdowncommand());
            commands.put("ask", new eightball());
            commands.put("avatar", new avatarcommand());
            commands.put("slap", new slapcommand());
            commands.put("prune", new prunecommand());
            commands.put("server", new serverinfocommand());
            commands.put("kick", new kickcommand());
            commands.put("ban", new bancommand());
            commands.put("find", new findcommand());
            commands.put("summon", new joinvccommand());
            commands.put("leave", new leavevccommand());
            commands.put("evaljs", new evaljs());
            commands.put("setannchannel", new setannchannel());
            commands.put("changename", new changename());

        } catch (Exception e) {
            System.err.print(e);
        }
    }
    public static void handleCommand(commandparser.commandcontainer cmd) {
        if (commands.containsKey(cmd.invoke)) {
            boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);

            if (safe) {
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
                commands.get(cmd.invoke).executed(safe, cmd.event);
            } else {
                commands.get(cmd.invoke).executed(safe, cmd.event);

            }

        }
    }

}
