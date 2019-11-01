package scnw;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SmartCaseFile extends PsiFileBase {

  public SmartCaseFile(@NotNull FileViewProvider viewProvider) {
    super(viewProvider, SmartCaseProcessLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public FileType getFileType() {
    return SmartCaseFileType.INSTANCE;
  }

  @Override
  public String toString() {
    return "SmartCase File";
  }

  @Override
  public Icon getIcon(int flags) {
    return super.getIcon(flags);
  }
}