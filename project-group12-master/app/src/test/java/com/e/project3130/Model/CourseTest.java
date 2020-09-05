package com.e.project3130.Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * CourseTest.java
 * <p>
 * Author Kinson Chu & Xin Deng
 * <p>
 * Create Date: 2019/3/30
 * <p>
 * Description: Tests for CourseTest.java.
 */

public class CourseTest {
    Course course = new Course();

    @Test
    public void getTitle() {
        course.setTitle("Software Engineering");
        String result = course.getTitle();
        assertEquals("Software Engineering",result);
    }

    @Test
    public void getCode() {
        course.setCode("CSCI3101");
        String result = course.getCode();
        assertEquals("CSCI3101",result);
    }
}