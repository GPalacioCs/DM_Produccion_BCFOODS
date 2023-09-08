package com.dosrobles.produccion.utils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsecutivosUtils {

    public static String proximoCodigo(String psCodigoOriginal) {
        if (psCodigoOriginal.isEmpty())
            return "1";

        Pattern identRegex = Pattern.compile(".+(?<!\\d)");
        Pattern numberRegex = Pattern.compile("(?<=\\D)\\d+(?!\\D)");
        Matcher identMatcher = identRegex.matcher(psCodigoOriginal);
        Matcher numberMatcher = numberRegex.matcher(psCodigoOriginal);

        String ident = "";
       if (identMatcher.find()) {
            ident = identMatcher.group();
        }

        String numbers = "";
        if (numberMatcher.find()) {
            numbers = numberMatcher.group();
        }

        int index = numbers.length() - 1;
        char[] nums = numbers.toCharArray();
        while (index >= 0) {

            if (nums[index] == '9') {
                nums[index] = '0';
                index--;
            } else {
                nums[index] = (char) ((nums[index]) + 1);
                break;
            }
        }
        return ident + new String(nums);
    }
}
