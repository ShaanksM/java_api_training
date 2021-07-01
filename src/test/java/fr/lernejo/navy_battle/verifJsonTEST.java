package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class verifJsonTEST {

    @Test
    void GoodStartRequest()
    {
        JSON_Check check = new JSON_Check();
    }

    @Test
    void BadStartRequest()
    {
        JSON_Check check = new JSON_Check();
        try {
            Assertions.assertEquals(false,check.ValidateStartRequest("{\"id\": \"2aca7611-0ae4-49f3-bf63-75bef4769028\",\"url\": 90,\"message\": \"May the best code win\"}"),"A bad request returned");
            Assertions.assertEquals(1, 0, "Number of org.everit.json.schema.ValidationException");
        }
        catch (org.everit.json.schema.ValidationException e) {Assertions.assertEquals(1, 1, "Number of org.everit.json.schema.ValidationException"); }
        try {
            Assertions.assertEquals(false, check.ValidateStartRequest("{\"id\": \"2aca7611-0ae4-49f3-bf63-75bef4769028\",\"message\": \"May the best code win\"}"), "A good bad returned");
            Assertions.assertEquals(1, 0, "Number of org.everit.json.schema.ValidationException");
        }
        catch (org.everit.json.schema.ValidationException e){ Assertions.assertEquals(1, 1, "Number of org.everit.json.schema.ValidationException");}
    }

    @Test
    void BadFireRequest() {
        JSON_Check check = new JSON_Check();
        Game g = new Game(null);
        try {
            Assertions.assertEquals(Game.FireResult.out, check.ValidateFireRequest("{\"consequence\": \"yolo\",\"shipLeft\": true}", g), "A bad request returned");
            Assertions.assertEquals(1, 0, "Number of org.everit.json.schema.ValidationException");
        } catch (Exception e) {
        }
        try {
            Assertions.assertEquals(Game.FireResult.out, check.ValidateFireRequest("{\"shipLeft\": true}", g), "A bad request returned");
            Assertions.assertEquals(1, 0, "Number of org.everit.json.schema.ValidationException");
        }catch (Exception e){

        }
    }

    @Test
    void GoodFireRequest()
    {
        JSON_Check check = new JSON_Check();
        Game g = new Game(null);
        Assertions.assertEquals(Game.FireResult.sunk,check.ValidateFireRequest("{\"consequence\": \"sunk\",\"shipLeft\": true}",g),"A good request returned");
        Assertions.assertEquals(Game.FireResult.hit,check.ValidateFireRequest("{\"consequence\": \"hit\",\"shipLeft\": true}",g),"A good request returned");
        Assertions.assertEquals(Game.FireResult.sunk,check.ValidateFireRequest("{\"consequence\": \"sunk\",\"shipLeft\": false}",g),"A good request returned");
        Assertions.assertEquals(g.ingame[0],false,"Game Should have ended");

    }

}
