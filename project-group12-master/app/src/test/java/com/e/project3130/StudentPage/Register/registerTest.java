package com.e.project3130.StudentPage.Register;

import org.junit.Assert;
import org.junit.Test;

/**
 * Register.java
 *
 * Author Cao Zheng & Jiaxiang Liu
 *
 * Create Date: 2019/3/30
 *
 * Description: Tests for Register.java.
 */

public class registerTest {

    @Test
    public void Register_is_Exists(){
        try {
            Class.forName("com.e.project3130.StudentPage.Register.register");
        } catch(ClassNotFoundException e) {
            Assert.fail("Should create a class to find the number of student in a course.");
        }
    }
}