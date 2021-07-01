package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    void ShotOutofBound() throws IOException {
        Game g = new Game(null);
        Assertions.assertEquals(Game.ResultatTir.out,g.TirerA("Shanks"),"Le tir est hors champs/Bateau");
        Assertions.assertEquals(Game.ResultatTir.out,g.TirerA("Z9"),"Le tir est hors champs/Bateau");
        Assertions.assertEquals(Game.ResultatTir.out,g.TirerA("A91"),"Le tir est hors champs/Bateau");
        Assertions.assertEquals(Game.ResultatTir.out,g.TirerA("A0"),"Le tir est hors champs/Bateau");
        Assertions.assertEquals(Game.ResultatTir.out,g.TirerA("b11"),"Le tir est hors champs/Bateau");
    }

    @Test
    void RetourTir() throws IOException {
        ServerMain s = new ServerMain ("1235");
        Game g = new Game(s);
        g.RetourDuTir();
    }
    @Test
    void BonneVise() throws IOException {
        boolean stop = false;
        Game g = new Game(null);
        int b= 0;
        while (b < 100) {
            int[] coord = g.yourboard.get(0).get(0);
            if (g.yourboard.get(0).size() == 1)
                Assertions.assertEquals(Game.ResultatTir.sunk, g.TirerA(String.format("%s%s", (char) (coord[1] + 'A'), coord[0] + 1)), "Tir coule");
            else
                Assertions.assertEquals(Game.ResultatTir.hit, g.TirerA(String.format("%s%s", (char) (coord[1] + 'A'), coord[0] + 1)), "Le coup fut un tir");
            if (g.yourboard.size() == 0)
                break;
            b++;
        }
        for (int x = 1; x < 11; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                Assertions.assertEquals(Game.ResultatTir.miss, g.TirerA(String.format("%s%s", (char) (y + 'A'), x )), "Rate, dommage");
            }
        }
    }
}
