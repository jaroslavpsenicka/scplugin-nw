package scnw;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import scnw.psi.Types;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class SmartCaseSyntaxHighlighter extends SyntaxHighlighterBase {
  static TextAttributesKey IDENTIFIER = createTextAttributesKey("IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
  static TextAttributesKey KEYWORD = createTextAttributesKey("KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
  static TextAttributesKey COMMENT = createTextAttributesKey("COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);

  private static final TextAttributesKey[] IDENTIFIER_KEYS = new TextAttributesKey[] {IDENTIFIER};
  private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[] {KEYWORD};
  private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[] {COMMENT};
  private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

  @NotNull
  @Override
  public Lexer getHighlightingLexer() {
    return new SmartCaseLexerAdapter();
  }

  @NotNull
  @Override
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
    if (tokenType.equals(Types.IDENTIFIER)) {
      return IDENTIFIER_KEYS;
    } else if (tokenType.equals(Types.PROCESS_KEYWORD) ||
      tokenType.equals(Types.TASK_KEYWORD) ||
      tokenType.equals(Types.FROM_KEYWORD)) {
      return KEYWORD_KEYS;
    } else if (tokenType.equals(Types.TEXT) || tokenType.equals(Types.SINGLE_LINE_COMMENT)) {
      return COMMENT_KEYS;
    } else {
      return EMPTY_KEYS;
    }
  }
}