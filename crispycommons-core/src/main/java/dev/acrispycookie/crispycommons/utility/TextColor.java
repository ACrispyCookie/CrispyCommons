package dev.acrispycookie.crispycommons.utility;

public class TextColor {

    public static String translateChar(char c, String s) {
        return s.replace(c, '§');
    }
    public static String translateChar(String s) {
        return s.replace('&', '§');
    }

    public static String removeColor(char c, String s) {
        return s.replace('§', c);
    }

    public static String removeColor(String s) {
        return s.replace('§', '&');
    }
}
