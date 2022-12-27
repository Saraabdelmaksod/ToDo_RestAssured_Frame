package com.qacart.todo.apis;

import com.qacart.todo.base.RequestSpecificationClass;
import com.qacart.todo.data.Route;
import com.qacart.todo.models.UserPojoClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserAPI {

    public static Response register(UserPojoClass user)
    {
        return given().spec(RequestSpecificationClass.getRequestSpec())
                .body(user)
                .when()
                .post(Route.REGISTER_PATH)
                .then().log().all().extract().response();

    }

    public static Response login(UserPojoClass user)
    {
       return given().spec(RequestSpecificationClass.getRequestSpec())
                .body(user).log().all()
                .when()
                .post(Route.LOGIN_PATH)
                .then().log().all().extract().response();

    }
}
