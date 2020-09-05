package com.e.project3130.AdminPage;

import org.junit.Assert;
import org.junit.Test;

/**
 * numberRegisterTest.java
 *
 * Author Jiaxiang Liu & Chao Zheng
 *
 * Create Date: 2019/3/27
 *
 * Description: Tests numberRegisterTest.java.
 **/
public class numberRegisterTest {

    @Test
    public void Number_Register_Exists(){
        try {
            Class.forName("com.e.project3130.AdminPage.RegisterNumber");
        } catch(ClassNotFoundException e) {
            Assert.fail("Should create a class to count the number of students registered in each course");
        }
    }
}