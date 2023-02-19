package com.bbdproj.myjpa;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class ValidationsUtil {

    public static boolean validateName(String name) {
        String regex = "[A-Za-z]{3,70}$";

        Pattern p = Pattern.compile(regex);

        return p.matcher(name).matches();

    }

    public static boolean validateEmail(String email) {
        String emailRegex = "@";

        Pattern pat = Pattern.compile(emailRegex);


        return pat.matcher(email).matches();
    }

    public static boolean validateDate(Date date) {

        Calendar calendar = Calendar.getInstance();

        if (calendar.get(Calendar.YEAR) == date.getYear()) {
            return true;
        }

        return false;

    }

}
