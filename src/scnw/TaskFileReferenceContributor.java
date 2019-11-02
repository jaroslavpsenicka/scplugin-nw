package scnw;

import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import scnw.compiler.PsiUtils;
import scnw.psi.SmartCaseTask;
import scnw.psi.Types;

import static com.intellij.patterns.PsiJavaPatterns.psiElement;

public class TaskFileReferenceContributor extends PsiReferenceContributor {

    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar reg) {
        reg.registerReferenceProvider(psiElement(Types.FILE_REF), new PsiReferenceProvider() {
            public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                if (element.getContainingFile().getVirtualFile() != null) try {

                    String filename = PsiUtils.unwrap(element.getText());
                    VirtualFile parentDir = element.getContainingFile().getVirtualFile().getParent();
                    VirtualFile refFile = parentDir.findFileByRelativePath(filename);
                    if (refFile != null && refFile.exists()) {
                        return new PsiReference[] {
                            new JsonFileReference(element, new TextRange(0, filename.length()), refFile)
                        };
                    }

                } catch (Exception ex) {
                    System.out.println("Error processing reference to " + element);
                }

                return PsiReference.EMPTY_ARRAY;
            }
        });
    }

}