package org.xi.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String[] args) {
        System.out.print(setParams("#day# #day+1# #day -1#      #hour -1#"));
    }

    public static String setParams(String sql) {
        StringBuffer sb = new StringBuffer();
        Pattern day = Pattern.compile("#\\s*(day|hour)(\\s*(\\+|\\-)\\s*\\d+)?\\s*#");
        Matcher matcher = day.matcher(sql);
        while (matcher.find()) {
            String expression = matcher.group().replaceAll("\\s*", "").replaceAll("#", "");
            int num = 0;
            if (expression.contains("+")) {
                num = Integer.parseInt(expression.split("\\+")[1]);
            } else if (expression.contains("-")) {
                num = -Integer.parseInt(expression.split("\\-")[1]);
            }

            String v = expression.startsWith("day") ? getDate(num) : getHour(num);
            matcher.appendReplacement(sb, v);
        }

        matcher.appendTail(sb);
        return sb.toString();
    }

    private static String getDate(int num) {
        return "";
    }

    private static String getHour(int num) {
        return "";
    }
}
