package scnw;

import com.intellij.psi.tree.IElementType;
import scnw.psi.Types;
import com.intellij.psi.TokenType;
import com.intellij.lexer.FlexLexer;

%%

%class SmartCaseLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=\R
WHITE_SPACE=[\ \n\t\f]
LEFT_CURLY="{"
RIGHT_CURLY="}"
EQUALS="="
SEMICOLON=";"
ARROW="->"

SINGLE_LINE_COMMENT="//"
CHAR=[^\n\f\\]
PROCESS_KEYWORD="process"
TASK_KEYWORD="task"
FROM_KEYWORD="from"
TEST_KEYWORD="test"
TRANSITION_KEYWORD="transition"
IDENTIFIER=[:jletter:][:jletterdigit:]*
STRING_VALUE=\"[\w ]*\"
FILE_REF=\"[\w\./]*\"

%state COMMENT
%state INNER_COMMENT
%state PROCESS
%state TEST
%state TRANSITION

%%

<YYINITIAL> {SINGLE_LINE_COMMENT}           { yybegin(COMMENT); return Types.SINGLE_LINE_COMMENT; }
<COMMENT> {CHAR}+                           { yybegin(YYINITIAL); return Types.TEXT; }

<YYINITIAL> {PROCESS_KEYWORD}               { yybegin(PROCESS); return Types.PROCESS_KEYWORD; }
<PROCESS> {TASK_KEYWORD}                    { yybegin(PROCESS); return Types.TASK_KEYWORD; }
<PROCESS> {FROM_KEYWORD}                    { yybegin(PROCESS); return Types.FROM_KEYWORD; }

<PROCESS> {SINGLE_LINE_COMMENT}             { yybegin(INNER_COMMENT); return Types.SINGLE_LINE_COMMENT; }
<INNER_COMMENT> {CHAR}+                     { yybegin(PROCESS); return Types.TEXT; }

<PROCESS> {TEST_KEYWORD}                    { yybegin(TEST); return Types.TEST_KEYWORD; }
<TEST>{RIGHT_CURLY}                         { yybegin(PROCESS); return Types.RIGHT_CURLY; }

<PROCESS> {TRANSITION_KEYWORD}              { yybegin(TRANSITION); return Types.TRANSITION_KEYWORD; }
<TRANSITION>{ARROW}                         { yybegin(TRANSITION); return Types.ARROW; }
<TRANSITION>{RIGHT_CURLY}                   { yybegin(PROCESS); return Types.RIGHT_CURLY; }

{EQUALS}                                    { return Types.EQUALS; }
{SEMICOLON}                                 { return Types.SEMICOLON; }
{STRING_VALUE}                              { return Types.STRING_VALUE; }
{FILE_REF}                                  { return Types.FILE_REF; }
{IDENTIFIER}                                { return Types.IDENTIFIER; }
{LEFT_CURLY}                                { return Types.LEFT_CURLY; }
{RIGHT_CURLY}                               { yybegin(YYINITIAL); return Types.RIGHT_CURLY; }
({CRLF}|{WHITE_SPACE})+                     { return TokenType.WHITE_SPACE; }

[^]                                         { return TokenType.BAD_CHARACTER; }
