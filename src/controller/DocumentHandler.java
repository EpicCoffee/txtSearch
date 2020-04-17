package controller;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import model.Session;
import model.TextFile;
import model.TextFileRatings;

public class DocumentHandler
{

	/** Search within the ContentWords in the textFile to find searchText(s), returns the matching words.
	 *
	 * @param textFile the textFile you want to search in.
	 * @param searchText the words you want to find in the text file.
	 * @return ArrayList<String> with the found search results.
	 */
	public static ArrayList<String> searchTextFileContentWithWords(TextFile textFile, String... searchText)
	{
		ArrayList<String> wordMatches = new ArrayList<>();
		String[] txtContent = getWordsFromString(textFile.getContentWords().toLowerCase());
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
	 * Sorting word in alphabetical order.
	 *
	 * @param unsortedWords All the word you want to sort.
	 * @return String[] of words in alphabetical order.
	 */
	public static String[] alphabeticSort(String[] unsortedWords)
	{
		Arrays.sort(unsortedWords);
		return unsortedWords;
	}

	/**
	 * Get the content of an text file as a String.
	 *
	 * @param filePath The file path to the text file you want content from.
	 * @return String with the content of the text file at filePath.
	 */
    public static String getContentInTextFileAt(String filePath)
	{
        try
        {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }

	/**
	 * Get the words with no spaces and no strange signs from a sentence.
	 *
	 * @param text the sentence you want to get words from.
	 * @return String[] with valid words.
	 */
    public static String[] getWordsFromString(String text)
	{
		return text.split("\\W+");
	}


	/**
	 * Loads all .txt files from our folderPath and adds the needed info as a TextFile to LoadedTextFiles at Session.
	 */
    public static void loadAllMyTextFiles()
	{
		Session.getSession().resetLoadedTextFiles();
		File f = new File(Constants.folderPath);
		FilenameFilter textFilter = (dir, name) -> name.toLowerCase().endsWith(".txt");

		File[] files = f.listFiles(textFilter);
		if (files == null)
		{
			return;
		}

		for (File file : files) {
			String name = file.getName().substring(0, file.getName().length() - 4);
			Session.getSession().addToLoadedTextFiles(new TextFile(name, getContentInTextFileAt(file.getPath())));
		}
	}

	/**
	 * Write the content you want inside of a text file.
	 * Creates the text file if it do not exist.
	 *
	 *  @param fileName The chosen documents name, without format.
	 * @param content The text you want to save in the file.
	 * @return true if file is created or when the content of the text file is changed.
	 */
	public static boolean editOrCreateTextFile(String fileName, String content)
	{
		try {
			File file = new File(Constants.folderPath + fileName + ".txt");
			if (file.exists())
			{
				return setContentInTextFile(fileName, content);
			}
			else if (file.createNewFile())
			{
				return setContentInTextFile(fileName, content);
			}
		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Write the content you want inside of an existing text file.
	 *
	 * @param fileName The chosen documents name, without format.
	 * @param content The text you want to save in the file.
	 * @return true if it manage to write the content in the file.
	 */

	private static boolean setContentInTextFile(String fileName, String content)
	{
		try (PrintWriter out = new PrintWriter(Constants.folderPath + fileName + ".txt")) {
			out.println(content);
			return true;
		}
		catch (IOException e)
		{
			System.out.println("Error");
			e.printStackTrace();
		}
		return false;
	}

    /**
     * Creates an sorted ArrayList with the documents and ranks.
	 * Ranks are based on amount of found words in the document.
	 * Sort the list and highest rank will be at top.
     *
     * @param chosenDocuments The chosen documents to search and rank.
     * @param chosenWords The words to search after.
     * @return ArrayList with TextFileRating sorted by highest rating first.
     */
	public static ArrayList<TextFileRatings> createSortedTextFileRatingsArrayList(ArrayList<TextFile> chosenDocuments, String... chosenWords)
	{
		ArrayList<TextFileRatings> ratedTextFiles = new ArrayList<>();

		for (TextFile chosenDocument : chosenDocuments)
		{
			int rating = searchTextFileContentWithWords(chosenDocument, chosenWords).size();
			ratedTextFiles.add(new TextFileRatings(rating, chosenDocument));
		}
		Collections.sort(ratedTextFiles);
		return ratedTextFiles;
	}
}