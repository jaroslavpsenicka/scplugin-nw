package scnw.debugger;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;

public class SmartCaseConfigurationFactory extends ConfigurationFactory {

    public SmartCaseConfigurationFactory(SmartCaseRunConfigurationType type) {
        super(type);
    }

    @Override
    public RunConfiguration createTemplateConfiguration(Project project) {
        return new SmartCaseRunConfiguration(project, this, "SmartCase");
    }

    @Override
    public String getName() {
        return "SmartCase configuration factory";
    }

}
