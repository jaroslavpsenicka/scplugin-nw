package scnw;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class SmartCaseLexerAdapter extends FlexAdapter {
  public SmartCaseLexerAdapter() {
    super(new SmartCaseLexer((Reader) null));
  }
}
