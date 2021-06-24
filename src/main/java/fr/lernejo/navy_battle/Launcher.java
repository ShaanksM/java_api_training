package fr.lernejo.navy_battle;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.HeartProject.Requete;
import fr.lernejo.navy_battle.Implement.ContentSrv;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.UUID;
import java.util.concurrent.Executors;

public class Launcher {
    private ContentSrv ls;
    private ContentSrv rs;

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                System.err.println("Usage: Launcher [port] {server_url}");
                System.exit(-1);
            }

            int serverPort = Integer.parseInt(args[0]);
            System.out.println("Starting to listen on port " + serverPort);

            new Launcher().Sserver(serverPort, args.length > 1 ? args[1] : null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Sserver(int port, String connectURL) throws IOException {
        ls = new ContentSrv(
            UUID.randomUUID().toString(),
            "Hehe pret a affronter Mohamad, jeune Padawan ?",
            "http://localhost:" + port
        );

        HttpServer sv = HttpServer.create(new InetSocketAddress(port), 0);
        sv.createContext("/ping", this::handlePing);
        sv.setExecutor(Executors.newSingleThreadExecutor());
        sv.start();
    }

    private void handlePing(HttpExchange exchange) throws IOException {
        String body = "Hellooo :D";
        exchange.sendResponseHeaders(200, body.length());
        try (OutputStream os = exchange.getResponseBody()) { // (1)
            os.write(body.getBytes());
        }
    }
}
