package bin.commands;

import bin.Main;
import bin.command;
import java.awt.Color;
import java.io.FileReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class infocommand implements command {
private final String HELPINFO = Main.prefix+ "bot :: returns information about d.va";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
       return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        try{
        String osversion = System.getProperty("os.version");
        String osname = System.getProperty("os.name");
        String osarch = System.getProperty("os.arch");
        String javaversion = System.getProperty("java.version");
        String javahome = System.getProperty("java.home");
        String javavendor = System.getProperty("java.vendor");
        String javavendorurl = System.getProperty("java.vendor.url");
        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
        long uptime = rb.getUptime();


        int cores = Runtime.getRuntime().availableProcessors();
        com.sun.management.OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean)
        java.lang.management.ManagementFactory.getOperatingSystemMXBean();
        long ram = os.getTotalPhysicalMemorySize();
        double ramdouble = (double) ram/1*Math.pow(10,-9);
        String coresstr = String.valueOf(cores);
        String ramstr = String.valueOf(ramdouble);
        int totalinguilds = event.getJDA().getGuilds().size();
        String totalstrguilds = String.valueOf(totalinguilds);
        String botname = event.getJDA().getSelfUser().getName();
        String avatarurl = event.getJDA().getSelfUser().getAvatarUrl();
                
                
        //embed building
        EmbedBuilder embed = new EmbedBuilder();
        embed.addField("osname",osname,true);
        embed.addField("osarch",osarch,true);
        embed.addField("osversion",osversion,true);
        embed.addField("cores",coresstr ,true);
        embed.addField("ram",ramstr ,true);
        embed.addField("total guilds", totalstrguilds ,true);
        embed.addField("java version",javaversion ,true);
        embed.addField("java vendor", javavendor ,true);
        embed.addField("java vendor url", javavendorurl ,true);
        embed.setColor(Color.blue);
        embed.setTitle("bot info", null);
        embed.setDescription("Using JDA 3.0.0, https://github.com/DV8FromTheWorld/JDA " + System.lineSeparator() + " Using jsonsimple, https://github.com/fangyidong/json-simple");
        embed.setAuthor(botname, avatarurl, null);
        event.getTextChannel().sendMessage(embed.build()).queue();
     }catch(Exception e){
         e.printStackTrace();
         
         }
         
    }

    @Override
    public String help() {
        return HELPINFO;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
       return;
    }



}
