package scnw.debugger;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.util.LocalTimeCounter;
import com.intellij.xdebugger.XExpression;
import com.intellij.xdebugger.XSourcePosition;
import com.intellij.xdebugger.evaluation.EvaluationMode;
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import scnw.SmartCaseFileType;

public class SmartCaseDebuggerEditorsProvider extends XDebuggerEditorsProvider {

    @NotNull
    @Override
    public FileType getFileType() {
        return SmartCaseFileType.INSTANCE;
    }

    @NotNull
    @Override
    public Document createDocument(@NotNull Project project, @NotNull XExpression expression,
        @Nullable XSourcePosition sourcePosition, @NotNull EvaluationMode mode) {

        final PsiFile psiFile = PsiFileFactory.getInstance(project)
            .createFileFromText("SmartCaseExpr." + SmartCaseFileType.INSTANCE.getDefaultExtension(),
                SmartCaseFileType.INSTANCE, "", LocalTimeCounter.currentTime(), true);
        return PsiDocumentManager.getInstance(project).getDocument(psiFile);
    }
}