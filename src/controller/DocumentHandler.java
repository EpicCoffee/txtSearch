package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import model.Session;
import model.TextFile;

public class DocumentHandler
{
	private final static String folderPath = "C:/txtSearch/";

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

	/**

	 * using content as an "empty box" where i store and manipulate eg. with split and filepath
	 * @param filePath String filePath has the file reading function so we send it as an argument it will read the files
	 * @return will return the the texts inside words an array each word has it own position index
	 * regex it removes all non alphabetic letters.
	 */
    public static String[] getContent(String filePath) {
        String content = "";

        try
        {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        }
        catch (IOException e)
        {
            e.printStackTrace();

        }

        return getWordsFromString(content);
    }

    public static String[] getWordsFromString(String text)
	{
		return text.split("\\W+");
	}

    public static void loadAllFiles()
	{
		Session.getSession().resetLoadedTextFiles();
		File f = new File(folderPath);
		FilenameFilter textFilter = (dir, name) -> name.toLowerCase().endsWith(".txt");

		File[] files = f.listFiles(textFilter);
		for (File file : files) {
			String name = file.getName().substring(0, file.getName().length() - 4);
			Session.getSession().addToLoadedTextFiles(new TextFile(name, getContent(file.getPath())));
		}

	}

	/**
	 * Tries to create a file with name and content.
	 * @return true if file is created.
	 */
	public static boolean saveFile(String fileName, String content)
	{
		try {
			File file = new File(folderPath + fileName + ".txt");
			if (file.exists())
			{
				return writeInTextFile(fileName, content);
			}
			else if (file.createNewFile())
			{
				return writeInTextFile(fileName, content);
			}
		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		return false;
	}

	public static boolean writeInTextFile(String fileName, String content)
	{
		try (PrintWriter out = new PrintWriter(folderPath + fileName + ".txt")) {
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
     * setRankingOnTextFile() uses searchString() to rank the chosen documents and saves the ranking and found words <br>
     *     in the TextFileRatings class.
     * @param chosenDocuments The chosen documents to search and rank.
     * @param chosenWords The words to search after.
     * @return Returns the sorted document.
     */
	public static ArrayList setRankingOnTextFile(ArrayList<TextFile> chosenDocuments, String ... chosenWords){
		ArrayList<TextFileRatings> ratedTextFiles = new ArrayList<TextFileRatings>();

		for (TextFile chosenDocument : chosenDocuments) {
			int rating = searchStrings(chosenDocument, chosenWords).size();
			ratedTextFiles.add(new TextFileRatings(rating, chosenDocument));
		}
		Collections.sort(ratedTextFiles);
		return ratedTextFiles;
	}
}