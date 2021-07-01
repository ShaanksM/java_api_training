package fr.lernejo.navy_battle.Implement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Random;

public class Carte {
    private final Integer[] BOATS = {5, 4, 3, 3, 2};
    private final gc[][] map = new gc[10][10];

    public Carte() {
        for (gc[] gcs : map) { Arrays.fill(gcs, gc.CASEVIDE);  }

        ConstruCarte();
        printMap();
    }

    public int getHeight() {
        return map[0].length;
    }

    public int getWidth() {
        return map.length;
    }

    private void ConstruCarte() {
        var random = new Random();
        var boats = new ArrayList<>(Arrays.asList(BOATS));
        Collections.shuffle(boats);
        while (!boats.isEmpty()) {
            int boat = boats.get(0);
            int x = Math.abs(random.nextInt()) % getWidth();
            int y = Math.abs(random.nextInt()) % getHeight();
            var orientation = random.nextBoolean() ? orientacion.HORIZONTAL : orientacion.HORIZONTAL.VERTICAL;
            if (!direction(boat, x, y, orientation))
                continue;
            AjoutBateau(boat, x, y, orientation);
            boats.remove(0);
        }
    }

    private boolean direction(int length, int x, int y, orientacion orientation) {
        if (x >= getWidth() || y >= getHeight() || Cellule(x, y) != gc.CASEVIDE)
            return false;

        if (length == 0)
            return true;

        return switch (orientation) {
            case HORIZONTAL -> direction(length - 1, x + 1, y, orientation);
            case VERTICAL -> direction(length - 1, x, y + 1, orientation);
        };
    }

    public gc Cellule(int x, int y) {
        if (x >= 10 || y >= 10)
            throw new RuntimeException("Coordonnée Invalide :/ ");

        return map[x][y];
    }


    public void AjoutBateau(int length, int x, int y, orientacion orientation) {
        while (length > 0) {
            if (Cellule(x, y) != gc.CASEVIDE)
                throw new RuntimeException("Argh.. Impossible de mettre un bateau ici (" + x + ";" + y + ") : La " +
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
