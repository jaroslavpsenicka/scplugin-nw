package scnw.compiler;

import com.intellij.openapi.compiler.CompileContext;
import com.intellij.openapi.compiler.CompileTask;
import com.intellij.openapi.compiler.CompilerManager;

public class SmartCaseCompileTask implements CompileTask {

    public SmartCaseCompileTask(CompilerManager compileManager) {
        compileManager.addAfterTask(this);
    }

    @Override
    public boolean execute(CompileContext context) {
        Compiler.getInstance().compile(context);
        return true;
    }
}