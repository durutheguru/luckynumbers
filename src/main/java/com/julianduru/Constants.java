package com.julianduru;


/**
 * created by julian
 */
public class Constants {


    public static final String API_BASE = "/api/v1";


    public static final String PAYLOAD_JSON_PATH = "$.data";



    public final class Users {

        public static final String LOTTERY_USER = "Lottery User";


        public static final String BACK_OFFICE_USER = "BackOffice User";


        public static final String PARTNER_USER = "Partner User";

    }


    public final class Security {


        public static final String SECRET = "app-secret-key";

        public static final long EXPIRATION_TIME = 86400000;

        public static final String TOKEN_PREFIX = "Bearer ";

        public static final String HEADER_STRING = "Authorization";


    }


    public static class Patterns {

        public static final String PHONE = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";

        public static final String EMAIL = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

        public static final String WEBSITE = "^((https?|ftp|smtp):\\/\\/)?(www.)?[a-z0-9]+\\.[a-z]+(\\/[a-zA-Z0-9#]+\\/?)*$";

    }



}
