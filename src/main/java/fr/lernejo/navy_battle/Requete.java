package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Requete {

    final ServerMain server;
    final verifJson jsck;

    Requete(ServerMain s){
        server = s;
        jsck = new verifJson();
    }


     String GetBodyRequest(HttpExchange exchange) throws IOException {
        InputStreamReader isr =  new InputStreamReader(exchange.getRequestBody(),"utf-8");
        BufferedReader br = new BufferedReader(isr);
        int b;
        StringBuilder buf = new StringBuilder(512);
        while ((b = br.read()) != -1) {
            buf.append((char) b);
        }
        br.close();
        isr.close();
        return buf.toString();
    }

    public void Feu(HttpExchange exchange, boolean test) throws IOException {
        String cell = "";
        try {
            cell = (String) exchange.getRequestURI().getQuery().split("cell=")[1];
            Game.ResultatTir f = server.Game.TirerA(cell);
            String bodyresponse = String.format("{\"consequence\": \"%s\",\"shipLeft\": %s}", f.toString(), (server.Game.yourboard.size() > 0) && (server.Game.inGame[0]));
            if (!test)
                exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(202, bodyresponse.length());
            try (
                OutputStream os = exchange.getResponseBody()) { // (1)
                os.write(bodyresponse.getBytes());
            }
            server.Game.RetourDuTir();
        }
        catch (Exception e) {server.GenererHTMLmsg(exchange, 400);}
    }

    public void StartHandler(HttpExchange exchange, boolean test) throws IOException, InterruptedException {
        try {
            String body = GetBodyRequest(exchange);
            String serverurl = jsck.ValidateStartRequest(body);
            server.target[0] = serverurl;
        } catch (Exception e) { server.GenererHTMLmsg(exchange, 400); }
        String bodyresponse = String.format("{\"id\": \"%s\",\"url\": \"%s\",\"message\": \"%s\"}", server.serverID, server.url, "Cat will prevIntelligenceArtil");
        exchange.sendResponseHeaders(202, bodyresponse.length());
        if (!test)
            exchange.getResponseHeaders().add("Content-Type", "application/json");
        try (
            OutputStream os = exchange.getResponseBody()) { // (1)
            os.write(bodyresponse.getBytes());
        }
        server.Game.inGame[0] = true;
        server.Game.RetourDuTir();
    }
}
