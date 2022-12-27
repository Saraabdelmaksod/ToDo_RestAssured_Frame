package com.qacart.todo.steps;

import com.github.javafaker.Faker;
import com.qacart.todo.apis.UserAPI;
import com.qacart.todo.models.UserPojoClass;
import io.restassured.response.Response;

public class UserSteps {

    public static UserPojoClass generateFakerUser()
    {
        Faker faker=new Faker();

        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String email=faker.internet().emailAddress();
        String password=faker.internet().password();

        UserPojoClass user=new UserPojoClass(firstName,lastName,email,password);
        return user;
    }

    public static UserPojoClass getRegisteredUser()
    {
        // generate Faker data
        UserPojoClass user= generateFakerUser();
        // Register by generated data
        UserAPI.register(user);
       //  return registered user
        return user;
    }


    public static String getToken()
    {
        // I create method to return token here in user class as we make register here

        UserPojoClass user= generateFakerUser();
       Response returnResponse= UserAPI.register(user);
       return returnResponse.body().path("access_token");

    }


 /*   public static UserPojoClass getUserAndPasswordForRegisteredUser()
    {
        // generate Faker data
        UserPojoClass user= generateFakerUser();
        // Register by generated data
        UserAPI.register(user);
        UserPojoClass user2=new UserPojoClass(user.getEmail(), user.getPassword());

        //  return registered user
        return user2;
    }*/
}
