package controller;

import java.util.ArrayList;
import model.Session;
import model.TextFile;
import model.TextFileRatings;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DocumentHandlerTest
{
	/*
		Checking if alphabeticSort works by having one unsorted array and one sorted.
	 */
	@Test
	void alphabeticSort()
	{
		String[] unsorted = {"cat","monkey","elephant","antelope"};
		String[] sorted = {"antelope","cat","elephant","monkey"};
		assertArrayEquals(sorted, DocumentHandler.alphabeticSort(unsorted),"Array is not alphabetic");
	}

	/*
		Checking if searchTextFileContentWithWords works by creating a text file and expecting it to find words 4 times in it.
	 */
	@Test
	void searchTextFileContentWithWords()
	{
		TextFile myTextFile = new TextFile("Name", "hej hopp katt mus Janne malle Kalle Janne Ulle paka makka katt");

		assertEquals(4, DocumentHandler.searchTextFileContentWithWords(myTextFile, "hej", "katt", "paka").size(), "I do not find the words.");
	}

	/*
		Checking if createSortedTextFileRatingsArrayList works by creating an ArrayList with TextFiles and one with the expected TextFileRatings in order.
		I use the choosenDocuments in the createSortedTextFileRatingsArrayList function and expect it to return a sorted TextFileRatings in same way as the expectedTextFileRatings are.
 		*/
	@Test
    void createSortedTextFileRatingsArrayList()
	{
		ArrayList<TextFile> chosenDocuments = new ArrayList<>();
		chosenDocuments.add(new TextFile("text1", "Janne är en hund och hundar gillar ketchup"));
		chosenDocuments.add(new TextFile("text2", "Anna äter falukorv det är väldigt gott. Anna har två katter som får smaka."));
		chosenDocuments.add(new TextFile("text3", "Janne gillar katter och han själv är en mus, katter, mus, katter"));
		chosenDocuments.add(new TextFile("text4", "Rebecca har en hund som är vän med en mus som är vän med en mus"));

		ArrayList<TextFileRatings> expectedTextFileRatings = new ArrayList<>();
		expectedTextFileRatings.add(new TextFileRatings(5, chosenDocuments.get(2)));
		expectedTextFileRatings.add(new TextFileRatings(2, chosenDocuments.get(3)));
		expectedTextFileRatings.add(new TextFileRatings(1, chosenDocuments.get(1)));
		expectedTextFileRatings.add(new TextFileRatings(0, chosenDocuments.get(0)));

		ArrayList<TextFileRatings> testRating = DocumentHandler.createSortedTextFileRatingsArrayList(chosenDocuments, "katter", "mus");

		for (int i = 0; i < testRating.size(); i++)
		{
			assertEquals(expectedTextFileRatings.get(i).getTextFile(), testRating.get(i).getTextFile(), "One of the documents at rating is not the same");
			assertEquals(expectedTextFileRatings.get(i).getRating(), testRating.get(i).getRating(), "One of the rating is the documents are not the same");
		}
    }

	/*
		Test by having 4 .txt files at C:\txtSearch
		Checking if loadAllMyTextFiles() loads all 4 to loadedTextFiles at our Session.
	 */
	@Test
	void loadAllMyTextFiles()
	{
		DocumentHandler.loadAllMyTextFiles();
		assertEquals(4, Session.getSession().getLoadedTextFiles().size(), "You do not have 4 txt files in folder C:\\txtSearch");
	}

	/*
		Make sure Marcus historia2 do not exist when starting test.
		It will first try to create the file Marcus historia2 and return result.
		It will then edit the same file and return the result of the edit.
	 */
	@Test
	void editOrCreateTextFile()
	{
		assertTrue(DocumentHandler.editOrCreateTextFile("Marcus historia2", "123Banana o puff"));

		assertTrue(DocumentHandler.editOrCreateTextFile("Marcus historia2", "Marcus the best"));
	}

	/*
		Edit or creates the file Marcus hisoria2 to test at this file.
		Checking if the content of the file is same as what getContentInTextFileAt returns. Trims the result to get rid of extra spaces at end.
 	*/
	@Test
	void getContentInTextFileAt()
	{
		DocumentHandler.editOrCreateTextFile("Marcus historia2", "Marcus the best");
		assertEquals("Marcus the best", DocumentHandler.getContentInTextFileAt(Constants.folderPath + "Marcus historia2.txt").trim(), "The content at Marcus histora2 is wrong");
	}

	/*
	Checking if it gives back you only words in a String[] from a sentence in a String.
 */
	@Test
	void getWordsFromString()
	{
		assertArrayEquals(new String[] {"hej", "jag", "e", "en", "katt"}, DocumentHandler.getWordsFromString("hej, jag e en , katt"), "Did not get matching words from string");
	}
}