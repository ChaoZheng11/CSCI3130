package com.e.project3130.AdminPage;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * addNewCourseTest.java
 *
 * Author Jiaxiang Liu & Chao Zheng
 *
 * Create Date: 2019/3/29
 *
 * Description: Tests addNewCourseTest.java.
 **/
public class addNewCourseTest {
    @Test
    public void addNewCourseTest1() {
        boolean result;
        result = (addNewCourse.codecheck("CSCI3101") && addNewCourse.weekcheck("MW"));
        assertEquals(result, true);
    }

    @Test
    public void addNewCourseTest2() {
        boolean result;
        result = (addNewCourse.codecheck("CSCI310") && addNewCourse.weekcheck("MWF"));
        assertEquals(result, false);
    }

    @Test
    public void addNewCourseTest3() {
        boolean result;
        result = (addNewCourse.codecheck("CSCI3101") && addNewCourse.weekcheck("OP"));
        assertEquals(result, false);
    }
}
