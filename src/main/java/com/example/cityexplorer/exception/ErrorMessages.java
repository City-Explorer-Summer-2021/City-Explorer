package com.example.cityexplorer.exception;

public enum ErrorMessages {
    BAD_REQUEST("Bad request"),
    BAD_LOGIN_PASSWORD("Bad login or password"),
    BAD_PASSWORD_NOT_MATCHING("Password not matching"),
    WRONG_USER("Wrong user"),
    NO_PERMISSIONS("No permissions"),
    WRONG_OLD_PASSWORD("Wrong old password"),
    NOT_FOUND("Not found"),
    USERNAME_ALREADY_TAKEN("The username is already taken"),
    NULL_USER_ID("User id should not be null"),
    NULL_USERNAME("Username should not be null"),
    NULL_USER_OBJECT("User object should not be null"),
    NULL_USER_AUTHORITIES("User authorities should not be null"),
    NULL_PASSWORD_DATA("Password data should not be null"),
    NULL_TIMESTAMP("Timestamp should not be null"),
    NULL_DESCRIPTION("Description should not be null"),
    NULL_PASSWORD("Password should not be null"),
    NULL_ATTRACTION_ID("Attraction id should not be null"),
    NULL_ATTRACTION_OBJECT("Attraction object should not be null"),
    NULL_CITY_ID("City id should not be null"),
    NULL_CITY_OBJECT("City object should not be null"),
    NULL_CITY_PHOTO_OBJECT("CityPhoto object should not be null"),
    NULL_EVENT_ID("Event id should not be null"),
    NULL_EVENT_OBJECT("Event object should not be null"),
    NULL_FOOD_PLACE_ID("Food place id should not be null"),
    NULL_FOOD_PLACE_OBJECT("Food place object should not be null"),
    NULL_FOOD_PLACE_VALUATION_ID("Food place valuation id should not be null"),
    NULL_FOOD_PLACE_VALUATION_OBJECT("Food place valuation object should not be null"),
    NULL_HOTEL_ID("Hotel id should not be null"),
    NULL_HOTEL_OBJECT("Hotel object should not be null"),
    NULL_HOTEL_CATEGORY_ID("Hotel category id should not be null"),
    NULL_HOTEL_CATEGORY_OBJECT("Hotel category object should not be null"),
    NULL_TRANSPORT_ID("Transport id should not be null"),
    NULL_TRANSPORT_OBJECT("Transport object should not be null"),
    NULL_TRANSPORT_CATEGORY_ID("Transport category id should not be null"),
    NULL_TRANSPORT_CATEGORY_OBJECT("Transport category object should not be null");

    private final String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
