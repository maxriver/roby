package it.max.roby.risorse;

public class Utility {

    public static String removeUselessChars(String word) {
        return word.replace(",", " ").replace(";", " ").replace("?", " ").replace(".", " ").trim();
    }
}
