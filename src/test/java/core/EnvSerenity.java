package core;

public class EnvSerenity extends config {

    //bootstrap
    public static final String basePetURI = environmentVariables.getProperty("baseURI") + "pet";
    public static final String petFileBodiesRoot = environmentVariables.getProperty("petFileBodiesRoot");
}
