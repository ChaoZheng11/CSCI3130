package com.e.project3130.StudentPage;



import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * author: Guoyu and Ziwei
 * date: Apr 1st, 2019
 * Tests for update personal information
 **/
public class personal_infoTest {


    @Test
    public void EmailTest1(){
        assertEquals(personal_info.EmailTest("sd123546@dal.ca"),"pass");
    }

    @Test
    public void EmailTest2(){
        assertEquals(personal_info.EmailTest("123456"),"not pass");
    }

    @Test
    public void EmailTest3(){
        assertEquals(personal_info.EmailTest(""),"not pass");
    }

    @Test
    public void ContactTest1(){
        assertEquals(personal_info.Contact("sd123546@dal.ca"),"not pass");
    }

    @Test
    public void ContactTest2(){
        assertEquals(personal_info.Contact("1234567890"),"pass");
    }

    @Test
    public void ContactTest3(){
        assertEquals(personal_info.Contact(""),"not pass");
    }

    @Test
    public void AddressTest1(){
        assertEquals(personal_info.Address(""),"not pass");
    }

    @Test
    public void AddressTest2(){
        assertEquals(personal_info.Address("fsdfgwef"),"pass");
    }

    @Test
    public void AddressTest3(){
        assertEquals(personal_info.Address("6201 University ave"),"pass");
    }

    //

}