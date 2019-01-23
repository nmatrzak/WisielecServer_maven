
package utils;

import java.util.Arrays;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class WordCodeDecode.
 */
public class WordCodeDecode {
	
	/** The Constant SEP. */
	private final static String SEP= "@";
	
	/** The Constant specPolishChars. */
	private final static String specPolishChars = "ĄĆĘŁŃÓŚŹŻąćęłńóśźż";	
	
	/** The Constant coded. */
	private final static List<Integer> coded = Arrays.asList(260,262,280,321,323,0,346,379,377,261,263,281,322,324,0,347,380);
	
	/**
	 * Decode.
	 *
	 * @param word the word
	 * @return the string
	 */
	public static String decode(String word) {
		for(int i=0; i < coded.size(); i++) {
			word = word.replace("&#"+i+";", String.valueOf(specPolishChars.charAt(i)));
		}
		return word;
	}
	
	/**
	 * Char to spec.
	 *
	 * @param c the c
	 * @return the string
	 */
	private static String charToSpec(char c) {
		for(int i=0; i < specPolishChars.length(); i++) {
			if (c == specPolishChars.charAt(i)) {
				return SEP+i+SEP;
			}
		}
		return String.valueOf(c);
	}
	
	/**
	 * Code polish word to word with specs.
	 *
	 * @param word the word
	 * @return the string
	 */
	public static String codePolishWordToWordWithSpecs(String word) {
		StringBuilder result = new StringBuilder();
		for(int i=0; i < word.length(); i++ ) {
			result.append(charToSpec(word.charAt(i)));
		}
		return result.toString();
	}
	
	/**
	 * Decode word with specs to polish word.
	 *
	 * @param word the word
	 * @return the string
	 */
	public static String decodeWordWithSpecsToPolishWord(String word) {
		for(int i=0; i < specPolishChars.length(); i++) {
			word = word.replace(SEP+i+SEP, specPolishChars.substring(i,i+1));				
			}
		return word;
	}
	  

}
