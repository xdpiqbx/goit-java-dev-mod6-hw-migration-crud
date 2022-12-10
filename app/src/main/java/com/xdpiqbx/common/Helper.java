package com.xdpiqbx.common;

import io.github.cdimascio.dotenv.Dotenv;

public class Helper {
    private static Dotenv dotenv = Dotenv.load();
    public static String env(String enVar){
        return dotenv.get(enVar);
    }
}
