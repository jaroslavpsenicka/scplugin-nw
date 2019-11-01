package scnw;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SmartCaseFileType extends LanguageFileType {

    public static final SmartCaseFileType INSTANCE = new SmartCaseFileType();

    private SmartCaseFileType() {
        super(SmartCaseProcessLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "SmartCase file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "SmartCase definition file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "sc";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return Icons.FILE;
    }

    public static class Factory extends FileTypeFactory {

        @Override
        public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
            fileTypeConsumer.consume(SmartCaseFileType.INSTANCE);
        }
    }

}