package com.qacart.todo.steps;

import com.github.javafaker.Faker;
import com.qacart.todo.apis.ToDoAPI;
import com.qacart.todo.models.ToDoPojoClass;
import io.restassured.response.Response;

public class ToDoSteps

{
       public static ToDoPojoClass generateToDoTask()
       {
           Faker faker=new Faker();
           String item=faker.book().title();
           boolean isCompleted= false;
           ToDoPojoClass task=new ToDoPojoClass(isCompleted,item);
           return task;

       }

      public static String getIdOfTask(ToDoPojoClass task,String token )
      {

          // add generated task
          Response returnBody= ToDoAPI.toDo(task, token);

          return returnBody.body().path("_id");

      }


}
