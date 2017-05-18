
package bin;

import java.io.FileReader;
import java.util.ArrayList;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class commandparser{   
public String prefix;
    
    public commandcontainer parse(String rv, MessageReceivedEvent e){
    try{
         JSONParser parser = new JSONParser();

            Object obj = parser.parse(new FileReader("./config/config.json"));
 
            JSONObject jsonObject = (JSONObject) obj;
 
           prefix = (String) jsonObject.get("prefix");
        System.out.println(prefix);
    }catch(Exception ex){
            System.out.println(ex);
            ex.printStackTrace();
}
     
      ArrayList<String> split = new ArrayList<String>();
      String raw = rv;
      String beheaded = raw.replaceFirst(prefix, "");
      String[] splitbehead = beheaded.split(" ");
      for(String s : splitbehead){
          split.add(s);
      }
      String invoke = split.get(0);
      String[] args = new String[split.size() -1];
      split.subList(1, split.size()).toArray(args);
      
      return new commandcontainer(raw, beheaded, splitbehead, invoke, args , e);
    }

 
    public class commandcontainer{
        public final String raw;
        public final String beheaded;
        public final String[] splitbehead;
        public final String invoke;
        public final String[] args; 
        public final MessageReceivedEvent event;
        
        public commandcontainer(String rw,String beheaded, String[] splitbehead, String invoke, String[] args, MessageReceivedEvent e){
            this.raw = rw;
            this.beheaded = beheaded;
            this.event = e;
            this.args = args;
            this.invoke = invoke;
            this.splitbehead = splitbehead;
        }
        
        
    }
}
