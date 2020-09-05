package com.e.project3130.AdminPage;

import org.junit.Assert;
import org.junit.Test;

/**
 * courseList.java
 * <p>
 * Author Kinson Chu & Xin Deng
 * <p>
 * Create Date: 2019/3/30
 * <p>
 * Description: Tests courseList.java.
 */


public class courseListTest {
    @Test
    public void Course_Detail_is_Exists(){
        try {
            Class.forName("com.e.project3130.AdminPage.courseList");
        } catch(ClassNotFoundException e) {
            Assert.fail("Should create a class called 'courseDetail'.");
        }

    }
}