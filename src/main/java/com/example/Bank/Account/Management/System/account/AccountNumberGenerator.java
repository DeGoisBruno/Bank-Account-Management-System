package com.example.Bank.Account.Management.System.account;

import java.util.Random;

//public class AccountNumberGenerator {
//    public static String generateAccountNumber() {
//        Random random = new Random();
//        StringBuilder accountNumber = new StringBuilder();
//        for (int i = 0; i < 10; i++) {
//            accountNumber.append(random.nextInt(10));
//        }
//        return accountNumber.toString();
//    }
//}

public class AccountNumberGenerator {

    public static String generateAccountNumber() {
        StringBuilder start = new StringBuilder("NZ");

        Random value = new Random();

        // Append two random digits after 'NZ'
        start.append(value.nextInt(10));
        start.append(value.nextInt(10));
        start.append(" ");

        int count = 0;
        for (int i = 0; i < 12; i++) {
            int n = value.nextInt(10);
            start.append(n);
            count++;

            // Add a space after every 4 digits (except after the last set of 4)
            if (count == 4 && i != 11) {
                start.append(" ");
                count = 0;
            }
        }

        return start.toString();
    }
}
