package whitbread.quintas.pt.whitbread.helpers;

public class SystemConstants {

    /**
     * Foursquare api client info
     * */
    public static String FOURSQUARE_CLIENT_ID = "LDTJYIPITIJ4SLJOK1I0R5SSOX0KQK0SNRW1GOCS1TW4GORK";
    public static String FOURSQUARE_CLIENT_SECRET = "ZXROBN3Z2T2IF3BEQF5IM4NZ5GEW4IKNRZKQUE0LX5SEQSFY";

    /**
     * Foursquare api endpoints
     * */
    public static final String ENDPOINT_FOURSQUARE_VENUE_SEARCH = "https://api.foursquare.com/v2/venues/search?";
    public static final String ENDPOINT_VERSION = "&v=20130815";
    public static final String ENDPOINT_FOURSQUARE_CLIENT_ID = "client_id=";
    public static final String ENDPOINT_FOURSQUARE_CLIENT_SECRET = "&client_secret=";
    public static final String ENDPOINT_FOURSQUARE_LL = "&ll=";
    public static final String ENDPOINT_FOURSQUARE_NEAR = "&near=";

    /**
     * Connection constants
     * */
    public static int REMOTE_EXECUTOR_TIMEOUT = 30000;
    public static final int REMOTE_EXECUTER_OK_CODE = 200;

    /**
     * Handlers
     * */
    public static final int HANDLER_SEARCH = 1;
    public static final String HANDLER_SEARCH_LOCATION = "search_location";

    /**
     * Logs
     * */
    public static final String LOG_TAG = "whitbread app";
    public static final String LOG_BUFFER_EXCEPTION = "Buffer Exception: ";

}
