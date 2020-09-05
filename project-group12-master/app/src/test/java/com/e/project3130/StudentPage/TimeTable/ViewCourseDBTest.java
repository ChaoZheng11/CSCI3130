package com.e.project3130.StudentPage.TimeTable;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 * Author:Ziwei Wang
 * Date: 2019/3/30
 * Description: Test for ViewCourseDB
 **/
public class ViewCourseDBTest {
    @Test
    public void ViewCourseDB_is_Exists(){
        try {
            Class.forName("com.e.project3130.StudentPage.TimeTable.ViewCourseDB");
        } catch(ClassNotFoundException e) {
            Assert.fail("Should create a class.");
        }
    }

    @Test
    public void SpotCalculatorTest1(){
        assertEquals("50", ViewCourseDB.SpotCalculator("100",50));
    }

    @Test
    public void SpotCalculatorTest2(){
        assertEquals("0",ViewCourseDB.SpotCalculator("0",0));
    }


    @Test
    public void SpotCalculatorTest3(){
        assertEquals("0",ViewCourseDB.SpotCalculator("40",50));
    }

    @Test
    public void SpotCalculatorTest4(){
        assertEquals("1", ViewCourseDB.SpotCalculator("3",2));
    }
}