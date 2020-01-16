package com.practice.springbootrestassured.testcases;

import com.practice.springbootrestassured.base.TestBase;
import com.practice.springbootrestassured.util.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC002_Create_Employee extends TestBase {

    String empFirstName = RestUtils.empFirstName();
    String empLastName = RestUtils.empLastName();
    Float salary = RestUtils.salary();

    @BeforeClass
    public void createEmployee() throws InterruptedException {
        logger.info("*****createEmployee**********");
        RestAssured.baseURI = "http://localhost:8080/rest/employee/saveEmployee";
        httpRequest = RestAssured.given();
        JSONObject jsonObjectReqParams = new JSONObject();
        jsonObjectReqParams.put("empFirstName", empFirstName);
        jsonObjectReqParams.put("empLastName", empLastName);
        jsonObjectReqParams.put("salary", salary);

        httpRequest.header("Content-Type","application/json");
        httpRequest.body(jsonObjectReqParams.toJSONString());
        response = httpRequest.request(Method.POST);
        Thread.sleep(10000);
    }

    @Test
    public void checkResponseBody(){
        logger.info("******Checking Response Body********");
        String responseBody = response.getBody().asString();
        logger.info("Response Body:::::"+responseBody);
        Assert.assertEquals(responseBody.contains(empFirstName), true);
        Assert.assertEquals(responseBody.contains(empLastName), true);
        Assert.assertEquals(responseBody.contains(salary.toString()), true);
    }

    @Test
    public void checkStatusCode(){
        logger.info("******Checking Status Code********");
        int statusCode = response.getStatusCode();
        logger.info("Response Status Code:::::"+statusCode);
        Assert.assertEquals(statusCode, 200);
    }
}
