package com.qacart.todo.testcases;

import com.qacart.todo.apis.ToDoAPI;
import com.qacart.todo.data.ErrorMessage;
import com.qacart.todo.models.ErrorMessagePojoClass;
import com.qacart.todo.models.ToDoPojoClass;
import com.qacart.todo.steps.ToDoSteps;
import com.qacart.todo.steps.UserSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Feature("Add tasks")

public class ToDoDynamic5Test {

    @Story("AS user, I want to login, so i can add tasks")
    @Test(description = "User Should be able to add Task")
    public void shouldAbleToAddTask()
    {
        ToDoPojoClass task= ToDoSteps.generateToDoTask();

        String token= UserSteps.getToken();

        Response response= ToDoAPI.toDo(task, token);

        ToDoPojoClass returnBody=response.as(ToDoPojoClass.class);

        assertThat(response.statusCode(), equalTo(201));
        assertThat(returnBody.getItem(), equalTo(task.getItem()));
        assertThat(returnBody.getCompleted(), equalTo(task.getCompleted()));
    }

    @Story("AS user, I want to login, so i can add tasks")
    @Test(description = "User Should not be able to add Task without entering Complete Status")
    public void shouldNotAbleToAddTaskWithoutCompleteStatus()
    {
        ToDoPojoClass task= new ToDoPojoClass( "ISTQB");

        String token= UserSteps.getToken();

        Response response= ToDoAPI.toDo(task, token);

        ErrorMessagePojoClass returnBody=response.body().as(ErrorMessagePojoClass.class);

        assertThat(response.statusCode(), equalTo(400));
        assertThat(returnBody.getMessage(), equalTo(ErrorMessage.COMPLETED_IS_REQUIRED));
    }

    @Story("AS user, I want to login, so i can view specific task")
    @Test(description = "User Should be able to get Task")
    public void shouldAbleToGetTaskById()
    {
        ToDoPojoClass addTask=ToDoSteps.generateToDoTask();
        String token= UserSteps.getToken();
        String id=ToDoSteps.getIdOfTask(addTask,token);
        Response response= ToDoAPI.getTask(token, id);

        ToDoPojoClass returnBody=response.body().as(ToDoPojoClass.class);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(returnBody.getCompleted(), equalTo(false));
        assertThat(returnBody.get_id(), equalTo(id));
        assertThat(returnBody.getItem(), equalTo(addTask.getItem()));

    }

    @Story("AS user, I want to login, so i can update tasks")
    @Test(description = "User Should not be able to Update Task")
    public void updateSpecificTaskById()
    {
        ToDoPojoClass addTask=ToDoSteps.generateToDoTask();
        String token= UserSteps.getToken();
        String id=ToDoSteps.getIdOfTask(addTask,token);
        Response response= ToDoAPI.getTask(token, id);

        ToDoPojoClass returnBody=response.body().as(ToDoPojoClass.class);

        assertThat(response.statusCode(), equalTo(200));
        assertThat(returnBody.getCompleted(), equalTo(addTask.getCompleted()));
        assertThat(returnBody.getItem(), equalTo(addTask.getItem()));

    }
    @Story("AS user, I want to login, so i can delete added task")
    @Test(description = "User Should not be able to delete Task")
    public void deleteSpecificTaskById()
    {
        ToDoPojoClass addTask=ToDoSteps.generateToDoTask();
        String token= UserSteps.getToken();
        String id=ToDoSteps.getIdOfTask(addTask,token);

       // String token= UserSteps.getToken();
      //  String idOfTask="639c799246b3ad001666d71c";

        Response response= ToDoAPI.deleteTask(token, id)  ;

        assertThat(response.statusCode(), equalTo(200));

    }




}
