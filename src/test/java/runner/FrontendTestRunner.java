package runner;

import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.ConfigurationParameters;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/frontend")
@ConfigurationParameters({
    @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps"),
    @ConfigurationParameter(
            key = FILTER_TAGS_PROPERTY_NAME,
            value = "@test and not @ignore and @herokuapp and not @download")
})
public class FrontendTestRunner {}
