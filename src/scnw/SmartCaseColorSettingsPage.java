package scnw;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.*;
import org.jetbrains.annotations.*;

import javax.swing.*;
import java.util.Map;

public class SmartCaseColorSettingsPage implements ColorSettingsPage {
  private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
      new AttributesDescriptor("Keyword", SmartCaseSyntaxHighlighter.KEYWORD),
      new AttributesDescriptor("Identifier", SmartCaseSyntaxHighlighter.IDENTIFIER),
      new AttributesDescriptor("Comment", SmartCaseSyntaxHighlighter.COMMENT),
  };

  @Nullable
  @Override
  public Icon getIcon() {
    return Icons.FILE;
  }

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    return new SmartCaseSyntaxHighlighter();
  }

  @NotNull
  @Override
  public String getDemoText() {
    return "// Lovely process\n" +
      "process Test {\n" +
      "\n" +
      "    // Name\n" +
      "    String hello = \"World\";\n" +
      "    Integer value;\n" +
      "\n" +
      "    // Load from separate file\n" +
      "    task task1 from \"./task1.sc\";\n" +
      "\n" +
      "}";
  }

  @Nullable
  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return null;
  }

  @NotNull
  @Override
  public AttributesDescriptor[] getAttributeDescriptors() {
    return DESCRIPTORS;
  }

  @NotNull
  @Override
  public ColorDescriptor[] getColorDescriptors() {
    return ColorDescriptor.EMPTY_ARRAY;
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return "SmartCase";
  }
}