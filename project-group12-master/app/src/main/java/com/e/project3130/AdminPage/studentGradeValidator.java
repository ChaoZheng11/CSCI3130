package com.e.project3130.AdminPage;

/**
 * studentGradeDetail.java
 *
 * Author Kinson Chu & Xin Deng
 *
 * Create Date: 2019/3/30
 *
 * Description: an activity, which used to validate
 * grade is valid or not.
 **/

public class studentGradeValidator
{

    public static boolean validate (String grade)
    {

        if(grade.isEmpty()) // grade can not be empty
        {
            return false;
        }

        if(grade.length() > 2) // There is only one letter for the grade
        {
            return false;
        }
        if(grade.matches(".*[a-z].*")) // Grade can not be
        {
            return false;
        }

        if(grade.matches(".*[A|B|C|D|F|X].*")) // A B C D F X
        {
            return true;
        }
        return true;
    }
    //
}
