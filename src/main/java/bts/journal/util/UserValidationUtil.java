package bts.journal.util;

public class UserValidationUtil {

    public static boolean validateEmail(String email) {
        return email != null && !email.isBlank() && email.matches("asd");
    }
}
