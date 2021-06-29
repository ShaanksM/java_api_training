package fr.lernejo.navy_battle;

import org.apache.commons.validator.routines.UrlValidator;

import java.io.*;
import java.net.*;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Launcher {

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        ServerMain server = null;
        try {
            server = new ServerMain(args[0]);
            if (args.length > 1) {
                ClientWeb client = new ClientWeb(server, args[1]);
                client.SeConnecterAuServ();
            }
        } catch (IOException | URISyntaxException e) {
            throw e;
        }
    }
}
