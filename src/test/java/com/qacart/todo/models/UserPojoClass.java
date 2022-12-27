package com.qacart.todo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPojoClass {

   // Empty Constractor to use in deserilization
    public UserPojoClass()
    {

    }
    // Use it for login
    public UserPojoClass(String email,String password)
    {

        this.email=email;
        this.password=password;

    }

    // Use it for Register
     public UserPojoClass(String firstName, String lastName,String email,String password)
   {
    this.firstName=firstName;
    this.lastName=lastName;
    this.email=email;
    this.password=password;

   }

//variable for request
    private String firstName;
    private String lastName;
    private String email;
    private String password;


    //variable for response
    @JsonProperty("access_token")
    private String access_token;
    private String userID;




    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @JsonProperty("access_token")
    public String getAccess_token() {
        return access_token;
    }
    @JsonProperty("access_token")
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }






}
