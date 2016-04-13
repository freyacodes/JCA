package frederikam.jca;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.RequestBodyEntity;
import org.json.JSONException;
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

    public String getResponse(String query) {
        String status = "";
        try {
            JSONObject jsonOut = new JSONObject();
            jsonOut.put("user", user)
                    .put("key", key)
                    .put("nick", nick)
                    .put("text", query);

            RequestBodyEntity post = Unirest.post("https://cleverbot.io/1.0/ask").header("Content-Type", "application/json")
                    .body(jsonOut.toString());

            JSONObject json = post.asJson().getBody().getObject();
            status = json.getString("status");
            String response = json.getString("response");
            return response;
        } catch (JSONException | UnirestException ex) {
            throw new RuntimeException(status,ex);
        }
    }

}