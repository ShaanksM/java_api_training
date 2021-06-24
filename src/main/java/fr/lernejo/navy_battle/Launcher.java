package fr.lernejo.navy_battle;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.Implement.ContentSrv;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.UUID;
import java.util.concurrent.Executors;

public class Launcher {
    private ContentSrv localServer;
    private ContentSrv remoteServer;

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                System.err.println("Usage: Launcher [port]");
                System.exit(-1);
            }

            int port = Integer.parseInt(args[0]);
            System.out.println("Starting to listen on http://0.0.0.0:" + port + "/");

            startServer(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startServer(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/ping", Launcher::handlePing);
        server.start();
    }

    private static void handlePing(HttpExchange exchange) throws IOException {
        String body = "Hello";
        exchange.sendResponseHeaders(200, body.length());
        try (OutputStream os = exchange.getResponseBody()) { // (1)
            os.write(body.getBytes());
        }
    }
}
