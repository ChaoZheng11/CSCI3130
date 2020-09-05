package com.e.project3130.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * addNewTerm.java
 *
 * Author Jiaxiang &
 *
 * Create Date: 2019/3/21
 *
 * Description: A basic term object.
 **/
public class Term implements Serializable {
    public String year;
    public String semester;
    public List<String> courselist = new ArrayList<>();

    public Term(){
    }

    public Term(String year,String semester){
        this.year = year;
        this.semester = semester;
    }

    public Term(String year,String semester, List<String> courselist){
        this.year = year;
        this.semester = semester;
        this.courselist = courselist;
    }

    public void setCourselist(List<String> course){
        this.courselist = course;
    }
    public List<String> returnCourseList(){
        return courselist;
    }

    public String getYear() { return year; }

    public String getSemester(){
        return semester;
    }

    public void setYear(String year) { this.year = year; }

    public void setSemester(String semester){this.semester = semester;}
    @Override
    public String toString (){
        return "Year" + year + "Semester" + semester;
    }
}
