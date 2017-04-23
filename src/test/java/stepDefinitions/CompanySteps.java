package stepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import implementations.Company;
import static org.junit.Assert.*;
import utilities.DefinitionModel;
import utilities.MyJsonReader;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import objects.PuzzleProperties;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created by Juan on 18/04/2017.
 */
public class CompanySteps extends DefinitionModel{

    static final Logger infoLog = Logger.getLogger("infoLog");
    static final Logger errorLog = Logger.getLogger("errorLog");

    Company company = new Company();


    private String manufacturerCode="";
    private String model;
    private Map<String,String> wkdaCars;


    @Before
    public void gettingProperties() {
        if (PuzzleProperties.getPropLoaded()) {
            infoLog.info("Project properties already loaded");
        } else {

            MyJsonReader.loadPuzzleProperties();
            infoLog.info("Puzzle Properties loaded!");
        }
    }

    @Before
    public void resetingTestValues(){
        infoLog.info("Reseting test values");
        errorString = new StringBuilder();
        flagFail=false;

    }


    @Given("^I have a list of manufacturers$")
    public void iHaveAListOfManufacturers(){
        wkdaCars =company.getWkdaSets("manufacturers",manufacturerCode,model);
    }

    @When("^I compare manufacturer codes$")
    public void iCompareManufacturerCodes(){
        errorString.append(company.checkRepeatedJasonKeys("manufacturers",manufacturerCode,model));
        if(!errorString.toString().equals("")){
            flagFail=true;
        }
    }

    @Then("^there are no repeated manufacturer codes$")
    public void thereAreNoRepeatedManufacturerCodes(){
        assertFalse("Repeated manufacturers: "+ errorString.toString(),flagFail);
    }

    @Given("^I choose a manufacturer '([^\"]*)'$")
    public void iChooseAManufacturer(String manufacturer) throws Throwable {

        manufacturerCode = company.getmanufacturerCode(manufacturer);

    }


    @When("^I select the different vehicles$")
    public void iSelectTheDifferentVehicles(){
        wkdaCars = company.getWkdaSets("manufacturers",manufacturerCode,model);
        infoLog.info("wkdaCars\n"+wkdaCars.toString());
    }

    @Then("^I verify there are no duplicated or null values$")
    public void iVerifyThereAreNoDuplicatedOrNullValues(){
        errorString.append(company.checkRepeatedJasonValues("carTypes",manufacturerCode,model));
        errorString.append(company.checkRepeatedJasonKeys("carTypes",manufacturerCode,model));
        errorString.append(company.checkDuplicatedDatesForSpecificModel(manufacturerCode));

        if(!errorString.toString().equals("")){
            flagFail=true;
            errorLog.error("Duplicated values and keys in models and dates: "+ errorString.toString());
        }

        assertFalse("Duplicated values and keys in models and dates: "+ errorString.toString(),flagFail);
    }

}
