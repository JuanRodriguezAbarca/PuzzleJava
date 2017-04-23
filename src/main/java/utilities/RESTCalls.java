package utilities;


import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import objects.JsonStructure;
import objects.PuzzleProperties;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.from;

/**
 * Created by Juan on 19/04/2017.
 */
public class RESTCalls {

    static final Logger infoLog = Logger.getLogger("infoLog");
    static final Logger errorLog = Logger.getLogger("errorLog");

    String token = PuzzleProperties.getToken();
    String url = PuzzleProperties.getUrl();
    String manufacturer = PuzzleProperties.getUriManufacturer();
    String mainTypes = PuzzleProperties.getUriMainTypes();
    String buildDates = PuzzleProperties.getUriBuildDates();


    /**
    Class and method designed to build the calls to the REST APIs, using a switchCase selecting specific call via parameter.
     Returns the Json retrieved in the answer. Try/Catch exception in case the format is not correct
     **/

    public JsonStructure restCall(String restCall, String manufacturerCode, String model){
        JsonStructure jsonStructure;
        String parameters=null;
        baseURI = url;

        switch (restCall){
            case "manufacturers":
                parameters= manufacturer+"?wa_key="+token;
                break;
            case "carTypes":
                parameters = mainTypes+"?wa_key="+token+"&manufacturer="+manufacturerCode;
                break;
            case "buildDates":
                parameters=buildDates+"?wa_key="+token+"&manufacturer="+manufacturerCode+"&main-type="+model;
        }

        infoLog.info("Connecting to: "+url+parameters);

        Response getFirstContact = given().contentType(ContentType.JSON).get(parameters);

        infoLog.info("Response taken: \n"+getFirstContact.asString());

        HashMap myJson = from(getFirstContact.asString()).get("");

        try {
            jsonStructure = new JsonStructure(Integer.parseInt(myJson.get("page").toString()),
                    Integer.parseInt(myJson.get("pageSize").toString()),
                    Integer.parseInt(myJson.get("totalPageCount").toString()),
                    (Map<String, String>) myJson.get("wkda"));
        } catch (NullPointerException e) {
            errorLog.error("REST response not correct for: "+manufacturerCode+"/"+model);
            errorLog.error(getFirstContact.asString());
            errorLog.error("#########################################\n");
            jsonStructure = new JsonStructure(0,0,0,(Map<String, String>) myJson.get("wkda"));
        }
        return jsonStructure;


    }


}
