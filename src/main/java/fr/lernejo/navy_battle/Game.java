package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import org.everit.json.schema.internal.RegexFormatValidator;

import java.io.IOException;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;


public class Game {
    final List<List<int[]>> yourboard;
    final boolean[] inGame;
    final IntelligenceArti IntelligenceArti;
    final ServerMain server;
    final Destruction shoot;
    // patternRegex
    final Pattern p = Pattern.compile("^[A-J](10|[1-9])$");

    enum ResultatTir {miss,hit,sunk,out}

    Game(ServerMain server)
    {
        inGame = new boolean[]{false};
        IntelligenceArti = new IntelligenceArti();
        yourboard = IntelligenceArti.GenererBateau();
        this.server = server;
        shoot = new Destruction(this);
    }

    public ResultatTir TirerA(String cell) throws IOException {
        if (!p.matcher(cell).find()) {return ResultatTir.out; }
        int y = (cell.charAt(0) - 'A');
        int x = Integer.parseInt(cell.split("[A-J]")[1]) - 1;
        for (List<int[]> coordship : yourboard) {for (int[] coord : coordship) {
            if (coord[0] == x && coord[1] == y){
                coordship.remove(coord);
                if (coordship.size() == 0){
                    yourboard.remove(coordship);
                    return ResultatTir.sunk;
                }
                return ResultatTir.hit;
        }}}
        return ResultatTir.miss;
    }

    public void RetourDuTir() {
        int[] coord = shoot.TargetCellPourTirer();
        coord[0]++;
        ResultatTir result = shoot.Shoot(coord);
    }
}
