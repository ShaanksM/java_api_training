package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntelligenceArtiTEST {


    @Test
    void TestBonPlacement()
    {
        IntelligenceArti test = new IntelligenceArti();
        List<List<int[]>> ships =new ArrayList<>();
        List<int[]> temp = new ArrayList<>();
        temp.add(new int[]{0,0});
        ships.add(temp);
        Assertions.assertEquals(true,test.VerificationPlacement(ships,5,5,0,2),"Navire peut etre place");
        Assertions.assertEquals(true,test.VerificationPlacement(ships,5,5,1,2),"Navire peut etre place");
        Assertions.assertEquals(true,test.VerificationPlacement(ships,0,3,1,2),"Navire peut etre place");
        Assertions.assertEquals(true,test.VerificationPlacement(ships,3,0,0,2),"Navire peut etre place");
        Assertions.assertEquals(false,test.VerificationPlacement(ships,2,0,1,2),"Navire peut pas etre place");
        Assertions.assertEquals(false,test.VerificationPlacement(ships,0,2,0,2),"Navire peut pas etre place");
        Assertions.assertEquals(false,test.VerificationPlacement(ships,0,1,1,2),"Navire peut pas etre place");
        Assertions.assertEquals(false,test.VerificationPlacement(ships,1,0,0,2),"Navire peut pas etre place");
        Assertions.assertEquals(false,test.VerificationPlacement(ships,0,0,1,1),"Navire peut pas etre place");
        Assertions.assertEquals(false,test.VerificationPlacement(ships,0,0,0,1),"Navire peut pas etre place");
    }

    @Test
    void TestPlacement()
    {
        IntelligenceArti test = new IntelligenceArti();
        List<List<int[]>> ships =new ArrayList<>();
        test.PlacementBateau(ships,2,0,1,2);
        test.PlacementBateau(ships,0,2,0,2);
    }

    void AfficherNavires(List<List<int[]>> ships)
    {
        int[][] Bateau = new int[10][10];
        for (List<int[]> coordship : ships) {
            for (int[] coord : coordship) {
                Bateau[coord[1]][coord[0]] = coordship.size();
            }
        }
        System.out.println();
        int l = 0;
        for (int i = 0 ; i < 10; i++)
        {
            for (int k = 0 ; k < 10; k++)
            {
                if (Bateau[i][k] != 0)
                    l++;
                System.out.print(Bateau[i][k]);
            }
            System.out.println();
        }
        System.out.println();
        Assertions.assertEquals(17,l,"NombreCellule Remplis");
    }

    @Test
    void GenererBateau()
    {
        IntelligenceArti test = new IntelligenceArti();
        for (int i = 0; i < 25; i++) {
            int k = 0;
            List<List<int[]>> ships = test.GenererBateau();
            for (List<int[]> coordship : ships) {
                for (int[] coord : coordship) {
                    k++;
                }
            }
            Assertions.assertEquals(17,k,"Nombre Cellule Remplis");
        }
    }
}
