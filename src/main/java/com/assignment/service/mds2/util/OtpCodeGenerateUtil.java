package com.assignment.service.mds2.util;

import java.util.Random;

public class OtpCodeGenerateUtil {
    public static String generateOtp() {
        Random rand = new Random();
        String otp = String.valueOf(rand.nextInt(10000));
        String otp2 = "";
        for (int i = 0; i < 3; i++) {
            otp2 += (char) ('A' + (rand.nextInt(26)));
        }
        String finalOtp = otp2+""+otp;
            return finalOtp;
    }

}
