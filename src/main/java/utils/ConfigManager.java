package utils;


import java.io.FileInputStream;
import java.util.Properties;

public class ConfigManager {

private static final Properties properties = new Properties();

    public static String env = System.getProperty("env").toLowerCase();

    static {
        switch (env)
        {
            case "m5pre":
            {
                try {
                    System.out.println("env: "+env);
                    FileInputStream file = new FileInputStream("src/main/java/utils/configM5Pre.properties");
                    properties.load(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "b1pre":
            {
                try {
                    System.out.println("env: "+env);
                    FileInputStream file = new FileInputStream("src/main/java/utils/configB1Pre.properties");
                    properties.load(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            default:
                System.out.println("wrong env");
        }

    }

    public static String get(String key) {
        return properties.getProperty(key);
    }


}
