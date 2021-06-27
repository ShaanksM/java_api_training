package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import fr.lernejo.navy_battle.HeartProject.Requete;
import fr.lernejo.navy_battle.Implement.Carte;
import fr.lernejo.navy_battle.Implement.Support;
import fr.lernejo.navy_battle.Implement.ContentSrv;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;
import java.util.concurrent.Executors;

public class Launcher {

    private final Support<ContentSrv> Serveur_Local = new Support<>();
    private final HttpClient client = HttpClient.newHttpClient();
    private final Support<Carte> map = new Support<>();
    private final Support<ContentSrv> Serveur_Distant = new Support<>();

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                System.err.println(" [port] {server_url}");
                System.exit(-1);
            }
            int srvprt = Integer.parseInt(args[0]);
            System.out.println("Ecoute sur le port " + srvprt);
            new Launcher().Depart_Du_Serveur(srvprt, args.length > 1 ? args[1] : null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Depart_Du_Serveur(int port, String connectURL) throws IOException {
        Serveur_Local.set(new ContentSrv(
            UUID.randomUUID().toString(),
            "http://localhost:" + port,
            "Hehe pret à affronter Mohamad, jeune Padawan ?"
        ));
        if (connectURL != null)
            new Thread(() -> this.rqteS(connectURL)).start();
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.setExecutor(Executors.newSingleThreadExecutor());
        server.createContext("/api/game/start", s -> sgm(new Requete(s)));
        server.createContext("/ping", this::hdlpng);
        server.start();
    }

    private void hdlpng(HttpExchange exchange) throws IOException {
        String body = "Ping : OK !";
        exchange.sendResponseHeaders(202, body.length());
        try (OutputStream os = exchange.getResponseBody()) { // (1)
            os.write(body.getBytes());
        }
    }

    public void sgm(Requete handler) throws IOException {
        try {
            if (Serveur_Distant.isNotCASEVIDE()) {
                handler.sndstrng(400, "Desole, j'ai deja un adversaire à tordre !");
                return;
            }

            Serveur_Distant.set(ContentSrv.fromJSON(handler.getJSONObject()));
            map.set(new Carte());
            System.out.println("Tu vas affronter : " + Serveur_Distant.get().getUrl());

            handler.sendJSON(202, Serveur_Local.get().toJSON());
        } catch (Exception e) {
            e.printStackTrace();
            handler.sndstrng(400, e.getMessage());
        }
    }

    public JSONObject sndhtpr(String url, JSONObject obj) throws IOException, InterruptedException {
        HttpRequest requetePost = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .setHeader("Accepter ", "application/json")
            .setHeader("Contenu", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(obj.toString()))
            .build();
        var response = client.send(requetePost, HttpResponse.BodyHandlers.ofString());
        return new JSONObject(response.body());
    }

    public void rqteS(String server) {
        try {
            map.set(new Carte());
            var response = sndhtpr(server + "/api/game/start", this.Serveur_Local.get().toJSON());

            this.Serveur_Distant.set(ContentSrv.fromJSON(response));

            System.out.println("Tu vas affronter contre :  " + Serveur_Distant.get().getUrl());

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Mince .. Il se peut qu'il y a un ( petit ) probleme ");
        }
    }
}
