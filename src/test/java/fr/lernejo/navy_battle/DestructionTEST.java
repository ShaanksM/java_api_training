package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DestructionTEST {
    @Test
    void MauvaisArgt()
    {
        Destruction a = new Destruction(null);
        try {
            a.Shoot(new int[]{1,2});
            assertEquals(Game.ResultatTir.out,a.Shoot(new int[]{1,2}),"Mauvais Tir");
        }catch (Exception e){}
    }

    @Test
    void FinduJeu() throws IOException {
        Game g = new Game( new ServerMain("1234"));
        g.server.target[0] = "http://localhost:1234";
        Destruction a = new Destruction(g);
        a.Shoot(new int[]{1,1});
    }
}
