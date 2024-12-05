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

    // Sleep Endpoints
    public static final String CREATE_SLEEP = "/sleeps";
    public static final String FIND_SLEEP_QUERY = "/sleeps";
    public static final String PUT_SLEEP_QUERY = "/sleeps";
    public static final String DELETE_SLEEP_QUERY = "/sleeps/{id}";

    // Activity Endpoints
    public static final String CREATE_ACTIVITY = "/activities";
    public static final String FIND_ACTIVITY_QUERY = "/activities";
    public static final String PUT_ACTIVITY_QUERY = "/activities";
    public static final String DELETE_ACTIVITY_QUERY = "/activities/{id}";
}