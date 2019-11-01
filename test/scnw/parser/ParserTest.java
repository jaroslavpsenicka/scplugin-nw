package scnw.parser;

import com.intellij.testFramework.ParsingTestCase;
import scnw.SmartCaseParserDefinition;

public class ParserTest extends ParsingTestCase {

  public ParserTest() {
    super("", "sc", new SmartCaseParserDefinition());
  }

  public void testEmpty() {
    doTest(true);
  }
  public void testComment() {
    doTest(true);
  }
  public void testAttribute() {
    doTest(true);
  }
  public void testCommentedAttribute() {
    doTest(true);
  }
  public void testCommentedAttributeValue() {
    doTest(true);
  }
  public void testMultipleAttributes() {
    doTest(true);
  }
  public void testTask() {
    doTest(true);
  }
  public void testMultipleTasks() {
    doTest(true);
  }
  public void testComplex() {
    doTest(true);
  }

  @Override
  protected String getTestDataPath() {
    return "test/resources/parser";
  }

  @Override
  protected boolean skipSpaces() {
    return false;
  }

  @Override
  protected boolean includeRanges() {
    return true;
  }
}
