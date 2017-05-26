package com.yunmin.rxdemo;

import java.util.List;

/**
 * Created by luoyunmin on 2017/3/28.
 */

public class Student {
    private String name;
    private List<Course> courseList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
