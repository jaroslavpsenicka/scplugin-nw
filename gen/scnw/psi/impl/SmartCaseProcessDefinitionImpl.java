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

public class SmartCaseProcessDefinitionImpl extends ASTWrapperPsiElement implements SmartCaseProcessDefinition {

  public SmartCaseProcessDefinitionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SmartCaseVisitor visitor) {
    visitor.visitProcessDefinition(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SmartCaseVisitor) accept((SmartCaseVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SmartCaseAttributes getAttributes() {
    return findChildByClass(SmartCaseAttributes.class);
  }

  @Override
  @Nullable
  public SmartCaseTasks getTasks() {
    return findChildByClass(SmartCaseTasks.class);
  }

}
