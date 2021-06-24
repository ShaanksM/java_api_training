package fr.lernejo.navy_battle.HeartProject;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import org.json.JSONException;
import org.json.JSONObject;

public class Requete {
    private final HttpExchange echng;

    public Requete(HttpExchange exchange) {
        this.echng = exchange;
    }

    public String readToString() throws IOException {
        return new String(this.echng.getRequestBody().readAllBytes());
    }

    public JSONObject getJSONObject() throws IOException {
        try {
            return new JSONObject(readToString());
        } catch (JSONException e) {
            sendString(400, e.toString());
            throw new JSONException(e);
        }
    }

    public void sendString(int status, String test) throws IOException{
        byte[] bytes = test.getBytes();
        echng.sendResponseHeaders(status, bytes.length);

        try (OutputStream os = echng.getResponseBody()) { // (1)
            os.write(bytes);
        }
        echng.close();
    }

    public void sendJSON(int status, JSONObject object) throws IOException {
        echng.getResponseHeaders().set("Content-type", "application/json");
        sendString(status, object.toString());
    }
}
