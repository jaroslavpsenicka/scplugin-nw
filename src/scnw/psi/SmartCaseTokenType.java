package scnw.psi;

import com.intellij.psi.tree.IElementType;
import scnw.SmartCaseProcessLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class SmartCaseTokenType extends IElementType {

    public SmartCaseTokenType(@NotNull @NonNls String debugName) {
        super(debugName, SmartCaseProcessLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "SmartCaseTokenType." + super.toString();
    }
}

