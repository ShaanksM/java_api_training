package fr.lernejo.navy_battle.Implement;

import org.json.JSONObject;
import org.json.JSONException;

public class ContentSrv {
    private final String id; private final String url;
    private final String msg;

    public ContentSrv(String id, String url, String msg) {
        this.id = id; this.msg = msg; this.url = url;
    }
    public String getMessage() {
        return msg;
    }

    public String getId() {
        return id;
    }  // On retourne  l'ID

    public String getUrl() {
        return url;
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
            obt.getString("url"),
            obt.getString("message"),
            obt.getString("id")
        );
    }
}
