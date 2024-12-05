package dev.anirban.stridesync.constants;

public class UrlConstants {

    // Authentication Endpoints
    public static final String REGISTER_USER = "/register";
    public static final String LOGIN_USER = "/login";

    // Measurement Endpoints
    public static final String CREATE_MEASUREMENT = "/measurements";
    public static final String FIND_MEASUREMENT_QUERY = "/measurements";
    public static final String PUT_MEASUREMENT_QUERY = "/measurements";
    public static final String DELETE_MEASUREMENT_QUERY = "/measurements/{id}";

    // Water Endpoints
    public static final String CREATE_WATER = "/waters";
    public static final String FIND_WATER_QUERY = "/waters";
    public static final String PUT_WATER_QUERY = "/waters";
    public static final String DELETE_WATER_QUERY = "/waters/{id}";
}