package com.vnpt.hddtcustomer.constants;


public class InvRestURIConstants {
    private static final String DOMAIN = "http://192.168.134.2:8080/HDDT_WebService/";

    public static final String GET_INV = DOMAIN + "rest/inv/";

    public static final String GET_INV_FOLLOW_OWNER = DOMAIN + "rest/inv/ownerid=";

    public static final String GET_INV_FOLLOW_PERIOD = DOMAIN + "rest/inv/ownerid=";

    public static final String GET_INV_FOLLOW_TYPE = DOMAIN + "rest/inv/ownerid=";

    public static final String GET_ALL_INV = DOMAIN + "rest/allinvs";

    public static final String GET_COUNT_INV = DOMAIN + "rest/inv/count";

    public static final String INSERT_INV = DOMAIN + "rest/inv/add";

    public static final String UPDATE_INV = DOMAIN + "rest/inv/update/";

    public static final String DELETE_INV = DOMAIN + "rest/inv/delete/";
}
