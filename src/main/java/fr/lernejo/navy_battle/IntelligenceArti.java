package fr.lernejo.navy_battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IntelligenceArti {

    final int[] allship = new int[]{5,4,3,3,2};
    final Random rng;
    IntelligenceArti()
    {
        rng = new Random();
    }

    public boolean VerificationPlacement(List<List<int[]>> ships, int x, int y, int orientation,int shipsize) {
        if (orientation == 0){
            if (x - shipsize < 0){return false;}
            for (List<int[]> coordship: ships) { for (int[] coord : coordship) { for (int i = 0; i < shipsize; i++) {
                if (coord[0] == (x - i) && coord[1] == y)
                    return false;
            }}}
        }
        else {
            if (y - shipsize < 0) {return false;}
            for (List<int[]> coordship : ships) {for (int[] coord : coordship) { for (int i = 0; i < shipsize; i++) {
                if (coord[0] == x && coord[1] == (y - i))
                    return false;
            }}}
        }
        return true;
    }

    public void PlacementBateau(List<List<int[]>> ships, int x, int y, int orientation,int shipsize) {
        List<int[]> shipcoord = new ArrayList<>();
        if (orientation == 0){
            for (int i = 0; i < shipsize; i++)
                shipcoord.add(new int[]{x - i, y});
        }
        else{
            for (int i = 0; i < shipsize; i++)
                shipcoord.add(new int[]{x , y - i});
        }
        ships.add(shipcoord);
    }

    public List<List<int[]>> GenererBateau()
    {
        List<List<int[]>> toreturn = new ArrayList<>();
        for (int sizeship : allship) {
            boolean correctplacement = false;
            while (!correctplacement) {
                int x = rng.nextInt(10);
                int y = rng.nextInt(10);
                int orientation = rng.nextInt(2);
                correctplacement = VerificationPlacement(toreturn,x,y,orientation,sizeship);
                if (correctplacement) { PlacementBateau(toreturn,x,y,orientation,sizeship); }
            }
        }
        return toreturn;
    };
}
