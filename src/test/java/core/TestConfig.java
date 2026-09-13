package core;

import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

/**
 * Lazy access to framework configuration. Unlike the old static-field style,
 * nothing is read at class-load time, so a missing key fails here with a
 * clear message instead of an obscure ExceptionInInitializerError.
 * System properties ({@code -Dkey=value}) always win over the config files.
 */
public final class TestConfig {

    private static volatile EnvironmentVariables environmentVariables;

    private TestConfig() {
    }

    private static EnvironmentVariables environment() {
        if (environmentVariables == null) {
            synchronized (TestConfig.class) {
                if (environmentVariables == null) {
                    environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
                }
            }
        }
        return environmentVariables;
    }

    public static String get(String key) {
        String systemValue = System.getProperty(key);
        if (systemValue != null) {
            return systemValue;
        }
        String value = environment().getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Missing configuration key: " + key);
        }
        return value;
    }

    public static String baseUri() {
        return get("baseURI");
    }

    public static String petUri() {
        return baseUri() + "pet";
    }

    public static String petStoreUri() {
        return baseUri() + "store";
    }

    public static String userUri() {
        return baseUri() + "user";
    }

    public static String petFileBodiesRoot() {
        return get("petFileBodiesRoot");
    }
}
