package scnw.compiler;

import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;

import java.util.List;
import java.util.stream.Collectors;

public class PsiUtils {

    public static String getElementOfType(PsiElement el, IElementType type) {
        PsiElement element = PsiTreeUtil.findChildrenOfType(el, PsiElement.class).stream()
            .filter(e -> e.getNode().getElementType() == type)
            .findFirst().orElse(null);
        return element != null ? element.getText() : null;
    }

    public static String getElementOfType(PsiElement el, IElementType type, int idx) {
        List<PsiElement> elements = PsiTreeUtil.findChildrenOfType(el, PsiElement.class).stream()
            .filter(e -> e.getNode().getElementType() == type)
            .collect(Collectors.toList());
        if (idx < elements.size()) return elements.get(idx).getText();
        throw new IllegalArgumentException("Index " + idx + " does not exist in " + elements);
    }

    public static String unwrap(String name) {
        if (name != null && name.startsWith("\"") && name.endsWith("\"")) {
            return name.substring(1, name.length()-1);
        }

        return name;
    }

}
