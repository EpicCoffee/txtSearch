package controller;

import java.util.ArrayList;
import java.util.Arrays;
import model.TextFile;

public class DocumentHandler
{
	/** Search within the ContentWords to find searchText, returns the matching words.
	 *
	 * @param textFile the textFile you want to search in.
	 * @param searchText the words you want to find in the text file.
	 * @return ArrayList<String> with the found search results.
	 */
	public static ArrayList<String> searchStrings(TextFile textFile, String ... searchText)
	{
		ArrayList<String> wordMatches = new ArrayList<>();
		String[] txtContent = textFile.getContentWords();
		for (String contentWord : txtContent)
		{
			if (Arrays.asList(searchText).contains(contentWord))
			{
				wordMatches.add(contentWord);
			}
		}
		return wordMatches;
	}
}
