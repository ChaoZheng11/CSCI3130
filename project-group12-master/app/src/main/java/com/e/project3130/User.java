package com.e.project3130;

import java.io.Serializable;

/**
 * Author Ziwei Wang & Chao Zheng
 * Create Date: 2019/2/10
 *Description: This is the base user object.
 *
 **/
public class User implements Serializable {
    public String name;
    public String id;
    public String password;
    public String email;
    public String role;
    public String personal_email;
    public String emergency_contact;
    public String address;


    public User(){

    }

    public User(String name, String email,String role,String password, String personal_email, String emergency_contact, String address){
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
        this.personal_email = personal_email;
        this.emergency_contact = emergency_contact;
        this.address = address;
    }


    public String toString(){
        return "student name: " + name + "student email: " + email + "Role: " +
                role + "Password:"+ password;
    }

}

