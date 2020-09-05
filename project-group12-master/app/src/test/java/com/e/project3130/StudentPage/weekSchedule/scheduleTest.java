package com.e.project3130.StudentPage.weekSchedule;

import com.e.project3130.StudentPage.WeeklySchedule.weekschedule;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class scheduleTest {

    @Test
    public void scheduleTest1(){
        assertEquals(weekschedule.numberCourses(5),"pass");
    }

    @Test
    public void scheduleTest2(){
        assertEquals(weekschedule.numberCourses(0),"Not Pass");
    }

    @Test
    public void scheduleTest3(){
        assertEquals(weekschedule.numberCourses(6),"Not Pass");
    }
}
