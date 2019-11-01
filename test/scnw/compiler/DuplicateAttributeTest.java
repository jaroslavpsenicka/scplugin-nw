package scnw.compiler;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VFileProperty;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.PsiManagerEx;
import com.intellij.testFramework.LightVirtualFile;
import com.intellij.testFramework.ParsingTestCase;
import junit.framework.TestCase;
import org.mockito.Mockito;
import scnw.SmartCaseParserDefinition;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.eq;

public class DuplicateAttributeTest extends ParsingTestCase {

    private Module module;
    private PsiManagerEx psiManager;

    public DuplicateAttributeTest() {
        super("", "sc", new SmartCaseParserDefinition());
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        LightVirtualFile file = new LightVirtualFile("Attr", this.myLanguage, loadFile("Attr.sc"));
        PsiFile entity = this.createFile(file);

        VirtualFile src = Mockito.mock(VirtualFile.class);
        Mockito.when(src.isDirectory()).thenReturn(true);
        Mockito.when(src.isValid()).thenReturn(true);
        Mockito.when(src.is(eq(VFileProperty.SYMLINK))).thenReturn(false);
        Mockito.when(src.getChildren()).thenReturn(new VirtualFile[] { entity.getVirtualFile() });

        ModuleRootManager rootManager = Mockito.mock(ModuleRootManager.class);
        Mockito.when(rootManager.getSourceRoots(anyBoolean())).thenReturn(new VirtualFile[] { src });
        module = Mockito.mock(Module.class);
        Mockito.when(module.getComponent(eq(ModuleRootManager.class))).thenReturn(rootManager);
        psiManager = Mockito.mock(PsiManagerEx.class);
        Mockito.when(psiManager.findFile(eq(file))).thenReturn(entity);
    }

    public void testDuplicateAttribute() {
        try {
            new Compiler().findProcess(module, psiManager);
            TestCase.fail();
        } catch (CompileException ex) {
            TestCase.assertEquals("Duplicate attribute: one", ex.getCompilationMessage().getText());
        }
    }

    @Override
    protected String getTestDataPath() {
        return "test/resources/compiler";
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }

}
