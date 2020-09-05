package com.e.project3130.AdminPage;

import org.junit.Assert;
import org.junit.Test;


/**
 * courseDetail.java
 *
 * Author Kinson Chu & Xin Deng
 *
 * Create Date: 2019/2/30
 *
 * Description: Tests AddNewTerm.java.
 **/
public class courseDetailTest {
    @Test
    public void Course_Detail_is_Exists(){
        try {
            Class.forName("com.e.project3130.AdminPage.courseDetail");
        } catch(ClassNotFoundException e) {
            Assert.fail("Should create a class called 'courseDetail'.");
        }

    }
}