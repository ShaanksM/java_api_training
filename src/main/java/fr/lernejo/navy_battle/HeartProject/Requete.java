package fr.lernejo.navy_battle.HeartProject;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import org.json.JSONException;
import org.json.JSONObject;

public class Requete {

    private final HttpExchange exchange;

    public Requete(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public String rdToString() throws IOException {
        return new String(this.exchange.getRequestBody().readAllBytes());
    }

    public JSONObject getJSONObject() throws IOException {
        try {
            return new JSONObject(rdToString());
        } catch (JSONException e) {
            EnvoieStrng(400, e.toString());
            throw new JSONException(e);
        }
    }

    public String ParametreDeRequete(String name) throws IOException {
        for (var key : exchange.getRequestURI().getQuery().split("&")) {
            var split = key.split("=");

            if (split.length == 2 && split[0].equals(name))
                return split[1];
        }

        throw new IOException("Parametre " + name + " URL manquant !");
    }

    public void EnvoieStrng(int status, String test) throws IOException {
        byte[] bytes = test.getBytes();
        exchange.sendResponseHeaders(status, bytes.length);

        try (OutputStream os = exchange.getResponseBody()) { // (1)
            os.write(bytes);
        }
        exchange.close();
    }

    public void sendJSON(int status, JSONObject object) throws IOException {
        exchange.getResponseHeaders().set("TypeContenu", "application/json");
        EnvoieStrng(status, object.toString());
    }
}
