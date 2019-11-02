package scnw.debugger;

import com.intellij.xdebugger.XDebugProcess;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProvider;
import org.jetbrains.annotations.NotNull;

public class SmartCaseDebugProcess extends XDebugProcess {

    private XDebuggerEditorsProvider editorsProvider = new SmartCaseDebuggerEditorsProvider();

    public SmartCaseDebugProcess(XDebugSession session) {
        super(session);
    }

    public XDebuggerEditorsProvider getEditorsProvider() {
        return editorsProvider;
    }
}
