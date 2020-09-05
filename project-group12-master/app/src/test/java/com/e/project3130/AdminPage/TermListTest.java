package com.e.project3130.AdminPage;

import org.junit.Assert;
import org.junit.Test;

/**
 * TermListTest.java
 *
 * Author Jiaxiang Liu & Chao Zheng
 *
 * Create Date: 2019/3/21
 *
 * Description: Tests TermList.java.
 **/
public class TermListTest {
    @Test
    public void Term_List_Exists(){
        try {
            Class.forName("com.e.project3130.AdminPage.TermList");
        } catch(ClassNotFoundException e) {
            Assert.fail("Should create a class called 'TermList'.");
        }
    }
}
