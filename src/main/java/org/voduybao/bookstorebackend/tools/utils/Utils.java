package org.voduybao.bookstorebackend.tools.utils;

import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;
import java.security.SecureRandom;

public class Utils {
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "@#$%^&+=";
    private static final String ALL_CHARS = LOWERCASE + UPPERCASE + DIGITS + SPECIAL_CHARS;
    private static final int PASSWORD_LENGTH = 6;

    public static void verifyEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new ResponseException(ResponseErrors.INVALID_EMAIL_FORMAT);
        }
    }

    public static String generateOtp() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        for (int i = 4; i < PASSWORD_LENGTH; i++) {
            password.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }

        return shuffleString(password.toString(), random);
    }

    private static String shuffleString(String input, SecureRandom random) {
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }
}
