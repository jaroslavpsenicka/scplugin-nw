package scnw.compiler;

import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.compiler.CompilationException;
import com.intellij.openapi.compiler.CompilerMessageCategory;
import com.intellij.psi.PsiElement;
import org.junit.Assert;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CompileException extends RuntimeException {

    private final CompilationMessage message;

    public CompileException(String message, PsiElement element) {
        Assert.assertNotNull("no element given", element);
        this.message = new CompilationMessage(message, element, HighlightSeverity.ERROR);
    }

    public CompileException(String message, PsiElement element, HighlightSeverity severity) {
        Assert.assertNotNull("no element given", element);
        this.message = new CompilationMessage(message, element, severity);
    }

    public CompilationMessage getCompilationMessage() {
        return message;
    }

}
