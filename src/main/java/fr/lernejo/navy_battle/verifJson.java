package fr.lernejo.navy_battle;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class verifJson {
    final JSONObject rawstartrequest = new JSONObject(new JSONTokener("{\"$schema\": \"http://json-schema.org/draft-07/schema\",\"type\": \"object\",\"properties\": {\"id\": {\"type\": \"string\"},\"url\": {\"type\": \"string\"},\"message\": {\"type\": \"string\"}},\"required\": [\"id\", \"url\",\"message\"]}"));
    final Schema startrequestschema = SchemaLoader.load(rawstartrequest);

    final JSONObject rawfirerequest = new JSONObject(new JSONTokener("{\"$schema\": \"http://json-schema.org/draft-07/schema\",\"type\": \"object\",\"properties\": {\"consequence\": {\"type\": \"string\",\"enum\": [\"miss\", \"hit\", \"sunk\"]},\"shipLeft\": {\"type\": \"boolean\"}} ,\"required\": [\"consequence\",\"shipLeft\"]}"));
    final Schema firerequestschema = SchemaLoader.load(rawfirerequest);

    public String ValidateStartRequest(String Request)
    {
        JSONObject obj = new JSONObject(Request);
        startrequestschema.validate(obj);
        return (String) obj.get("url");
    }

    public Game.ResultatTir ValidateFireRequest(String Request, Game g)
    {
        JSONObject obj = new JSONObject(Request);
        firerequestschema.validate(obj);
        if (!(boolean) obj.get("shipLeft")) {
            g.inGame[0] = false;
        }
        return (Game.ResultatTir.valueOf((String)obj.get("consequence")));
    }
}
