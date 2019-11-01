package scnw;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import scnw.compiler.CompilationMessage;
import scnw.compiler.CompileException;
import scnw.compiler.Compiler;
import org.jetbrains.annotations.NotNull;
import scnw.compiler.PsiUtils;
import scnw.psi.*;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SmartCaseAnnotator implements Annotator {

    private final Set<String> knownTypes;

    public SmartCaseAnnotator() {
        knownTypes = Arrays.stream(Compiler.TYPES.values())
        .map(e -> e.name())
        .collect(Collectors.toSet());
    }

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {

        Compiler compiler = Compiler.getInstance();
        SmartCaseAttribute attr = element instanceof SmartCaseAttribute ? (SmartCaseAttribute) element :
            element.getParent() instanceof SmartCaseAttribute ? (SmartCaseAttribute) element.getParent() :
            null;
        SmartCaseTask task = element instanceof SmartCaseTask ? (SmartCaseTask) element :
            element.getParent() instanceof SmartCaseTask ? (SmartCaseTask) element.getParent() :
            null;

        try {

            // Attributes
            if (attr != null) {
                compiler.verifyAttributeType(attr, knownTypes);
                compiler.verifyDuplicateAttributes((SmartCaseAttributes) attr.getParent());
            }

            // Tasks
            if (task != null) {
                compiler.verifyDuplicateTasks((SmartCaseTasks) task.getParent());
            }

        } catch (CompileException ex) {
            CompilationMessage message = ex.getCompilationMessage();
            holder.createAnnotation(message.getSeverity(), message.getRange(), message.getText());
        }
    }

}