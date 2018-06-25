package com.finedust.nomins.uitls;

public class NumberUtil {

    public static int parseInt(String expectedInt) {
        try{
            return Integer.parseInt(expectedInt);
        }catch(Exception e) {
            System.out.println("NumberUtil  parseInt [ " + expectedInt + " ]");
            e.printStackTrace();
            return 0;
        }
    }

    public static double parseDouble(String expectedDouble) {
        try{
            return Double.parseDouble(expectedDouble);
        }catch(Exception e) {
            System.out.println("NumberUtil  parseDouble [ " + expectedDouble + " ]");
            e.printStackTrace();
            return 0d;
        }
    }
}