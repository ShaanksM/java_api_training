package fr.lernejo.navy_battle.HeartProject;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import org.json.JSONException;
import org.json.JSONObject;

public class Requete {
    private final HttpExchange echng;

    public void sndstrng(int status, String test) throws IOException{
        byte[] bytes = test.getBytes();
        echng.sendResponseHeaders(status, bytes.length);

        try (OutputStream os = echng.getResponseBody()) { os.write(bytes);
        }
        echng.close();
    }

    public Requete(HttpExchange exchange) {
        this.echng = exchange;
    }

    public String rdtostrg() throws IOException {
        return new String( this.echng.getRequestBody().readAllBytes());
    }

    public JSONObject getJSONObject() throws IOException {
        try { return new JSONObject(rdtostrg()); }

        catch (JSONException f) {
            sndstrng(400, f.toString());
            throw new JSONException(f);
        }
    }

    public void sendJSON(int status, JSONObject object) throws IOException {
        echng.getResponseHeaders().set("Contenu", "application - json");
        sndstrng(status, object.toString());
    }
}
