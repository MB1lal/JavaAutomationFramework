package core;

public class EnvSerenity extends config {

    //bootstrap
    public static final String webDriver = environmentVariables.getProperty("webdriver");

    public static final String basePetURI = environmentVariables.getProperty("baseURI") + "pet";
}
