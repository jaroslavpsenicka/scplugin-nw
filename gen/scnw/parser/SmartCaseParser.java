// This is a generated file. Not intended for manual editing.
package scnw.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static scnw.psi.Types.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class SmartCaseParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t instanceof IFileElementType) {
      r = parse_root_(t, b, 0);
    }
    else {
      r = false;
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return file(b, l + 1);
  }

  /* ********************************************************** */
  // WHITESPACE? IDENTIFIER WHITESPACE? IDENTIFIER WHITESPACE? defaultValue? WHITESPACE? SEMICOLON
  public static boolean attribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute")) return false;
    if (!nextTokenIs(b, "<attribute>", IDENTIFIER, WHITESPACE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ATTRIBUTE, "<attribute>");
    r = attribute_0(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    r = r && attribute_2(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    r = r && attribute_4(b, l + 1);
    r = r && attribute_5(b, l + 1);
    r = r && attribute_6(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // WHITESPACE?
  private static boolean attribute_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_0")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  // WHITESPACE?
  private static boolean attribute_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_2")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  // WHITESPACE?
  private static boolean attribute_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_4")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  // defaultValue?
  private static boolean attribute_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_5")) return false;
    defaultValue(b, l + 1);
    return true;
  }

  // WHITESPACE?
  private static boolean attribute_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_6")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  /* ********************************************************** */
  // (attribute | comment WHITESPACE? attribute)*
  public static boolean attributes(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attributes")) return false;
    Marker m = enter_section_(b, l, _NONE_, ATTRIBUTES, "<attributes>");
    while (true) {
      int c = current_position_(b);
      if (!attributes_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "attributes", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // attribute | comment WHITESPACE? attribute
  private static boolean attributes_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attributes_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = attribute(b, l + 1);
    if (!r) r = attributes_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // comment WHITESPACE? attribute
  private static boolean attributes_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attributes_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = comment(b, l + 1);
    r = r && attributes_0_1_1(b, l + 1);
    r = r && attribute(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // WHITESPACE?
  private static boolean attributes_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attributes_0_1_1")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  /* ********************************************************** */
  // WHITESPACE? SINGLE_LINE_COMMENT (TEXT | WHITESPACE)* CRLF?
  public static boolean comment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comment")) return false;
    if (!nextTokenIs(b, "<comment>", SINGLE_LINE_COMMENT, WHITESPACE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMMENT, "<comment>");
    r = comment_0(b, l + 1);
    r = r && consumeToken(b, SINGLE_LINE_COMMENT);
    r = r && comment_2(b, l + 1);
    r = r && comment_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // WHITESPACE?
  private static boolean comment_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comment_0")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  // (TEXT | WHITESPACE)*
  private static boolean comment_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comment_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!comment_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "comment_2", c)) break;
    }
    return true;
  }

  // TEXT | WHITESPACE
  private static boolean comment_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comment_2_0")) return false;
    boolean r;
    r = consumeToken(b, TEXT);
    if (!r) r = consumeToken(b, WHITESPACE);
    return r;
  }

  // CRLF?
  private static boolean comment_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comment_3")) return false;
    consumeToken(b, CRLF);
    return true;
  }

  /* ********************************************************** */
  // EQUALS WHITESPACE? STRING_VALUE WHITESPACE?
  public static boolean defaultValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defaultValue")) return false;
    if (!nextTokenIs(b, EQUALS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUALS);
    r = r && defaultValue_1(b, l + 1);
    r = r && consumeToken(b, STRING_VALUE);
    r = r && defaultValue_3(b, l + 1);
    exit_section_(b, m, DEFAULT_VALUE, r);
    return r;
  }

  // WHITESPACE?
  private static boolean defaultValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defaultValue_1")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  // WHITESPACE?
  private static boolean defaultValue_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defaultValue_3")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  /* ********************************************************** */
  // process | (comment process)
  static boolean file(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = process(b, l + 1);
    if (!r) r = file_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // comment process
  private static boolean file_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = comment(b, l + 1);
    r = r && process(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // WHITESPACE? PROCESS_KEYWORD WHITESPACE? IDENTIFIER processDefinition
  public static boolean process(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "process")) return false;
    if (!nextTokenIs(b, "<process>", PROCESS_KEYWORD, WHITESPACE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROCESS, "<process>");
    r = process_0(b, l + 1);
    r = r && consumeToken(b, PROCESS_KEYWORD);
    r = r && process_2(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    r = r && processDefinition(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // WHITESPACE?
  private static boolean process_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "process_0")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  // WHITESPACE?
  private static boolean process_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "process_2")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  /* ********************************************************** */
  // LEFT_CURLY WHITESPACE? attributes? WHITESPACE? tasks? WHITESPACE? transitions? WHITESPACE? tests? WHITESPACE? RIGHT_CURLY
  public static boolean processDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "processDefinition")) return false;
    if (!nextTokenIs(b, LEFT_CURLY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LEFT_CURLY);
    r = r && processDefinition_1(b, l + 1);
    r = r && processDefinition_2(b, l + 1);
    r = r && processDefinition_3(b, l + 1);
    r = r && processDefinition_4(b, l + 1);
    r = r && processDefinition_5(b, l + 1);
    r = r && processDefinition_6(b, l + 1);
    r = r && processDefinition_7(b, l + 1);
    r = r && processDefinition_8(b, l + 1);
    r = r && processDefinition_9(b, l + 1);
    r = r && consumeToken(b, RIGHT_CURLY);
    exit_section_(b, m, PROCESS_DEFINITION, r);
    return r;
  }

  // WHITESPACE?
  private static boolean processDefinition_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "processDefinition_1")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  // attributes?
  private static boolean processDefinition_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "processDefinition_2")) return false;
    attributes(b, l + 1);
    return true;
  }

  // WHITESPACE?
  private static boolean processDefinition_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "processDefinition_3")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  // tasks?
  private static boolean processDefinition_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "processDefinition_4")) return false;
    tasks(b, l + 1);
    return true;
  }

  // WHITESPACE?
  private static boolean processDefinition_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "processDefinition_5")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  // transitions?
  private static boolean processDefinition_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "processDefinition_6")) return false;
    transitions(b, l + 1);
    return true;
  }

  // WHITESPACE?
  private static boolean processDefinition_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "processDefinition_7")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  // tests?
  private static boolean processDefinition_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "processDefinition_8")) return false;
    tests(b, l + 1);
    return true;
  }

  // WHITESPACE?
  private static boolean processDefinition_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "processDefinition_9")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  /* ********************************************************** */
  // WHITESPACE? TASK_KEYWORD WHITESPACE? IDENTIFIER WHITESPACE? FROM_KEYWORD WHITESPACE? FILE_REF WHITESPACE? SEMICOLON
  public static boolean task(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "task")) return false;
    if (!nextTokenIs(b, "<task>", TASK_KEYWORD, WHITESPACE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TASK, "<task>");
    r = task_0(b, l + 1);
    r = r && consumeToken(b, TASK_KEYWORD);
    r = r && task_2(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    r = r && task_4(b, l + 1);
    r = r && consumeToken(b, FROM_KEYWORD);
    r = r && task_6(b, l + 1);
    r = r && consumeToken(b, FILE_REF);
    r = r && task_8(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // WHITESPACE?
  private static boolean task_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "task_0")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  // WHITESPACE?
  private static boolean task_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "task_2")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  // WHITESPACE?
  private static boolean task_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "task_4")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  // WHITESPACE?
  private static boolean task_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "task_6")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  // WHITESPACE?
  private static boolean task_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "task_8")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  /* ********************************************************** */
  // (task | comment WHITESPACE? task)*
  public static boolean tasks(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tasks")) return false;
    Marker m = enter_section_(b, l, _NONE_, TASKS, "<tasks>");
    while (true) {
      int c = current_position_(b);
      if (!tasks_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "tasks", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // task | comment WHITESPACE? task
  private static boolean tasks_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tasks_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = task(b, l + 1);
    if (!r) r = tasks_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // comment WHITESPACE? task
  private static boolean tasks_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tasks_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = comment(b, l + 1);
    r = r && tasks_0_1_1(b, l + 1);
    r = r && task(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // WHITESPACE?
  private static boolean tasks_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tasks_0_1_1")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  /* ********************************************************** */
  // WHITESPACE? TEST_KEYWORD WHITESPACE? IDENTIFIER testDefinition
  public static boolean test(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "test")) return false;
    if (!nextTokenIs(b, "<test>", TEST_KEYWORD, WHITESPACE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TEST, "<test>");
    r = test_0(b, l + 1);
    r = r && consumeToken(b, TEST_KEYWORD);
    r = r && test_2(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    r = r && testDefinition(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // WHITESPACE?
  private static boolean test_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "test_0")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  // WHITESPACE?
  private static boolean test_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "test_2")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  /* ********************************************************** */
  // WHITESPACE? LEFT_CURLY WHITESPACE? RIGHT_CURLY
  public static boolean testDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "testDefinition")) return false;
    if (!nextTokenIs(b, "<test definition>", LEFT_CURLY, WHITESPACE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TEST_DEFINITION, "<test definition>");
    r = testDefinition_0(b, l + 1);
    r = r && consumeToken(b, LEFT_CURLY);
    r = r && testDefinition_2(b, l + 1);
    r = r && consumeToken(b, RIGHT_CURLY);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // WHITESPACE?
  private static boolean testDefinition_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "testDefinition_0")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  // WHITESPACE?
  private static boolean testDefinition_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "testDefinition_2")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  /* ********************************************************** */
  // (test | comment WHITESPACE? test)*
  public static boolean tests(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tests")) return false;
    Marker m = enter_section_(b, l, _NONE_, TESTS, "<tests>");
    while (true) {
      int c = current_position_(b);
      if (!tests_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "tests", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // test | comment WHITESPACE? test
  private static boolean tests_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tests_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = test(b, l + 1);
    if (!r) r = tests_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // comment WHITESPACE? test
  private static boolean tests_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tests_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = comment(b, l + 1);
    r = r && tests_0_1_1(b, l + 1);
    r = r && test(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // WHITESPACE?
  private static boolean tests_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tests_0_1_1")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  /* ********************************************************** */
  // WHITESPACE? TRANSITION_KEYWORD WHITESPACE? IDENTIFIER WHITESPACE? ARROW WHITESPACE? IDENTIFIER trasitionDefinition
  public static boolean transition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transition")) return false;
    if (!nextTokenIs(b, "<transition>", TRANSITION_KEYWORD, WHITESPACE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TRANSITION, "<transition>");
    r = transition_0(b, l + 1);
    r = r && consumeToken(b, TRANSITION_KEYWORD);
    r = r && transition_2(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    r = r && transition_4(b, l + 1);
    r = r && consumeToken(b, ARROW);
    r = r && transition_6(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    r = r && trasitionDefinition(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // WHITESPACE?
  private static boolean transition_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transition_0")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  // WHITESPACE?
  private static boolean transition_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transition_2")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  // WHITESPACE?
  private static boolean transition_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transition_4")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  // WHITESPACE?
  private static boolean transition_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transition_6")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  /* ********************************************************** */
  // (transition | comment WHITESPACE? transition)*
  public static boolean transitions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transitions")) return false;
    Marker m = enter_section_(b, l, _NONE_, TRANSITIONS, "<transitions>");
    while (true) {
      int c = current_position_(b);
      if (!transitions_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "transitions", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // transition | comment WHITESPACE? transition
  private static boolean transitions_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transitions_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = transition(b, l + 1);
    if (!r) r = transitions_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // comment WHITESPACE? transition
  private static boolean transitions_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transitions_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = comment(b, l + 1);
    r = r && transitions_0_1_1(b, l + 1);
    r = r && transition(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // WHITESPACE?
  private static boolean transitions_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transitions_0_1_1")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  /* ********************************************************** */
  // WHITESPACE? LEFT_CURLY WHITESPACE? RIGHT_CURLY
  public static boolean trasitionDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trasitionDefinition")) return false;
    if (!nextTokenIs(b, "<trasition definition>", LEFT_CURLY, WHITESPACE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TRASITION_DEFINITION, "<trasition definition>");
    r = trasitionDefinition_0(b, l + 1);
    r = r && consumeToken(b, LEFT_CURLY);
    r = r && trasitionDefinition_2(b, l + 1);
    r = r && consumeToken(b, RIGHT_CURLY);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // WHITESPACE?
  private static boolean trasitionDefinition_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trasitionDefinition_0")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

  // WHITESPACE?
  private static boolean trasitionDefinition_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trasitionDefinition_2")) return false;
    consumeToken(b, WHITESPACE);
    return true;
  }

}
