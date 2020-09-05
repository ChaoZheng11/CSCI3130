package com.e.project3130.AdminPage;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * AddNewTermTest.java
 *
 * Author Jiaxiang Liu & Chao Zheng
 *
 * Create Date: 2019/3/21
 *
 * Description: Tests AddNewTerm.java.
 **/

public class AddNewTermTest{
    @Test
    public void AddNewTermTest1(){
        boolean result;
        result = (AddNewTerm.semestercheck("Fall")&&AddNewTerm.yearcheck("2019"));
        assertEquals(result,true);
    }
    @Test
    public void AddNewTermTest2(){
        boolean result;
        result = (AddNewTerm.semestercheck("")&&AddNewTerm.yearcheck("2019"));
        assertEquals(result,false);
    }
    @Test
    public void AddNewTermTest3(){
        boolean result;
        result = (AddNewTerm.semestercheck("Winter")&&AddNewTerm.yearcheck(""));
        assertEquals(result,false);
    }
    @Test
    public void AddNewTermTest4(){
        boolean result;
        result = (AddNewTerm.semestercheck("Fall")&&AddNewTerm.yearcheck("big big"));
        assertEquals(result,false);
    }
    @Test
    public void AddNewTermTest5(){
        boolean result;
        result = (AddNewTerm.semestercheck("Fal")&&AddNewTerm.yearcheck("2018"));
        assertEquals(result,false);
    }
}
