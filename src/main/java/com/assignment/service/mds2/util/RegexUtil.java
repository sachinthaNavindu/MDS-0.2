package com.assignment.service.mds2.util;

public class RegexUtil {
    public static boolean checkUsernames(String username) {
        String userNameRegex = "^[a-zA-Z]+$";

        if (username.matches(userNameRegex)) {
            return true;
        }else{
            return false;
        }
    }

    public static boolean checkPasswords(String password) {
        String passWordRegex ="^[a-zA-Z0-9@#%$]+$";
        if (password.matches(passWordRegex)) {
            return true;
        }else{
            return false;
        }
    }

    public static boolean checkEmail(String email) {
        String emailRegex ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|org|net|edu|gov|mil)$";

        if (email.matches(emailRegex)) {
            return true;
        }else{
            return false;
        }
    }
}
