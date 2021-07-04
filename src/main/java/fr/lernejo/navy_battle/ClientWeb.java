package fr.lernejo.navy_battle;

import org.apache.commons.validator.routines.UrlValidator;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ClientWeb {

    final ServerMain server;
    final String url;

    ClientWeb(ServerMain s, String url) throws URISyntaxException {
        server = s;
        UrlValidator urlValidator = new UrlValidator();
        urlValidator.isValid(url);
        this.url = url;
    }

    public boolean SeConnecterAuServ() throws URISyntaxException, IOException, InterruptedException {
        try {
            HttpClient cli = HttpClient.newHttpClient();
            HttpRequest requetePost = HttpRequest.newBuilder().uri(new URI(url + "/api/game/start")).setHeader("Accept", "application/json")
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(String.format("{\"id\": \"%s\",\"url\": \"%s\",\"message\": \"%s\"}",server.serverID,server.url,"I love cat and u ?")))
                .build();
            HttpResponse<String> response = cli.send(requetePost, HttpResponse.BodyHandlers.ofString());
            server.target[0] = url;
            server.Game.inGame[0] = true;
        } catch (URISyntaxException | InterruptedException | IOException e) {throw e;}
        return true;
    }

}
