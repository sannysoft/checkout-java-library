package com.checkout.helpers;

public class AppSettings {
    public static String secretKey;//"sk_test_32b9cb39-1cd6-4f86-b750-7069a133657d"
    public static String publicKey;//"pk_test_2997d616-471e-48a5-ba86-c775ed3ag38a"
    public static boolean debugMode = false;
    public static int connectTimeout = 60;
    public static int readTimeout = 60;
    static String baseApiUrl = "";
    private static double clientVersion = 1.0;
    public static String clientUserAgentName = String.format("Checkout-JavaLibraryClient/%s", clientVersion);

    private static String liveUrl = "https://api2.checkout.com/v2/%s";
    private static String sandboxUrl = "https://sandbox.checkout.com/api2/v2/%s";

    public static void SetEnvironment(Environment env) {
        switch (env) {
            case LIVE:
                baseApiUrl = liveUrl;
                break;
            case SANDBOX:
                baseApiUrl = sandboxUrl;
                break;
        }
    }

}
