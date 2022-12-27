package com.qacart.todo.apis;

import com.qacart.todo.base.RequestSpecificationClass;
import com.qacart.todo.data.Route;
import com.qacart.todo.models.ToDoPojoClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ToDoAPI {

    public static Response toDo(ToDoPojoClass task, String token)
    {
        return given().spec(RequestSpecificationClass.getRequestSpec()).body(task)
            .auth().oauth2(token)
            .when().post(Route.TASK_PATH)
            .then().log().all().extract().response();
    }

    public static Response getTask(String token, String id)
    {
        return given().spec(RequestSpecificationClass.getRequestSpec())
            .auth().oauth2(token)
            .when().get(Route.TASK_PATH+"/" + id).then().log().all().extract().response();
    }

    public static Response updateTask(String token, ToDoPojoClass task, String idOfTask)
    {
        return given().spec(RequestSpecificationClass.getRequestSpec())
            .auth().oauth2(token).body(task)
            .when().put(Route.TASK_PATH+ "/"+ idOfTask).then().log().all().extract().response();
    }

    public static Response deleteTask(String token, String idOfTask)
    {
        return given().spec(RequestSpecificationClass.getRequestSpec())
                .auth().oauth2(token)
                .when().delete(Route.TASK_PATH+ "/"+ idOfTask).then().log().all().extract().response();
    }


}
