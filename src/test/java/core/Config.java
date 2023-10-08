package core;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

public class Config {
    public final static EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();

    public static String getConfigValue(String key) {
        return EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(key);
    }
}
