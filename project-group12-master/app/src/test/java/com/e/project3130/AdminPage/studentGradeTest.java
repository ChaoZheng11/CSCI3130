package com.e.project3130.AdminPage;

/**
 * studentGrade.java
 *
 * Author Kinson Chu & Xin Deng
 *
 * Create Date: 2019/3/30
 *
 * Description: Tests studentGrade.java.
 */

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class studentGradeTest
{
    @Test
    public void nullGrade () throws Exception
    {
        String grade ="";
        assertEquals(false, studentGradeValidator.validate(grade));
    }

    @Test
    public void smallLetter () throws Exception
    {
        String grade ="a";
        assertEquals(false, studentGradeValidator.validate(grade));
    }

    @Test
    public void lengthGraterThan1 () throws Exception
    {
        String grade ="1321131";
        assertEquals(false, studentGradeValidator.validate(grade));
    }

    @Test
    public void GoodPassword1 () throws Exception
    {
        String grade ="D";
        assertEquals(true, studentGradeValidator.validate(grade));
    }
    @Test
    public void GoodPassword2 () throws Exception
    {
        String grade ="X";
        assertEquals(true, studentGradeValidator.validate(grade));
    }
    //
}

