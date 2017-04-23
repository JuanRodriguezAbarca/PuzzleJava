package utilities;

import objects.PuzzleProperties;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Juan on 18/04/2017.
 */

/**
 * Class and method design to fill the properties object with the info contained in the JSON file with context parameters
 */
public class MyJsonReader {

    final static Logger infoLog = Logger.getLogger("infoLog");

    public static PuzzleProperties loadPuzzleProperties(){
        PuzzleProperties puzzleProperties = null;
        String propertiesLocation = System.getProperty("myProperties");

        infoLog.info("Loading properties file...\n"+propertiesLocation);

        try{

            JSONParser parser = new JSONParser();
            JSONObject properties = (JSONObject) parser.parse(new FileReader(propertiesLocation));

            puzzleProperties= new PuzzleProperties(properties.get("url").toString(),properties.get("token").toString(),
                    properties.get("uriManufacturer").toString(),properties.get("uriMainTypes").toString(),
                    properties.get("uriBuiltDates").toString());

        } catch (IOException | ParseException e){
            e.printStackTrace();
        }

        return puzzleProperties;
    }

//    public JsonStructure jsonToJsonStructure(JSONObject restResponse){
//
//
//
//    }

}
