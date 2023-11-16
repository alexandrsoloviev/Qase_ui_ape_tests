package config;

import org.aeonbits.owner.ConfigFactory;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Project {
    public static ProjectConfig config = ConfigFactory.create(ProjectConfig.class);



    static {
        System.out.println(System.getProperties());
        validateProperty(config.runIn(), "runIn");
        switch (config.runIn()) {
            case "browser_selenoid":
                validateProperty(config.remoteDriver(), "remoteDriver");
                System.out.println(config.runIn());
                System.out.println(config.remoteDriver());
                System.out.println(config.baseUrl());
                System.out.println(config.browser());
                System.out.println(config.browserSize());
            case "browser_local":
                validateProperty(config.browser(), "browser");
                System.out.println(config.runIn());
                System.out.println(config.baseUrl());
                System.out.println(config.browser());
                System.out.println(config.browserSize());

                break;
            case "android_selenoid":
                validateProperty(config.remoteDriver(), "remoteDriver");
            case "android_browserstack":
                validateProperty(config.user(), "user");
                validateProperty(config.key(), "key");
                break;
            case "android_emulator":
            case "android_real":
                validateProperty(config.remoteDriver(), "remoteDriver");
                validateProperty(config.deviceName(), "deviceName");
                validateProperty(config.platformVersion(), "platformVersion");
                break;
            default:
                throw new IllegalStateException("Unexpected 'runIn' value: " + config);
        }

    }

    public static void validateProperty(String propertyValue, String propertyName) {
        assertThat(propertyValue).withFailMessage("'%s' value is null or empty", propertyName).isNotEmpty();
    }

    public static boolean isRemoteDriver() {
        return !(config.remoteDriver() == null) && !config.remoteDriver().isEmpty();
    }
}
