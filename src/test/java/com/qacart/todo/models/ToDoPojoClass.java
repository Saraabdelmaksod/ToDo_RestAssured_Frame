package com.qacart.todo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ToDoPojoClass {


    //@JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("isCompleted")
    private Boolean isCompleted;
    @JsonProperty("_id")
    private String _id;
    private String item;
    private String userID;
    private String createdAt;
    @JsonProperty("__v")
    private String __v;

// To make deserlization
    public ToDoPojoClass() {}
    public ToDoPojoClass(Boolean isCompleted,String item)
    {
        this.isCompleted=isCompleted;
        this.item=item;
    }
    public ToDoPojoClass(String item)
    {
        this.item=item;
    }


    @JsonProperty("isCompleted")
    public Boolean getCompleted() {return isCompleted;}
    @JsonProperty("isCompleted")
    public void setCompleted(Boolean completed) {isCompleted = completed;}
    @JsonProperty("_id")
    public String get_id() {return _id;}
    @JsonProperty("_id")
    public void set_id(String _id) {this._id = _id;}
    public String getItem() {return item;}
    public void setItem(String item) {this.item = item;}
    public String getUserID() {return userID;}
    public void setUserID(String userID) {this.userID = userID;}
    public String getCreatedAt() {return createdAt;}
    public void setCreatedAt(String createdAt) {this.createdAt = createdAt;}
    @JsonProperty("__v")
    public String get__v() {return __v;}
    @JsonProperty("__v")
    public void set__v(String __v) {this.__v = __v;}



}
