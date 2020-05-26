package es.map.ipsg.util;

import java.text.Collator;
import java.util.Comparator;

/**
 * El Class StringIgnoreAccentComparator.
 */
public class StringIgnoreAccentComparator implements Comparator<String> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(String o1, String o2) {
		Collator myCollator = Collator.getInstance();
		myCollator.setStrength(Collator.PRIMARY);
		int result = myCollator.compare(o1, o2);
		return result;
	}

}
