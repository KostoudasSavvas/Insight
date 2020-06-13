package guitexteditor;

import javax.swing.JPopupMenu;
import javax.swing.JTextPane;
import com.inet.jortho.FileUserDictionary;
import com.inet.jortho.SpellChecker;
import com.inet.jortho.SpellCheckerOptions;

/**
 * This class initializes all the necessary parts of the Spellchecking 
 * function such as constructing the pop up menu and registers as a 
 * dictionary, the english dict which is located at the resources folder.
 * @author savaf
 *
 */


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