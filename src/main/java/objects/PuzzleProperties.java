package objects;

/**
 * Created by Juan on 18/04/2017.
 */
    public class PuzzleProperties {

    private static String url;
    private static String token;
    private static String uriManufacturer;
    private static String uriMainTypes;
    private static String uriBuildDates;
    private static boolean propLoaded=false;


    public PuzzleProperties(String url, String token, String uriManufacturer, String uriMainTypes, String uriBuildDates){

        PuzzleProperties.url = url;
        PuzzleProperties.token = token;
        PuzzleProperties.uriManufacturer = uriManufacturer;
        PuzzleProperties.uriMainTypes = uriMainTypes;
        PuzzleProperties.uriBuildDates = uriBuildDates;
        PuzzleProperties.propLoaded = true;
    }

    public static String getUrl() {
        return url;
    }

    public static String getToken() {
        return token;
    }

    public static String getUriManufacturer() {
        return uriManufacturer;
    }

    public static String getUriMainTypes() {
        return uriMainTypes;
    }

    public static String getUriBuildDates() {
        return uriBuildDates;
    }

    public static boolean getPropLoaded(){
        return propLoaded;
    }


}
