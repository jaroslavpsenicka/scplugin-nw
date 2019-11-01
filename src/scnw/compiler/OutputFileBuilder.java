package scnw.compiler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.util.PsiTreeUtil;
import org.junit.Assert;
import scnw.psi.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OutputFileBuilder {

    private final Module module;
    private SmartCaseProcess process;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static enum KNOWN_ATTR_NAMES {
        Id, NotNull, NotEmpty
    };

    public static OutputFileBuilder forModule(Module module) {
        return new OutputFileBuilder(module);
    }

    private OutputFileBuilder(Module module) {
        this.module = module;
    }

    public OutputFileBuilder withProcess(SmartCaseProcess process) {
        this.process = process;
        return this;
    }

    public void generateTo(VirtualFile outputDirectory) throws IOException {
        Assert.assertNotNull("output directory cannot be null", outputDirectory);
        File outputFile = new File(outputDirectory.getPath(), module.getName() + ".json");
        if (outputFile.exists() && !outputFile.canWrite()) throw new IllegalStateException("cannot write " + outputFile);
        FileUtil.writeToFile(outputFile, this.toJson());
    }

    String toJson() {
        JsonObject root = new JsonObject();
        root.addProperty("name", PsiUtils.getElementOfType(process, Types.IDENTIFIER));

        JsonArray attrArray = new JsonArray();
        SmartCaseAttributes attributes = process.getProcessDefinition().getAttributes();
        if (attributes != null) attributes.getAttributeList().stream().forEach(a -> attrArray.add(toJson(a)));
        root.add("attributes", attrArray);

        JsonArray taskArray = new JsonArray();
        SmartCaseTasks tasks = process.getProcessDefinition().getTasks();
        if (tasks != null) tasks.getTaskList().stream().forEach(t -> taskArray.add(toJson(t)));
        root.add("tasks", taskArray);

        return gson.toJson(root);
    }

    // Attribute

    private JsonObject toJson(SmartCaseAttribute attr) {
        JsonObject object = new JsonObject();
        object.addProperty("type",
            PsiUtils.getElementOfType(attr, Types.IDENTIFIER, 0));
        object.addProperty("name",
            PsiUtils.getElementOfType(attr, Types.IDENTIFIER, 1));
        object.addProperty("defaultValue",
            unwrap(PsiUtils.getElementOfType(attr.getDefaultValue(), Types.IDENTIFIER)));

        SmartCaseComment comment = PsiTreeUtil.getPrevSiblingOfType(attr, SmartCaseComment.class);
        if (comment != null) {
            String text = PsiUtils.getElementOfType(comment, Types.TEXT);
            object.addProperty("description", text.trim());
        }

        return object;
    }

    // Task

    private JsonObject toJson(SmartCaseTask task) {
        String filename = unwrap(PsiUtils.getElementOfType(task, Types.FILE_REF));
        File ref = new File(process.getContainingFile().getVirtualFile().getParent().getPath(), filename);
        try {
            JsonObject object = gson.fromJson(new FileReader(ref), JsonObject.class);
            object.addProperty("name", PsiUtils.getElementOfType(task, Types.IDENTIFIER));
            return object;
        } catch (FileNotFoundException ex) {
            throw new CompileException("Error reading file " + filename, task);
        }
    }

    private String unwrap(String name) {
        if (name != null && name.startsWith("\"") && name.endsWith("\"")) {
            return name.substring(1, name.length()-1);
        }

        return name;
    }


}
