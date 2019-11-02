package scnw.debugger;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.GenericProgramRunner;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.xdebugger.XDebugProcess;
import com.intellij.xdebugger.XDebugProcessStarter;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.xdebugger.XDebuggerManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SmartCaseRunner extends GenericProgramRunner {

    @NotNull
    @Override
    public String getRunnerId() {
        return "SmartCaseRunner";
    }

    @Override
    public boolean canRun(@NotNull String executorId, @NotNull RunProfile profile) {
        return executorId.equals("Debug") && profile instanceof SmartCaseRunConfiguration;
    }

    @Override
    public void execute(@NotNull ExecutionEnvironment environment, @Nullable Callback callback) throws ExecutionException {
        super.execute(environment, callback);
    }

    @Override
    protected RunContentDescriptor doExecute(@NotNull RunProfileState state, @NotNull ExecutionEnvironment env)
        throws ExecutionException {
        FileDocumentManager.getInstance().saveAllDocuments();
        return createContentDescriptor(state, env);
    }

    protected RunContentDescriptor createContentDescriptor(final RunProfileState runProfileState,
        final ExecutionEnvironment environment) throws ExecutionException {
        XDebuggerManager debugger = XDebuggerManager.getInstance(environment.getProject());
        XDebugSession debugSession = debugger.startSession(environment, new SmartCaseDebugStarter());
        return debugSession.getRunContentDescriptor();
    }

    private static class SmartCaseDebugStarter extends XDebugProcessStarter {
        public XDebugProcess start(@NotNull final XDebugSession session) throws ExecutionException {
            return new SmartCaseDebugProcess(session);
        }
    }
    
}