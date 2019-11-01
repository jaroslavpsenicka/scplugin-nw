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

SINGLE_LINE_COMMENT="//"
CHAR=[^\n\f\\]
PROCESS_KEYWORD="process"
TASK_KEYWORD="task"
FROM_KEYWORD="from"
IDENTIFIER=[:jletter:][:jletterdigit:]*
STRING_VALUE=\"[\w ]*\"
FILE_REF=\"[\w\./]*\"

%state COMMENT
%state INNER_COMMENT
%state PROCESS

%%

<YYINITIAL> {SINGLE_LINE_COMMENT}           { yybegin(COMMENT); return Types.SINGLE_LINE_COMMENT; }
<COMMENT> {CHAR}+                           { yybegin(YYINITIAL); return Types.TEXT; }

<YYINITIAL> {PROCESS_KEYWORD}               { yybegin(PROCESS); return Types.PROCESS_KEYWORD; }
<PROCESS> {TASK_KEYWORD}                    { yybegin(PROCESS); return Types.TASK_KEYWORD; }
<PROCESS> {FROM_KEYWORD}                    { yybegin(PROCESS); return Types.FROM_KEYWORD; }

<PROCESS> {SINGLE_LINE_COMMENT}             { yybegin(INNER_COMMENT); return Types.SINGLE_LINE_COMMENT; }
<INNER_COMMENT> {CHAR}+                     { yybegin(PROCESS); return Types.TEXT; }

{EQUALS}                                    { return Types.EQUALS; }
{SEMICOLON}                                 { return Types.SEMICOLON; }
{STRING_VALUE}                              { return Types.STRING_VALUE; }
{FILE_REF}                                  { return Types.FILE_REF; }
{IDENTIFIER}                                { return Types.IDENTIFIER; }
{LEFT_CURLY}                                { return Types.LEFT_CURLY; }
{RIGHT_CURLY}                               { yybegin(YYINITIAL); return Types.RIGHT_CURLY; }
({CRLF}|{WHITE_SPACE})+                     { return TokenType.WHITE_SPACE; }

[^]                                         { return TokenType.BAD_CHARACTER; }
