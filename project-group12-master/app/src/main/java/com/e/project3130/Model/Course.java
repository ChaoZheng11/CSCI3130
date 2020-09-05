package com.e.project3130.Model;

import java.io.Serializable;

/**
 * Author:Ziwei Wang
 * Date: 2019/3/9
 * Create a base course object
 **/

public class Course implements Serializable {
    public String code;
    public String title;
    public String time;
    public String week;
    public String id;
    public String term;
    public String spot;

    public Course(){
    }

    public Course(String code,String title,String time, String week, String term, String spot){
        this.code = code;
        this.title = title;
        this.time = time;
        this.week = week;
        this.term = term;
        this.spot = spot;
    }

    public String getTitle() { return title; }

    public String getCode(){
        return code;
    }

    public void setTitle(String title) { this.title = title; }

    public void setCode(String code){this.code = code;}
    @Override
    public String toString (){
        return "Course Title: " + title + "Course Code" + code;
    }
}
