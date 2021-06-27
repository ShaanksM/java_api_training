package fr.lernejo.navy_battle.Implement;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Carte {
    private final gc[][] map = new gc[15][15];

    public Carte() {
        for (gc[] gcs : map) { Arrays.fill(gcs, gc.CASEVIDE);  }

        printMap();
    }

    public gc getCell(int x, int y) {
        if (x >= 10 || y >= 10)
            throw new RuntimeException("Coordonnée Invalide :/ ");

        return map[x][y];
    }


    public void addBoat(int length, int x, int y, orientacion orientation) {
        while (length > 0) {
            if (getCell(x, y) != gc.CASEVIDE)
                throw new RuntimeException("Argh.. Impossible de mettreu un bateau ici (" + x + ";" + y + ") : La " +
                    "cellule est occupé !");

            map[x][y] = gc.BATEAU;
            length--;

            switch (orientation) {
                case VERTICAL -> y++;
                case HORIZONTAL -> x++;
            }
        }
    }

    public void printMap() {
        System.out.println(" .... ");
        for (gc[] row : map) {
            System.out.println(Arrays.stream(row).map(gc::getltr).collect(Collectors.joining(" ")));
        }
        System.out.println(" .... ");
    }
}
