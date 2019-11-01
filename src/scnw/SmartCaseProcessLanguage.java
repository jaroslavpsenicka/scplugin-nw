package scnw;

import com.intellij.lang.Language;

public class SmartCaseProcessLanguage extends Language {

    public static final SmartCaseProcessLanguage INSTANCE = new SmartCaseProcessLanguage();

    private SmartCaseProcessLanguage() {
        super("SmartCaseProcessLanguage");
    }
}

