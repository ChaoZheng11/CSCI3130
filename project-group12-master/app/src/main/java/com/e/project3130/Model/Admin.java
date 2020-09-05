package com.e.project3130.Model;
/**
 * Author Ziwei Wang
 * Create Date: 2019/2/12
 *Description: Sort and list the courses and list information of the courses.
 *
 **/
public class Admin {
    private String header_name;
    private String header_id;
    private String header_password;
    private String header_email;

    public Admin(){

    }

    public Admin(String header_name, String header_email, String header_password){
        this.header_name = header_name;
        this.header_password = header_password;
        this.header_email=header_email;
    }


    public String toString(){
        return "Admin name" + header_name + "header ID" + header_id+"Admin Email"+header_email;
    }
}
