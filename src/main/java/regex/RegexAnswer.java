package regex;// File: RegexPractice.java
// How to use:
// 1) Create a new project (no Maven needed).
// 2) New Java Class -> paste this entire file content.
// 3) Run main(). Read outputs. Compare with comments.
// 4) Then copy the TODO class at the bottom into a new file to practice.

import java.util.*;
import java.util.regex.*;
public class RegexAnswer {
    public static void main(String[] args){
        // --- Quick Reference (common patterns) ---
        // .                -> any character
        // [a-z]            -> Lowercase letters
        // [A-Z]            -> Uppercase letters
        // [0-9] or \\d     -> All digit
        // \\s              -> whitespace (space/tab/newline)
        // \\S              -> non-whitespace
        // \\w              -> word char [a-zA-Z0-9_]
        // \\W              -> Non-word char
        // [^...]           -> Not these chars
        // ^ and $          -> anchors (start/end of string)
        //(abc)             → capture group
        //(?:abc)           → non-capturing group
        // (a|b)            -> Group
        //* → 0 or more
        //+ → 1 or more
        //? → 0 or 1 (optional)

        // -----------------------------------------
        // 1) Remove all non-alphanumeric
        // -----------------------------------------
        String s1 = "A man, a plan, a canal: Panama! 123";
        String r1 = removeNonAlnum(s1);
        println("1) removeNonAlnum", r1, "AmanaplanacanalPanama123");

        // -----------------------------------------
        // 2) Remove whitespace (space/tab/newline)
        // -----------------------------------------
        String s2 = "A man \t a plan \n a canal";
        String r2 = removeWhitespace(s2);
        println("2) removeWhitespace", r2, "Amanaplanacanal");

        // -----------------------------------------
        // 3) Keep only digits
        // -----------------------------------------
        String s3 = "Order# ABC-123-XYZ, price = 4599.";
        String r3 = keepOnlyDigits(s3);
        println("3) keepOnlyDigits", r3, "1234599");

        // -----------------------------------------
        // 4) Check if string is all digits
        // -----------------------------------------
        String s4a = "12345";
        String s4b = "12a45";
        println("4) isAllDigits(12345)", String.valueOf(isAllDigits(s4a)), "true");
        println("4) isAllDigits(12a45)", String.valueOf(isAllDigits(s4b)), "false");

        // -----------------------------------------
        // 5) Simple email validation (basic)
        // -----------------------------------------
        String e1 = "user.name+tag@sub.domain.com";
        String e2 = "bad@@domain..com";
        println("5) isSimpleEmail(user.name+tag@sub.domain.com)", String.valueOf(isSimpleEmail(e1)), "true");
        println("5) isSimpleEmail(bad@@domain..com)", String.valueOf(isSimpleEmail(e2)), "false");

        // -----------------------------------------
        // 6) Split by spaces and punctuation into tokens
        // -----------------------------------------
        String s6 = "Hello, world; this.is  regex 101!";
        String[] r6 = splitTokens(s6);
        println("6) splitTokens count", String.valueOf(r6.length), "5");
        // Expected tokens: ["Hello","world","this","is","regex","101"] (depending on how you split)
        // Here we split on [\\s,.;!?]+, so "101" stays separate. Count may vary if you include digits as part of words.

        // -----------------------------------------
        // 7) Normalize multiple spaces to single space
        // -----------------------------------------
        String s7 = "Hello    world   Java   regex";
        String r7 = normalizeSpaces(s7);
        println("7) normalizeSpaces", r7, "Hello world Java regex");

        // -----------------------------------------
        // 8) Extract all emails in a text
        // -----------------------------------------
        String s8 = "Contact: a@x.com, b.c-d+z@sub.y.org; invalid: x@@y, a@b";
        List<String> r8 = extractEmails(s8);
        println("8) extractEmails count", String.valueOf(r8.size()), "2");

        // -----------------------------------------
        // 9) Mask phone numbers (keep last 4 digits)
        // Format: replace digits with '*' except last 4
        // -----------------------------------------
        String s9 = "Call me at 9123-4567 or 65 8123 9999.";
        String r9 = maskPhoneKeepLast4(s9);
        // Example mask: ****-**** or ** **** **** -> exact look may vary, goal: only last 4 digits readable
        println("9) maskPhoneKeepLast4", r9, "(visually check: only last 4 digits per phone remain visible)");

        // -----------------------------------------
        // 10) Find all integers in text
        // -----------------------------------------
        String s10 = "IDs: 101, 202 and 303; rev=04";
        List<Integer> r10 = findAllIntegers(s10);
        println("10) findAllIntegers", r10.toString(), "[101, 202, 303, 04]");

        // -----------------------------------------
        // 11) Remove duplicate punctuation (e.g., "Hello!!!" -> "Hello!")
        // -----------------------------------------
        String s11 = "What??? Really!! No.... way---";
        String r11 = collapseRepeatingPunct(s11);
        println("11) collapseRepeatingPunct", r11, "What? Really! No. way-");

        // -----------------------------------------
        // 12) Validate a simple date format (YYYY-MM-DD)
        // -----------------------------------------
        String d1 = "2025-09-05";
        String d2 = "25-9-5";
        println("12) isDateYYYYMMDD(2025-09-05)", String.valueOf(isDateYYYYMMDD(d1)), "true");
        println("12) isDateYYYYMMDD(25-9-5)", String.valueOf(isDateYYYYMMDD(d2)), "false");

        System.out.println("\nAll demo checks printed. Compare actual vs expected shown on the right.");
    }

    // -------- Implementations (Ready to run) --------

    // 1) Remove all non-alphanumeric
    static String removeNonAlnum(String s) {
        if (s == null) return null;
        return s.replaceAll("[^a-zA-Z0-9]", "");
    }

    // 2) Remove whitespace (spaces, tabs, newlines)
    static String removeWhitespace(String s) {
        if (s == null) return null;
        return s.replaceAll("\\s", "");
    }

    // 3) Keep only digits
    static String keepOnlyDigits(String s) {
        if (s == null) return null;
        return s.replaceAll("\\D", "");
    }

    // 4) Check if string is all digits
    static boolean isAllDigits(String s) {
        if (s == null) return false;
        return s.matches("\\d+");
    }

    // 5) Simple email check (basic pattern, not RFC-perfect but fine for interviews)
    static boolean isSimpleEmail(String s) {
        if (s == null) return false;
        return s.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    // 6) Split into tokens by spaces/punct
    static String[] splitTokens(String s) {
        if (s == null) return new String[0];
        return s.trim().split("[\\s,.;!?]+");
    }

    // 7) Normalize multiple spaces to a single space
    static String normalizeSpaces(String s) {
        if (s == null) return null;
        return s.trim().replaceAll("\\s+", " ");
    }

    // 8) Extract all emails
    static List<String> extractEmails(String s) {
        if (s == null) return List.of();
        Pattern p = Pattern.compile("[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+");
        Matcher m = p.matcher(s);
        List<String> out = new ArrayList<>();
        while (m.find()) out.add(m.group());
        return out;
    }

    // 9) Mask phone digits, keep only last 4 visible for each contiguous digit group
    //    Strategy: replace any digit that has >= 4 digits to its right (in the same run) with '*'
    static String maskPhoneKeepLast4(String s) {
        if (s == null) return null;
        // Replace each run of digits, keeping last 4
        Pattern run = Pattern.compile("\\d+");
        Matcher m = run.matcher(s);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String digits = m.group();
            if (digits.length() <= 4) {
                m.appendReplacement(sb, digits);
            } else {
                String masked = "*".repeat(digits.length() - 4) + digits.substring(digits.length() - 4);
                m.appendReplacement(sb, masked);
            }
        }
        m.appendTail(sb);
        return sb.toString();
    }

    // 10) Find all integers in text
    static List<Integer> findAllIntegers(String s) {
        if (s == null) return List.of();
        Matcher m = Pattern.compile("\\d+").matcher(s);
        List<Integer> out = new ArrayList<>();
        while (m.find()) {
            out.add(Integer.parseInt(m.group()));
        }
        return out;
    }

    // 11) Collapse repeated punctuation: "!!!" -> "!", "???" -> "?", "..." -> "."
    //     Keep only one char for runs of the same punctuation mark.
    static String collapseRepeatingPunct(String s) {
        if (s == null) return null;
        // Replace runs of any of these punctuations with a single one.
        return s.replaceAll("([!?\\.-])\\1+", "$1");
    }

    // 12) Validate YYYY-MM-DD (not checking calendar validity, only pattern)
    static boolean isDateYYYYMMDD(String s) {
        if (s == null) return false;
        return s.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }

    // --- tiny helper for printing expected vs actual ---
    static void println(String label, String actual, String expected) {
        System.out.printf("%-35s -> %-30s  (expected: %s)%n", label, actual, expected);
    }
}