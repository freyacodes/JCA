package frederikam.jca;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class JCABuilder {

    private String user;
    private String key;
    private String nick;

    public void setUser(String user) {
        this.user = user;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public JCA buildBlocking() {
        try {
            HttpRequestWithBody post = Unirest.post("https://cleverbot.io/1.0/create")
                    .header("user", user)
                    .header("key", key);

            if (nick != null) {
                post.header("nick", nick);
            }
            JSONObject json = post.asJson().getBody().getObject();
            String status = json.getString("status");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

}
