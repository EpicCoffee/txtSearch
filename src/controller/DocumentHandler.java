package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;

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
	public static void CreateTextDocument() throws IOException {

		File file = new File("C:/txtSearch/engwords.txt");
		File file1 = new File("C:/txtSearch/numbers.txt");
		File file2 = new File("C:/txtSearch/plaintext.txt");

		if (file.createNewFile()) {
			System.out.println(" file created");
			if (file1.createNewFile()){
				System.out.println("file created");
				if (file2.createNewFile()){
					System.out.println("file created");
				}
			}

		}else{
			System.out.println("File already exists");
		}



		}

	public static String getContent(String filePath) {
		String content = "";
		try
		{
			content = new String(Files.readAllBytes(Paths.get(filePath)));

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		String[] wordsAndNumbers = content.split(",");


		System.out.println(wordsAndNumbers[0]);


		return content;
	}
}
