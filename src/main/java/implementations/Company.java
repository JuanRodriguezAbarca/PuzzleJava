package implementations;


import org.apache.log4j.Logger;
import utilities.RESTCalls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Juan on 19/04/2017.
 */

/**
 * Specific class designed to implement the company.feature.
 */
public class Company {

    //    static final Logger log = Logger.getLogger(Company.class);
    static final Logger infoLog = Logger.getLogger("infoLog");
    static final Logger errorLog = Logger.getLogger("errorLog");

    RESTCalls restCalls = new RESTCalls();
    static Map<String, String> vehicleCodeMap = null;

    /**
     * This Method call the RESTCalls class sending the specific REST service and method. Returns a Map with the JSon info
     * @param apiCall
     * @param manufacturer
     * @param model
     * @return
     */
    public Map<String, String> getWkdaSets(String apiCall, String manufacturer, String model) {
        Map<String, String> wkdaSet;
        if (apiCall.contentEquals("manufacturers")) {
            if (vehicleCodeMap == null) {
                infoLog.info("RESTcall to " + apiCall);
                wkdaSet = restCalls.restCall(apiCall, manufacturer, model).getWkda();
                vehicleCodeMap = wkdaSet;
                return vehicleCodeMap;
            } else {
                return vehicleCodeMap;
            }
        } else {
            infoLog.info("RESTcall to " + apiCall);
            wkdaSet = restCalls.restCall(apiCall, manufacturer, model).getWkda();
            return wkdaSet;
        }
    }

    /**
     * This method compares the JSon keys to verify there are not null or repetions. Returns a String with the errors found
     * @param restCall
     * @param manufacturer
     * @param model
     * @return
     */
    public String checkRepeatedJasonKeys(String restCall, String manufacturer, String model) {
        infoLog.info("Comparing keys for " + restCall);
        List<String> keyCodes = new ArrayList<>();
        Map<String, String> wkda = getWkdaSets(restCall, manufacturer, model);
        StringBuilder keyCode = new StringBuilder();
        String currentCode;

        for (Map.Entry entry : wkda.entrySet()) {
            currentCode = entry.getKey().toString();
            infoLog.info("Comparing values for key: " + currentCode);
            if (currentCode.contentEquals("") || currentCode == null) {
                keyCode.append("Manufacturer '" + manufacturer + "' and model '" + model + "'");
                keyCode.append(entry.getKey().toString());
                errorLog.error("Manufacturer '" + manufacturer + "' and model '" + model + "' IS EMPTY");

            } else {
                for (int i = 0; i < keyCodes.size() - 1; i++) {
                    if (i == 0) {
                        keyCodes.add(currentCode);
                    } else {
                        if (keyCodes.get(i).equals(currentCode)) {
                            keyCode.append(currentCode);
                            keyCode.append("\n");
                            infoLog.info("Repeated key: " + currentCode);
                        } else
                            keyCodes.add(currentCode);
                    }
                }

            }

        }
        return keyCodes.toString();
    }


    /**
     ** This method compares the JSon values to verify there are not null or repetions. Returns a String with the errors found
     * @param restCall
     * @param manufacturer
     * @param model
     * @return
     */
    public String checkRepeatedJasonValues(String restCall, String manufacturer, String model) {
        infoLog.info("Checking repetition of values in: " + restCall);
        List<String> valueCodes = new ArrayList<>();
        StringBuilder valueCode = new StringBuilder();
        String currentValue;
        for (Map.Entry entry : getWkdaSets(restCall, manufacturer, model).entrySet()) {
            currentValue = entry.getValue().toString();
            infoLog.info("Comparing values for: " + currentValue);
            if (currentValue.contentEquals("") || currentValue == null) {
                valueCode.append("Manufacturer '" + manufacturer + "' and model '" + model + "'");
                valueCode.append(entry.getKey().toString());
                errorLog.error("Manufacturer '" + manufacturer + "' and model '" + model + "' IS EMPTY");
            } else {
                for (int i = 0; i < valueCodes.size() - 1; i++) {

                    if (i == 0) {
                        valueCodes.add(currentValue);
                    } else {
                        if (valueCodes.get(i).equals(currentValue)) {
                            valueCode.append(currentValue);
                            valueCode.append("\n");
                            infoLog.info("Repeated key: " + currentValue);
                            errorLog.error("Manufacturer '" + manufacturer + "' and model '" + model + "Repeated key: " + currentValue);
                        } else {
                            valueCodes.add(currentValue);
                        }
                    }
                }
            }
        }
        return valueCodes.toString();
    }

    /**
     * This method returns the manufacturer code based on manufacturer name
     * @param manufacturer
     * @return
     */
    public String getmanufacturerCode(String manufacturer) {
        String manufacturerCode = "";
        for (Map.Entry entry : getWkdaSets("manufacturers", "", "").entrySet()) {
            if (manufacturer.equals(entry.getValue())) {
                manufacturerCode = entry.getKey().toString();
            }
        }

        infoLog.info("Manufactured code for " + manufacturer + ": " + manufacturerCode);

        return manufacturerCode;
    }

    /**
     * This method checks duplicated JSon values using 'checkRepeatedJasonValues'.
     * Defined explicitly to test the buildDates for each model. Returns string with errors found
     * @param manufacturerCode
     * @return
     */
    public String checkDuplicatedDatesForSpecificModel(String manufacturerCode) {
        String model;
        String modelCode;
        Map<String, String> modelMap;
        modelMap = getWkdaSets("carTypes", manufacturerCode, "");
        StringBuilder failBuilder = new StringBuilder();
        for (Map.Entry car : modelMap.entrySet()) {
            model = car.getValue().toString();
            modelCode = car.getKey().toString();
            infoLog.info("Current vehicle: " + manufacturerCode + ":" + model);
            infoLog.info("Current vehicleCode: " + manufacturerCode + ":" + modelCode);
            failBuilder.append(checkRepeatedJasonValues("buildDates", manufacturerCode, model));
        }
        return failBuilder.toString();
    }


}
