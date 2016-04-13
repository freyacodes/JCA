package frederikam.jca;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class JCA {
    
    private String user;
    private String key;
    private String nick;

    protected JCA(String user, String key, String nick) {
        this.user = user;
        this.key = key;
        this.nick = nick;
    }
    
    public String getResponse(String query){
        try {
            HttpRequestWithBody post = Unirest.post("https://cleverbot.io/1.0/ask")
                    .header("user", user)
                    .header("key", key)
                    .header("nick", nick)
                    .header("text", query);
            
            JSONObject json = post.asJson().getBody().getObject();
            String response = json.getString("text");
            return response;
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
