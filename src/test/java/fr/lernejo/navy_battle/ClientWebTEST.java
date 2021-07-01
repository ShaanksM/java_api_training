package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class ClientWebTEST {
    @Test
    void AccepterClient() throws IOException, URISyntaxException, InterruptedException {
        ClientWeb ci = new ClientWeb(new ServerMain("1237"), "http://localhost:1237");
        ci.SeConnecterAuServ();
    }
}
