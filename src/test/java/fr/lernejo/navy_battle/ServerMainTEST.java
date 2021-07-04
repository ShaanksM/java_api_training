package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

class ServerMainTEST {


    static ServerMain StartTestServer(String port) throws Exception {
        try {
            return new ServerMain(port);
        }catch (IOException e) {
            Assertions.assertEquals(0,1,"Number of exception");
            throw new Exception("Something went wrong with the test server");
        }
    }

    static void DestroyTestServer(ServerMain server)
    {
        if (server == null)
            return;
        server.server.stop(0);
    }

    @Test
    void PingTest()  {
        ServerMain server = null;
        try {
            server = StartTestServer("9877");
        } catch (Exception e) {
            Assertions.assertEquals(0,1,"Number of exception on server");
        }
        try {
            HttpClient cli =HttpClient.newHttpClient();
            HttpRequest requetePost = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9877/ping"))
                .setHeader("Accept", "application/json")
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("Ping"))
                .build();
            HttpResponse<String> response = cli.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertEquals(200,response.statusCode(),"Status code 200 was expected for a POST on /ping");
            Assertions.assertEquals("OK",response.body(),"Ok body was expected for a POST  on /ping");
        } catch (URISyntaxException | InterruptedException | IOException e) {
            Assertions.assertEquals(0,1,"Number of exception for POST on /ping");
        }
        DestroyTestServer(server);
    }

    @Test
    void TrashTest()  {
        ServerMain server = null;
        try {
            server = StartTestServer("9878");
        } catch (Exception e) {
            Assertions.assertEquals(0,1,"Number of exception");
        }
        try {
            HttpClient cli =HttpClient.newHttpClient();
            HttpRequest requetePost = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9878/yolo"))
                .setHeader("Accept", "application/json")
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("Ping"))
                .build();
            HttpResponse<String> response = cli.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertEquals(404,response.statusCode(),"Status code 404 was expected for a POST on /yolo");
        } catch (URISyntaxException | InterruptedException | IOException e) {
            Assertions.assertEquals(0,1,"Number of exception");
        }

        try {
            HttpClient cli =HttpClient.newHttpClient();
            HttpRequest requetePost = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9878/api/game/fire"))
                .setHeader("Accept", "application/json")
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("Ping"))
                .build();
            HttpResponse<String> response = cli.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertEquals(404,response.statusCode(),"Status code 404 was expected for a POST on /yolo");
        } catch (URISyntaxException | InterruptedException | IOException e) {
            Assertions.assertEquals(0,1,"Number of exception");
        }

        try {
            HttpClient cli =HttpClient.newHttpClient();
            HttpRequest requeteget = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9878/api/game/fire"))
                .setHeader("Accept", "application/json")
                .setHeader("Content-Type", "application/json")
                .GET()
                .build();
            HttpResponse<String> response = cli.send(requeteget, HttpResponse.BodyHandlers.ofString());
            Assertions.assertEquals(400,response.statusCode(),"Status code 404 was expected for a POST on /yolo");
        } catch (URISyntaxException | InterruptedException | IOException e) {
            Assertions.assertEquals(0,1,"Number of exception");
        }

        try {
            HttpClient cli =HttpClient.newHttpClient();
            HttpRequest requetePost = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9878/api/game/start"))
                .setHeader("Accept", "application/json")
                .setHeader("Content-Type", "application/json")
                .GET()
                .build();
            HttpResponse<String> response = cli.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertEquals(404,response.statusCode(),"Status code 404 was expected for get on /api/game/start");
        } catch (URISyntaxException | InterruptedException | IOException e) {
            Assertions.assertEquals(0,1,"Number of exception");
        }
        server.Game.inGame[0] = true;
        try {
            HttpClient cli =HttpClient.newHttpClient();
            HttpRequest requetePost = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9878/api/game/start"))
                .setHeader("Accept", "application/json")
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("Ping"))
                .build();
            HttpResponse<String> response = cli.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertEquals(404,response.statusCode(),"Status code 404 was expected for get on /api/game/start");
        } catch (URISyntaxException | InterruptedException | IOException e) {
            Assertions.assertEquals(0,1,"Number of exception");
        }
        DestroyTestServer(server);
    }

}
