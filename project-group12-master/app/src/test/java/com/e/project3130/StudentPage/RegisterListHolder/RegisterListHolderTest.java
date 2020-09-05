package com.e.project3130.StudentPage.RegisterListHolder;

import org.junit.Assert;
import org.junit.Test;

/**
 * RegisterListHolder.java
 *
 * Author Cao Zheng & Jiaxiang Liu
 *
 * Create Date: 2019/3/30
 *
 * Description: Tests for RegisterListHolder.java.
 */

public class RegisterListHolderTest {

    @Test
    public void Register_List_Holder_is_Exists(){
        try {
            Class.forName("com.e.project3130.StudentPage.Register.RegisterListHolder");
        } catch(ClassNotFoundException e) {
            Assert.fail("Should create a class to find the number of student in a course.");
        }
    }
}