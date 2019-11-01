package scnw;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import scnw.psi.SmartCaseProcessDefinition;
import scnw.psi.Types;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class ProcessDefinitionCompletionContributor extends CompletionContributor {

    public ProcessDefinitionCompletionContributor() {
        PsiElementPattern.Capture<PsiElement> place = psiElement();
        extend(CompletionType.BASIC, place, new ProcessDefinitionProvider());
    }

    private static class ProcessDefinitionProvider extends CompletionProvider<CompletionParameters> {
        public void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet resultSet) {
            resultSet.addElement(LookupElementBuilder.create("task"));
            resultSet.addElement(LookupElementBuilder.create("transition"));
        }
    }
}

