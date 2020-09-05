package com.e.project3130.StudentPage;

import org.junit.Assert;
import org.junit.Test;

/**
 * GradeReportTest.java
 *
 * Author Xin Deng & Kinson
 *
 * Create Date: 2019/3/30
 *
 * Description: Tests for GradeReport.java.
 */

public class gradeReportTest {

    @Test
    public void Grade_Report_is_Exists(){
        try {
            Class.forName("com.e.project3130.StudentPage.AcademicReport.GradeReport");
        } catch(ClassNotFoundException e) {
            Assert.fail("Should create a class called 'GradeReport'.");
        }

    }

}

