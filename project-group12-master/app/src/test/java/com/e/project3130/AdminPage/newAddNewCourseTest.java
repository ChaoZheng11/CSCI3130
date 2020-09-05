package com.e.project3130.AdminPage;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * newAddNewCourseTest.java
 *
 * Author Jiaxiang Liu & Chao Zheng
 *
 * Create Date: 2019/3/27
 *
 * Description: Tests newAddNewCourseTest.java.
 **/
public class newAddNewCourseTest {
    @Test
    public void newAddNewCourseTest1(){
        boolean result;
        result = (addNewCourse_new.semestercheck1("Summer")&&addNewCourse_new.yearcheck1("2020"));
        assertEquals(result,true);
    }
    @Test
    public void newAddNewCourseTest2(){
        boolean result;
        result = (addNewCourse_new.semestercheck1("Fall")&&addNewCourse_new.yearcheck1("2015"));
        assertEquals(result,false);
    }
    @Test
    public void newAddNewCourseTest3(){
        boolean result;
        result = (addNewCourse_new.semestercheck1("winttt")&&addNewCourse_new.yearcheck1("2019"));
        assertEquals(result,false);
    }
    @Test
    public void newAddNewCourseTest4(){
        boolean result;
        result = (addNewCourse_new.semestercheck1("Summer")&&addNewCourse_new.yearcheck1(""));
        assertEquals(result,false);
    }
    @Test
    public void newAddNewCourseTest5(){
        boolean result;
        result = (addNewCourse_new.semestercheck1("")&&addNewCourse_new.yearcheck1("2020"));
        assertEquals(result,false);
    }
}
