package fr.lernejo.navy_battle;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Destruction {
    final Game game;

    final List<int[]> allshots =new ArrayList<>();

    public Destruction(Game g)
    {
        game = g;
        for (int i = 0; i < 10; i++) { for (int j = 0; j < 10; j++) {
            allshots.add(new int[]{i,j});
        }}
    }

    public int[] TargetCellPourTirer(){
        return allshots.remove(game.IntelligenceArti.rng.nextInt(allshots.size()));
    }

    public Game.ResultatTir Shoot(int[] coord) {
        String cell = String.format("%s%s", (char) (coord[1] + 'A'), coord[0] + 1);
        if (!game.inGame[0])
            return EndGame();
        try {
            String url = String.format("%s/api/game/fire?cell=%s", game.server.target[0], cell);
            HttpClient cli = HttpClient.newHttpClient();
            HttpRequest requetefire = HttpRequest.newBuilder()
                .uri(new URI(url)).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").GET()
                .build();
            HttpResponse<String> response = cli.send(requetefire, HttpResponse.BodyHandlers.ofString());
            return game.server.rqtHdlr.jsck.ValidateFireRequest(response.body(), game);
        } catch (Exception e) {return Game.ResultatTir.out; }
    }

    public Game.ResultatTir  EndGame(){
        game.inGame[0] = false;
        return Game.ResultatTir.out;
    }
}
