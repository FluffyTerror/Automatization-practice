package org.FluffyTerror.Cucumber.Runner;
import org.junit.platform.suite.api.*;

import static io.cucumber.core.options.Constants.*;

@Suite
@IncludeEngines("cucumber")
@ConfigurationParameters({
        @ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@UI"),
        @ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/Features"),
        @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.FluffyTerror.Cucumber.Steps, org.FluffyTerror.Cucumber.Hooks"),
        @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm,pretty")
})

public class TestRunner {

}
