package utils;

import io.restassured.RestAssured;

public class APIConstants {

    public static final String baseURL = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static final String GENERATE_TOKEN = baseURL+"/generateToken.php";
    public static final String CREATE_EMPLOYEE = baseURL+"/createEmployee.php";
    public static final String GET_ONE_EMPLOYEE = baseURL+"/getOneEmployee.php";
    public static final String UPDATE_EMPLOYEE = baseURL+"/updateEmployee.php";
    public static final String PARTIAL_UPDATE_EMPLOYEE = baseURL+"/updatePartialEmplyeesDetails.php";
    public static final String DELETE_EMPLOYEE = baseURL+"/deleteEmployee.php";
    public static final String GET_ALL_EMPLOYEES = baseURL+"/getAllEmployees.php";
    public static final String GET_JOB_TITLE = baseURL+"/jobTitle.php";
    public static final String GET_EMPLOYMENT_STATUS = baseURL+"/employeementStatus.php";

    public static final String HEADER_CONTENT_TYPE_KEY = "Content-Type";
    public static final String HEADER_CONTENT_TYPE_VALUE = "application/json";
    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";


}
