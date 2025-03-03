package utils;

import java.util.Random;

public class CommonFunction {
    public static String userId;
    public static String token;
    public static int RandomNumber()
    {
        Random random = new Random();
        return random.nextInt(99999);
    }
    public static int RandomPhoneNumber()
    {
        Random random = new Random();
        return random.nextInt(999999999);
    }
}
