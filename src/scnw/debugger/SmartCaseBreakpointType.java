package scnw.debugger;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.xdebugger.breakpoints.XBreakpointProperties;
import com.intellij.xdebugger.breakpoints.XLineBreakpoint;
import com.intellij.xdebugger.breakpoints.XLineBreakpointType;
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProvider;
import org.jetbrains.annotations.NotNull;
import scnw.SmartCaseFile;
import scnw.psi.SmartCaseProcess;
import scnw.psi.SmartCaseProcessDefinition;
import scnw.psi.SmartCaseTests;

public class SmartCaseBreakpointType extends XLineBreakpointType<XBreakpointProperties> {

    private XDebuggerEditorsProvider editorsProvider = new SmartCaseDebuggerEditorsProvider();

    public SmartCaseBreakpointType() {
        super("sc", "SmartCase Breakpoints");
    }

    @Override
    public boolean canPutAt(@NotNull VirtualFile file, int line, @NotNull Project project) {
        PsiManager manager = PsiManager.getInstance(project);
        PsiFile psiFile = manager.findFile(file);
        Document document = FileDocumentManager.getInstance().getDocument(file);
        if (psiFile instanceof SmartCaseFile && document != null) {
            SmartCaseFile processFile = (SmartCaseFile) psiFile;
            SmartCaseProcess process = processFile.findChildByClass(SmartCaseProcess.class);
            if (process != null) {
                TextRange testsRange = getTestsRange(process);
                if (testsRange != null) {
                    testsRange.containsRange(document.getLineStartOffset(line), document.getLineEndOffset(line));
                }
            }
        }

        return false;
    }

    private TextRange getTestsRange(SmartCaseProcess process) {
        SmartCaseProcessDefinition processDef = PsiTreeUtil.findChildOfType(process, SmartCaseProcessDefinition.class);
        if (processDef != null) {
            SmartCaseTests tests = PsiTreeUtil.findChildOfType(processDef, SmartCaseTests.class);
            return (tests != null) ? tests.getTextRange() : null;
        }

        return null;
    }

    @Override
    public XDebuggerEditorsProvider getEditorsProvider(@NotNull XLineBreakpoint<XBreakpointProperties> breakpoint, @NotNull Project project) {
        return editorsProvider;
    }

    @Override
    public XBreakpointProperties createBreakpointProperties(@NotNull VirtualFile file, int line) {
        return null;
    }
}