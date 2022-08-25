package com.example.cropapp;

public class Course {
    public String getCourseTitle() {
        return CourseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        CourseTitle = courseTitle;
    }

    public String getCourseDesc() {
        return CourseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        CourseDesc = courseDesc;
    }

    private String CourseTitle="";
    private String CourseDesc="";
    public Course(String courseTitle, String courseDesc) {
        CourseTitle = courseTitle;
        CourseDesc = courseDesc;
    }
    public Course(){


    }
}
