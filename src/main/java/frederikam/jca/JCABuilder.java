package frederikam.jca;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class JCABuilder {

    private String user;
    private String key;
    private String nick;

    public JCABuilder setUser(String user) {
        this.user = user;
        return this;
    }

    public JCABuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public JCABuilder setNick(String nick) {
        this.nick = nick;
        return this;
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
            
            if(!"success".equals(status)){
                throw new IOException("Cleverbot responded with unexpected status: "+status);
            }
            
            nick = json.getString("nick");
            JCA jca = new JCA(user, key, nick);
            return jca;
        } catch (UnirestException | JSONException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
