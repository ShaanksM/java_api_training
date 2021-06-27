package fr.lernejo.navy_battle.Implement;

import org.json.JSONObject;
import org.json.JSONException;

public class ContentSrv {
    private final String id; private final String url;
    private final String msg;

    public ContentSrv(String id, String url, String msg) {
        this.id = id; this.url = url; this.msg = msg;
    }
    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getMessage() {

        return msg;
    }

    public JSONObject toJSON(){
        JSONObject ob = new JSONObject();
        ob.put("id", id);
        ob.put("url", url);
        ob.put("message", msg);
        return ob;
    }

    public static ContentSrv fromJSON(JSONObject obt) throws JSONException {
        return new ContentSrv(
            obt.getString("id"),
            obt.getString("url"),
            obt.getString("message")

        );
    }

    public ContentSrv withURL(String url) {
        return new ContentSrv(
            this.id,
            url,
            this.msg
        );
    }
}
