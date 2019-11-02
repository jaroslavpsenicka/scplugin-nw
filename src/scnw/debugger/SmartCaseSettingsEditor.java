package scnw.debugger;

import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.*;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.ui.ListCellRendererWrapper;
import com.intellij.ui.components.panels.HorizontalLayout;
import com.intellij.ui.components.panels.VerticalLayout;
import org.jdesktop.swingx.combobox.ListComboBoxModel;
import org.jetbrains.annotations.NotNull;
import scnw.SmartCaseFile;
import scnw.compiler.PsiUtils;
import scnw.psi.SmartCaseProcess;
import scnw.psi.SmartCaseTest;
import scnw.psi.SmartCaseTests;
import scnw.psi.Types;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SmartCaseSettingsEditor extends SettingsEditor<SmartCaseRunConfiguration> {

    private TextFieldWithBrowseButton processChooser;
    private JComboBox<SmartCaseTest> testChooser;

    @Override
    protected void resetEditorFrom(SmartCaseRunConfiguration runConfiguration) {
        Project project = runConfiguration.getProject();
        FileChooserDescriptor filesOnly = new FileChooserDescriptor(true, false, false, false, false, false);
        processChooser.addBrowseFolderListener(new TextBrowseFolderListener(filesOnly, project) {
            protected void onFileChosen(@NotNull VirtualFile chosenFile) {
                super.onFileChosen(chosenFile);
                testChooser.setModel(new ListComboBoxModel<SmartCaseTest>(readTests(chosenFile, project)));
                testChooser.setEnabled(true);
            }
        });
    }

    private List<SmartCaseTest> readTests(VirtualFile file, Project project) {
        PsiManager manager = PsiManager.getInstance(project);
        PsiFile psiFile = manager.findFile(file);
        if (psiFile instanceof SmartCaseFile) {
            SmartCaseProcess process = ((SmartCaseFile) psiFile).findChildByClass(SmartCaseProcess.class);
            if (process != null) {
                SmartCaseTests tests = process.getProcessDefinition().getTests();
                return tests != null ? tests.getTestList() : Collections.emptyList();
            }
        }

        return Collections.emptyList();
    }

    @Override
    protected void applyEditorTo(SmartCaseRunConfiguration runConfiguration) throws ConfigurationException {
        runConfiguration.setProcess(processChooser.getText());
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new VerticalLayout(5));
        LabeledComponent<ComponentWithBrowseButton> processFile = new LabeledComponent<>();
        processFile.setText("Process");
        processFile.setLabelLocation("West");
        processFile.getLabel().setPreferredSize(new Dimension(60, 20));
        this.processChooser = new TextFieldWithBrowseButton();
        this.processChooser.getTextField().setEditable(false);

        processFile.setComponent(processChooser);
        settingsPanel.add(processFile);
        LabeledComponent<JComboBox> test = new LabeledComponent<>();
        test.setText("Test");
        test.setLabelLocation("West");
        test.getLabel().setPreferredSize(new Dimension(60, 20));
        testChooser = new ComboBox<>(new DefaultComboBoxModel<>());
        testChooser.setRenderer(new ListCellRendererWrapper<SmartCaseTest>() {
            public void customize(JList list, SmartCaseTest value, int index, boolean isSelected, boolean cellHasFocus) {
                setText(PsiUtils.getElementOfType(value, Types.IDENTIFIER));
            }
        });
        this.testChooser.setEnabled(false);
        test.setComponent(testChooser);
        settingsPanel.add(test);

        return settingsPanel;
    }

}
