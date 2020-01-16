package com.practice.springbootrestassured.util;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

    public static String empFirstName(){
        return "Ryan"+RandomStringUtils.randomAlphabetic(4);
    }

    public static String empLastName(){
        return "Wellington"+RandomStringUtils.randomAlphabetic(4);
    }

    public static Float salary(){
        return Float.valueOf(RandomStringUtils.randomNumeric(5));
    }
}
