package com.vnpt.hddtcustomer.constants;


public class CusRestURIConstants {

    private static final String DOMAIN = "http://192.168.134.2:8080/HDDT_WebService/";

    public static final String DO_LOGIN = DOMAIN + "dologin/";

    public static final String GET_CUS = DOMAIN + "rest/cus/{id}";

    public static final String GET_ALL_CUS = DOMAIN + "rest/allcuss";

    public static final String GET_COUNT_CUS = DOMAIN + "rest/cus/count";

    public static final String INSERT_CUS = DOMAIN + "rest/cus/add";

    public static final String UPDATE_CUS = DOMAIN + "rest/cus/update/{id}";

    public static final String DELETE_CUS = DOMAIN + "rest/cus/delete/{id}";

}
