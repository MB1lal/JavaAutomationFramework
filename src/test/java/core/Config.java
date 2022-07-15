package core;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class Config {
    public final static EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();

    public static String getConfigValue(String key) {
        return EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(key);
    }
}
