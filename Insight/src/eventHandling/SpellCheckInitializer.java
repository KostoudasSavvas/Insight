package eventHandling;

import javax.swing.JPopupMenu;
import javax.swing.JTextPane;
import com.inet.jortho.FileUserDictionary;
import com.inet.jortho.SpellChecker;
import com.inet.jortho.SpellCheckerOptions;

import GenerateGui.MainEditorWindow;

public class SpellCheckInitializer {
	public void initializeSpellCheck(JTextPane textArea) {
		SpellChecker.setUserDictionaryProvider(new FileUserDictionary());
        SpellChecker.registerDictionaries(MainEditorWindow.class.getResource("/dictionary"), "en");
        SpellChecker.register(textArea);
        SpellCheckerOptions sco=new SpellCheckerOptions();
        sco.setCaseSensitive(true);
        sco.setSuggestionsLimitMenu(15);

        JPopupMenu popup = SpellChecker.createCheckerPopup(sco);
        textArea.setComponentPopupMenu(popup);
	}
}