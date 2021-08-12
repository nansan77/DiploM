package ru.netology.data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static String getFirstCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getFirstCardExpectedStatus() {
        return "APPROVED";
    }

    public static String getSecondCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getSecondCardExpectedStatus() {
        return "DECLINED";
    }

    public static String getCardNumberDifferent() {
        return faker.business().creditCardNumber();
    }

    public static String getCardNumberEmpty() {
        return "                   ";
    }

    public static String getValidMonth() {
        return "01";
    }

    public static String getInvalidMonth() {
        return "13";
    }

    public static String getEmptyMonth() {
        return "  ";
    }

    public static String getZeroMonth() {
        return "00";
    }

    public static String getValidYear() {
        return "24";
    }

    public static String getInvalidYear() {
        return "14";
    }

    public static String getEmptyYear() {
        return "  ";
    }

    public static String getValidOwner() {
        return faker.name().fullName();
    }

    public static String getInvalidOwner() {
        return "Хулио#12345";
    }

    public static String getEmptyOwner() {
        return "  ";
    }

    public static String getValidCvs() {
        return "456";
    }

    public static String getInvalidCvs() {
        return "13";
    }

    public static String getEmptyCvs() {
        return "  ";
    }

}