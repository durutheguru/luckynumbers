package com.omarze;


/**
 * created by julian
 */
public class Constants {

    public final static String API_V1_BASE = "/api/v1";


    public final static String PAYLOAD_JSON_PATH = "$.data";


    public final class Users {

        public final static String LOTTERY_USER = "Lottery User";


        public final static String BACKOFFICE_USER = "BackOffice User";


        public final static String PARTNER_USER = "Partner User";

    }


    public final class Security {


        public static final String SECRET = "app-secret-key";

        public static final long EXPIRATION_TIME = 86400000;

        public static final String TOKEN_PREFIX = "Bearer ";

        public static final String HEADER_STRING = "Authorization";


    }


    public static class Patterns {

        public final static String PHONE = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";

        public final static String EMAIL = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

        public final static String WEBSITE = "^((https?|ftp|smtp):\\/\\/)?(www.)?[a-z0-9]+\\.[a-z]+(\\/[a-zA-Z0-9#]+\\/?)*$";

    }


}
