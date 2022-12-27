package com.qacart.todo.testcases;

import com.qacart.todo.apis.UserAPI;
import com.qacart.todo.data.ErrorMessage;
import com.qacart.todo.models.ErrorMessagePojoClass;
import com.qacart.todo.models.UserPojoClass;
import com.qacart.todo.steps.UserSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@Feature("User Register Test ")
public class UserTestDynamic5Test {
    @Story("AS user, I want to register, so i can add tasks")
    @Test(description = "Should be able to Register with valid data")
    public void testRegisterWithValidData(){
        // serialization
        UserPojoClass user= UserSteps.generateFakerUser();

        // call register function
        Response response= UserAPI.register(user);

        // deserialization
        UserPojoClass returnedBody= response.body().as(UserPojoClass.class);

        assertThat(response.statusCode(), equalTo(201));
        assertThat(returnedBody.getFirstName(), equalTo(user.getFirstName()));
    }

    @Story("AS user, I want to register, so i can add tasks")
    @Test(description = "Should not be able to Register with the mail used before")
    public void shouldNotBeRegisterWithTheSameEmail(){

        UserPojoClass user= UserSteps.getRegisteredUser();

        // call register function
        Response response= UserAPI.register(user);

        ErrorMessagePojoClass returnBody=response.body().as(ErrorMessagePojoClass.class);

        assertThat(response.statusCode(), equalTo(400));
        assertThat(returnBody.getMessage(), equalTo(ErrorMessage.EMAIL_IS_ALREADY_REGISTERED));
    }

    @Story("AS user, I want to login, so i can add tasks")
    @Test(description = "Should be able to login with registered user")
    public void shouldBeAbleToLogin()
    {
        UserPojoClass user= UserSteps.getRegisteredUser();
        UserPojoClass userLogin= new UserPojoClass(user.getEmail(), user.getPassword());

        Response response=UserAPI.login(userLogin);

        UserPojoClass returnedBody= response.body().as(UserPojoClass.class);
        assertThat(returnedBody.getFirstName(),equalTo(user.getFirstName()));
        assertThat(returnedBody.getAccess_token(),not(equalTo(null)));
    }

    @Story("AS user, I want to login, so i can add tasks")
    @Test(description = "Should not be able to login with wrong data")
    public void shouldNotBeAbleToLoginWhenPassIsWrong()
    {
        UserPojoClass user= UserSteps.getRegisteredUser();
        UserPojoClass userLogin= new UserPojoClass(user.getEmail(), "12345");

        Response response=UserAPI.login(userLogin);
        ErrorMessagePojoClass returnBody=response.body().as(ErrorMessagePojoClass.class);

        assertThat(response.statusCode(), equalTo(401));
        assertThat(returnBody.getMessage(), equalTo(ErrorMessage.EMAIL_OR_PASSWORD_WRONG));
    }



}
