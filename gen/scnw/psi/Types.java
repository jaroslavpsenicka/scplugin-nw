// This is a generated file. Not intended for manual editing.
package scnw.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import scnw.psi.impl.*;

public interface Types {

  IElementType ATTRIBUTE = new SmartCaseElementType("ATTRIBUTE");
  IElementType ATTRIBUTES = new SmartCaseElementType("ATTRIBUTES");
  IElementType COMMENT = new SmartCaseElementType("COMMENT");
  IElementType DEFAULT_VALUE = new SmartCaseElementType("DEFAULT_VALUE");
  IElementType PROCESS = new SmartCaseElementType("PROCESS");
  IElementType PROCESS_DEFINITION = new SmartCaseElementType("PROCESS_DEFINITION");
  IElementType TASK = new SmartCaseElementType("TASK");
  IElementType TASKS = new SmartCaseElementType("TASKS");
  IElementType TEST = new SmartCaseElementType("TEST");
  IElementType TESTS = new SmartCaseElementType("TESTS");
  IElementType TEST_DEFINITION = new SmartCaseElementType("TEST_DEFINITION");

  IElementType CRLF = new SmartCaseTokenType("CRLF");
  IElementType EQUALS = new SmartCaseTokenType("EQUALS");
  IElementType FILE_REF = new SmartCaseTokenType("FILE_REF");
  IElementType FROM_KEYWORD = new SmartCaseTokenType("FROM_KEYWORD");
  IElementType IDENTIFIER = new SmartCaseTokenType("IDENTIFIER");
  IElementType LEFT_CURLY = new SmartCaseTokenType("LEFT_CURLY");
  IElementType PROCESS_KEYWORD = new SmartCaseTokenType("PROCESS_KEYWORD");
  IElementType RIGHT_CURLY = new SmartCaseTokenType("RIGHT_CURLY");
  IElementType SEMICOLON = new SmartCaseTokenType("SEMICOLON");
  IElementType SINGLE_LINE_COMMENT = new SmartCaseTokenType("SINGLE_LINE_COMMENT");
  IElementType STRING_VALUE = new SmartCaseTokenType("STRING_VALUE");
  IElementType TASK_KEYWORD = new SmartCaseTokenType("TASK_KEYWORD");
  IElementType TEST_KEYWORD = new SmartCaseTokenType("TEST_KEYWORD");
  IElementType TEXT = new SmartCaseTokenType("TEXT");
  IElementType WHITESPACE = new SmartCaseTokenType("WHITESPACE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ATTRIBUTE) {
        return new SmartCaseAttributeImpl(node);
      }
      else if (type == ATTRIBUTES) {
        return new SmartCaseAttributesImpl(node);
      }
      else if (type == COMMENT) {
        return new SmartCaseCommentImpl(node);
      }
      else if (type == DEFAULT_VALUE) {
        return new SmartCaseDefaultValueImpl(node);
      }
      else if (type == PROCESS) {
        return new SmartCaseProcessImpl(node);
      }
      else if (type == PROCESS_DEFINITION) {
        return new SmartCaseProcessDefinitionImpl(node);
      }
      else if (type == TASK) {
        return new SmartCaseTaskImpl(node);
      }
      else if (type == TASKS) {
        return new SmartCaseTasksImpl(node);
      }
      else if (type == TEST) {
        return new SmartCaseTestImpl(node);
      }
      else if (type == TESTS) {
        return new SmartCaseTestsImpl(node);
      }
      else if (type == TEST_DEFINITION) {
        return new SmartCaseTestDefinitionImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
