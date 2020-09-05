package com.e.project3130.Model;

import java.io.Serializable;

/**
 * Author Chao Zheng
 * Create Date: 2019/2/12
 *Description: Create a base student object
 *
 **/
public class Student implements Serializable {
    public String student_name;
    public String student_id;
    public String student_password;
    public String email;
    public String personal_email;
    public String emergency_contact;
    public String address;

    public Student(){}

    public Student(String student_name, String student_email,
                   String student_password){
        this.student_name = student_name;
        this.email = student_email;
        this.student_password = student_password;
        this.personal_email = "";
        this.emergency_contact = "";
        this.address = "";
    }

    public String toString(){
        return "student name: " + student_name + "student email: "
                + email ;
    }

}
