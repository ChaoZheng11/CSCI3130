package com.e.project3130.StudentPage;

import com.e.project3130.StudentPage.TutionFee.Tuition;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * TuitionTest.java
 *
 * Author Cao Zheng & Jiaxiang Liu
 *
 * Create Date: 2019/2/30
 *
 * Description: Tests for Tuition.java.
 */

//UnitTest for TuitionCalculator.
public class TuitionTest {
    @Test
    public void TuitionTest1(){
        assertEquals(Tuition.TuitionCalculator(5), 5000.0, 0.0);
    }
    @Test
    public void TuitionTest2(){
        assertEquals(Tuition.TuitionCalculator(0), 0.0, 0.0);
    }
    @Test
    public void TuitionTest3(){
        assertEquals(Tuition.TuitionCalculator(3), 3400.0, 0.0);
    }
}