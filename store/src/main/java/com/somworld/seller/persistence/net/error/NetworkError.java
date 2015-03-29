package com.somworld.seller.persistence.net.error;

/**
 * Created by somesh.shrivastava on 29/03/15.
 */
public interface NetworkError {

  public interface CODE {

    int NETWORK_TIMEOUT = 509;

    int NETWORK_CONNECTION = 510;

    int AUTHENTICATION_FAIL = 401;

    int SERVER_ERROR = 504;

    int NETWORK_ERROR = 505;

    int NO_3G_OR_WIFI_CONNECTION = 509;

    int PARSE_ERROR = 510;

    int INCORRECT_DATA = 506;

  }

  public interface MESSAGE {
    String NETWORK_TIMEOUT = "Time out when fetching the data.";

    String NETWORK_CONNECTION = "Problem in network connection. Open connection setting?";

    String AUTHENTICATION_FAIL = "Authentication Fail";

    String SERVER_ERROR = "Problem is Server.Please try after some time";

    String NETWORK_ERROR = "Problem while connection.please try again letter";

    String NO_3G_OR_WIFI_CONNECTION = "No Network connection found.Please check your network setting";

    String PARSE_ERROR = "Problem in Server. Please try after some time";

    String INCORRECT_DATA = "Response is not Correct";
  }

}
