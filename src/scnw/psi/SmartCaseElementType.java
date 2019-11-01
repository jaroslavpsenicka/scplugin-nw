package scnw.psi;


import com.intellij.psi.tree.IElementType;
import scnw.SmartCaseProcessLanguage;
import org.jetbrains.annotations.*;

public class SmartCaseElementType extends IElementType {

    public SmartCaseElementType(@NotNull @NonNls String debugName) {
        super(debugName, SmartCaseProcessLanguage.INSTANCE);
    }

}