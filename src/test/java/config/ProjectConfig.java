package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
        "classpath:config/project-{runIn}.properties"})
public interface ProjectConfig extends Config {
//    @DefaultValue("browser_local")
    String runIn();

    String remoteDriver();

    String baseUrl();

    String browser();

    String browserSize();

    String user();

    String key();

    String deviceName();

    String platformVersion();
}
