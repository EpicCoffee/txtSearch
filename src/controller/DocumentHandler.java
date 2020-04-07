package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

	/**

	 * using content as an "empty box" where i store and manipulate eg. with split and filepath
	 * @param filePath String filePath has the file reading function so we send it as an argument it will read the files
	 * @return will return the the texts inside words an array each word has it own position index
	 * regex it removes all non alphabetic letters.
	 */
    public  String[] getContent(String filePath) {
        String content = "";
        try
        {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        }
        catch (IOException e)
        {
            e.printStackTrace();

        }
        String[] words =content.split("\\W+");



        return words;
    }

	/**
	 * Tries to create a file with name and content.
	 * @return true if file is created.
	 */
	public boolean CreateFile(String name, String content)
	{
        try {
            File file = new File("C:/txtSearch/" + name + ".txt");
            if (file.createNewFile())
			{
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(content);
				return true;
			}
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
		return false;
    }

	public List setRankingOnTextFile(ArrayList<TextFile> chosenDocuments, String ... chosenWords){
		List<TextFileRatings> scores = new ArrayList<TextFileRatings>();

		for (int i = 0; i < chosenDocuments.size(); i++) {
			int rating = searchStrings(chosenDocuments.get(i),chosenWords).size();
			scores.add(new TextFileRatings(rating,chosenDocuments.get(i)));
		}
		Collections.sort(scores);
		return scores;
	}

}
