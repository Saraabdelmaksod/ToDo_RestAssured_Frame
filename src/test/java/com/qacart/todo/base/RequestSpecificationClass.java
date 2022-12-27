package com.qacart.todo.base;


import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestSpecificationClass {

    public static String getEnv()
    {
      String env=System.getProperty("env","PRODUCTION");
      String baseURL;
      switch (env){
          case "PRODUCTION" : baseURL ="https://qacart-todo.herokuapp.com";
          break;
          case "TEST" : baseURL ="http://localhost:8080";
          break;
          default: throw new RuntimeException("Environment is not Supported");

      }
      return baseURL;
    }


public static RequestSpecification getRequestSpec()
{
    RequestSpecification requestSpec=  given()
            .baseUri(getEnv())
            .contentType(ContentType.JSON).log().all();

    return requestSpec;
}


}
