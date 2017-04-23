package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Juan on 22/04/2017.
 */

/**
 * Super Class for the DefintiionStep Classes to keep track the fails and load the project properties
 */
public class DefinitionModel {

    static{System.setProperty("log.timestamp",
            new SimpleDateFormat("yyyy-MM-dd-HHmmss").format(new Date()));}

    static{MyJsonReader.loadPuzzleProperties();}

    protected StringBuilder errorString = new StringBuilder();
    protected boolean flagFail=false;

}


