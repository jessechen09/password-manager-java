package pwd;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHARS = "!@#$%&*()_+-=[]?{}|";
    private static final SecureRandom random = new SecureRandom();

    private static final int LENGTH = 12;

    public static String generatePassword(boolean hasNumbers, boolean hasSymbols, boolean hasUpperAndLowerCases) {
        StringBuilder allowedCharsBuilder = new StringBuilder(CHAR_LOWER);
        if (hasUpperAndLowerCases) {
            allowedCharsBuilder.append(CHAR_UPPER);
        }
        if (hasNumbers) {
            allowedCharsBuilder.append(NUMBER);
        }
        if (hasSymbols) {
            allowedCharsBuilder.append(OTHER_CHARS);
        }
        String allowedChars = allowedCharsBuilder.toString();

        // Generate the random password
        StringBuilder passwordBuilder = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            char randomChar = allowedChars.charAt(randomIndex);
            passwordBuilder.append(randomChar);
        }
        return passwordBuilder.toString();
    }
}