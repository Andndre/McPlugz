package me.andre.mcplugz;

public class StrH {

    public static String capitalize(String str){
        String[] strArr = str.split(" ");
        StringBuilder result = new StringBuilder();

        for (String s: strArr){
            result.append(String.valueOf(s.charAt(0)).toUpperCase()).append(s.substring(1)).append(" ");
        }

        return result.substring(0, result.length() - 1);
    }
}
