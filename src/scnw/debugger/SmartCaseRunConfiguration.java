package scnw.debugger;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunConfigurationBase;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.remoteServer.configuration.deployment.DeploymentSourceType;
import com.intellij.remoteServer.impl.configuration.deployment.DeployToServerRunConfiguration;
import com.intellij.util.xmlb.XmlSerializer;
import com.intellij.util.xmlb.annotations.Attribute;
import com.intellij.util.xmlb.annotations.Tag;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SmartCaseRunConfiguration extends RunConfigurationBase {

    private String process;
    private String test;

    public SmartCaseRunConfiguration(Project project, SmartCaseConfigurationFactory factory, String name) {
        super(project, factory, name);
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @NotNull
    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new SmartCaseSettingsEditor();
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {

    }

    @Override
    public void readExternal(@NotNull Element element) throws InvalidDataException {
        super.readExternal(element);
        ConfigurationState state = XmlSerializer.deserialize(element, ConfigurationState.class);
        this.process = state.process;
        this.test = state.test;
    }

    @Override
    public void writeExternal(@NotNull Element element) throws WriteExternalException {
        ConfigurationState state = new ConfigurationState();
        state.process = this.process;
        state.test = this.test;
        XmlSerializer.serializeInto(state, element, null);
        super.writeExternal(element);
    }

    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment executionEnvironment) throws ExecutionException {
        return null;
    }

    public static class ConfigurationState {
        @Attribute("process")
        public String process;
        @Attribute("test")
        public String test;

        public ConfigurationState() {
        }
    }

}
