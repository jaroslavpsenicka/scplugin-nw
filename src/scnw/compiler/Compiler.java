package scnw.compiler;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.compiler.CompileContext;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;
import scnw.SmartCaseFile;
import scnw.psi.*;

import java.util.*;
import java.util.stream.Collectors;

public class Compiler {

    private static Compiler INSTANCE = new Compiler();
    public enum TYPES {
        String, Integer, Boolean, List, Map
    };

    public static Compiler getInstance() {
        return INSTANCE;
    }

    public void compile(CompileContext context) {
        Arrays.stream(ModuleManager.getInstance(context.getProject()).getModules())
            .forEach(m -> compile(m,context));
    }

    void compile(Module module, CompileContext context) {
        System.out.println("Compiling " + module);
        PsiManager manager = PsiManager.getInstance(module.getProject());
        Application application = ApplicationManager.getApplication();
        application.invokeLater(() -> application.runWriteAction(() -> {
            try {
                OutputFileBuilder builder = compile(module, manager);
                builder.generateTo(context.getModuleOutputDirectory(module));
            } catch (Exception ex) {
                System.out.println("Error compiling project");
                ex.printStackTrace();
            }
        }));
    }

    OutputFileBuilder compile(Module module, PsiManager manager) {
        return OutputFileBuilder.forModule(module)
            .withProcess(findProcess(module, manager));
    }

    SmartCaseProcess findProcess(Module module, PsiManager manager) {
        List<VirtualFile> files = findFiles(module, manager, SmartCaseProcess.class);
        if (files.size() == 0) {
            throw new IllegalStateException("No process found");
        } else if (files.size() > 1) {
            throw new IllegalStateException("Mutliple processes found");
        }

        SmartCaseFile processFile = (SmartCaseFile) manager.findFile(files.get(0));
        SmartCaseProcess process = processFile.findChildByClass(SmartCaseProcess.class);
        if (process == null) throw new CompileException("No process found", processFile);

        SmartCaseAttributes attributes = process.getProcessDefinition().getAttributes();
        verifyDuplicateAttributes(attributes);
        verifyAttributeTypes(attributes);
        SmartCaseTasks tasks = process.getProcessDefinition().getTasks();
        verifyDuplicateTasks(tasks);

        return process;
    }

    public void verifyDuplicateAttributes(SmartCaseAttributes attributes) {
        if (attributes != null) {
            Set<String> names = new HashSet<>();
            attributes.getAttributeList().forEach(a -> {
                String name = PsiUtils.getElementOfType(a, Types.IDENTIFIER, 1);
                if (!names.contains(name)) names.add(name);
                else throw new CompileException("Duplicate attribute: " + name, a);
            });
        }
    }

    public void verifyAttributeTypes(SmartCaseAttributes attributes) {
        if (attributes != null) {
            Set<String> knownTypes = Arrays.stream(TYPES.values())
                .map(e -> e.name())
                .collect(Collectors.toSet());
            attributes.getAttributeList().stream()
                .forEach(e -> verifyAttributeType(e, knownTypes));
        }
    }

    public void verifyAttributeType(SmartCaseAttribute attribute, Set<String> knownTypes) {
        String type = PsiUtils.getElementOfType(attribute, Types.IDENTIFIER);
        if (!knownTypes.contains(type)) throw new CompileException("Unknown type: " + type, attribute);
    }

    public void verifyDuplicateTasks(SmartCaseTasks tasks) {
        if (tasks != null) {
            Set<String> names = new HashSet<>();
            tasks.getTaskList().forEach(a -> {
                String name = PsiUtils.getElementOfType(a, Types.IDENTIFIER);
                if (!names.contains(name)) names.add(name);
                else throw new CompileException("Duplicate task: " + name, a);
            });
        }
    }

    private List<VirtualFile> findFiles(Module module, PsiManager manager, Class<? extends PsiElement> caseClass) {
        ArrayList<VirtualFile> files = new ArrayList<>();
        ModuleRootManager rootManager = ModuleRootManager.getInstance(module);
        Arrays.stream(rootManager.getSourceRoots(false)).forEach(r -> {
            VfsUtil.iterateChildrenRecursively(r, f -> {
                if (f.isDirectory()) return true;
                PsiFile cf = manager.findFile(f);
                if (cf instanceof SmartCaseFile) {
                    return ((SmartCaseFile) cf).findChildByClass(caseClass) != null;
                } else return false;
            }, f -> {
                if (!f.isDirectory()) files.add(f);
                return true;
            });
        });

        return files;
    }

}
