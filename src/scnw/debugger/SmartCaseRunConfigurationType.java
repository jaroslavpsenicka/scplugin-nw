package scnw.debugger;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;
import scnw.Icons;

import javax.swing.*;

public class SmartCaseRunConfigurationType implements ConfigurationType {

    @Override
    public String getDisplayName() {
        return "SmartCase";
    }

    @Override
    public String getConfigurationTypeDescription() {
        return "SmartCase Run Configuration Type";
    }

    @Override
    public Icon getIcon() {
        return Icons.FILE;
    }

    @NotNull
    @Override
    public String getId() {
        return "DEMO_RUN_CONFIGURATION";
    }

    @Override
    public ConfigurationFactory[] getConfigurationFactories() {
        return new ConfigurationFactory[] {
            new SmartCaseConfigurationFactory(this)
        };
    }

}
