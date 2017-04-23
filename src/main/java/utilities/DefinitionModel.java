package utilities;

import objects.PuzzleProperties;
import org.apache.log4j.Logger;

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

    protected String errorFault="";
    protected boolean flagFail=false;

}


