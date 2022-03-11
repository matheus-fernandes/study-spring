package com.study.springboot.message;

public interface DepartmentLoggingMessage {
    String INFO_POST_DEPARTMENT = "Posting a new department";
    String INFO_PUT_DEPARTMENT = "Putting a new department";
    String INFO_DELETE_DEPARTMENT = "Deleting a department";
    String INFO_GET_ALL_DEPARTMENT = "Getting all the available departments";
    String INFO_GET_DEPARTMENT_BY_ID = "Getting a department by id";
    String INFO_GET_DEPARTMENT_BY_NAME_IG_CASE = "Getting a department by name ignoring case";
}
