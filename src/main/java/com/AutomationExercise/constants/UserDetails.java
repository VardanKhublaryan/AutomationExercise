package com.AutomationExercise.constants;

public enum UserDetails {
    NAME("Vardan"),
    USER_LAST_NAME("Vardan Khublaryan"),
    EMAIL("vardan@gmail.com"),
    PASSWORD("1111"),
    MOBILE_NUMBER("789456123"),
    COMPANY("IDT"),
    ADDRESS_1("Yerevan"),
    ADDRESS_2("44"),
    COUNTRY("Canada"),
    STATE("Armenia"),
    CITY("Yerevan"),
    ZIP_CODE("1234"),
    CART_NAME("Vardan Khublaryan"),
    CART_NUMBER("46387368737638"),
    CART_EXPIRATION_MONTH("05"),
    CART_EXPIRATION_YEAR("2030"),
    CVC("378");

    final String value;

    UserDetails(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
