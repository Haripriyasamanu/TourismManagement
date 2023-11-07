package com.example.demo.utils;

import java.util.regex.Pattern;

public class Validator {


    private static final String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    private static final String namePattern ="^[A-Z].*";
    private static final String numberPattern= "^[0-9]{1,10}$";
    private static final String reviewPattern="^.[0-4]{1,5}$";
    public  static  boolean validatePassword(String password){
        return Pattern.matches(passwordPattern,password);
    }

    public static boolean validateName(String name){
        return  Pattern.matches(namePattern,name);
    }
    public static  boolean validNumber(String contactNumber){
        return  Pattern.matches(numberPattern,contactNumber);
    }
    public static boolean validReview(String rating){
        return Pattern.matches(reviewPattern,rating);
    }

}
