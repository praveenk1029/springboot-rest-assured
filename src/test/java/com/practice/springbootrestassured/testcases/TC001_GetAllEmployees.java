package com.practice.springbootrestassured.testcases;

import com.practice.springbootrestassured.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC001_GetAllEmployees extends TestBase {

    @BeforeClass
    public void getAllEmployees() throws InterruptedException {
        logger.info("********Started getAllEmployees*******");
        RestAssured.baseURI = "http://localhost:8080/rest/employee/getAllEmployees";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET);
        Thread.sleep(5000);
    }

    @Test
    public void checkResponseBody(){
        logger.info("******Checking Response Body********");
        String responseBody = response.getBody().asString();
        logger.info("Response Body:::::"+responseBody);
        Assert.assertTrue(responseBody!=null);
    }

    @Test
    public void checkStatusCode(){
        logger.info("******Checking Status Code********");
        int statusCode = response.getStatusCode();
        logger.info("Response Status Code:::::"+statusCode);
        Assert.assertEquals(statusCode, 200);
    }
}
