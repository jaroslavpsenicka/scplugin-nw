// This is a generated file. Not intended for manual editing.
package scnw.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static scnw.psi.Types.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import scnw.psi.*;

public class SmartCaseAttributeImpl extends ASTWrapperPsiElement implements SmartCaseAttribute {

  public SmartCaseAttributeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SmartCaseVisitor visitor) {
    visitor.visitAttribute(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SmartCaseVisitor) accept((SmartCaseVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SmartCaseDefaultValue getDefaultValue() {
    return findChildByClass(SmartCaseDefaultValue.class);
  }

}
