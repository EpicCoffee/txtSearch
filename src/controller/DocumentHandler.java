package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

	/**
	 * alphabeticSort() receives an unsorted array and returns it alphabetically sorted. It uses the method <br>
	 * Arrays.sort to sort the array.
	 *
	 * @param unsortedTextfile The unsorted text file.
	 * @return The sorted text file.
	 */
	public static String[] alphabeticSort(String[] unsortedTextfile){
		Arrays.sort(unsortedTextfile);
		return unsortedTextfile;
	}



	//____________________NOT FINISHED?____________________

    //Ska vi lägga denna i ett eget fönster?
    class Score implements Comparable<Score> {
        int score;
        TextFile textFile;

        /**
         * Sets the values of score and textFile.
         *
         * @param score The number of matching words.
         * @param textFile The text file with the matching score.
         */
        public Score(int score, TextFile textFile) {
            this.score = score;
            this.textFile = textFile;
        }

        /**
         * CompareTo() is a pre-made method that makes it possible to sort an object list.
         *
         * @param o The score to compare
         * @return The result of the compare.
         */
        @Override
        public int compareTo(Score o) {
            return Integer.compare(score, o.score);
        }
    }

    /**
     * sortRanking uses searchStrings() to rank each text document and sorts them after the ranking.
     *
     * @param chosenDocuments The text files user has chosen to compare.
     * @param chosenWords The words the user wants to search after.
     * @return A sorted list of the most relevant text files.
     */
    public List sortRanking(ArrayList<TextFile> chosenDocuments, String ... chosenWords){
        List<Score> scores = new ArrayList<Score>();

        for (TextFile chosenDocument : chosenDocuments) {
            int rating = searchStrings(chosenDocument, chosenWords).size();
            scores.add(new Score(rating, chosenDocument));
        }
        Collections.sort(scores);
        return scores;
    }
}