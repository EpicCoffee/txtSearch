package controller;

import java.util.ArrayList;
import model.Session;
import model.TextFile;
import model.TextFileRatings;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DocumentHandlerTest
{

	@Test
	void alphabeticSort()
	{
		String[] unsorted = {"cat","monkey","elephant","antelope"};
		String[] sorted = {"antelope","cat","elephant","monkey"};
		assertArrayEquals(sorted, DocumentHandler.alphabeticSort(unsorted),"Array is not alphabetic");
	}

	@Test
	void searchTextFileContentWithWords()
	{
		TextFile myTextFile = new TextFile("Name", "hej hopp katt mus Janne malle Kalle Ulle paka makka katt");
		assertEquals(4, DocumentHandler.searchTextFileContentWithWords(myTextFile, "hej", "katt", "Janne").size(), "I do not find the words.");
	}

	@Test
    void createSortedTextFileRatingsArrayList()
	{
		ArrayList<TextFile> chosenDocuments = new ArrayList<>();
		chosenDocuments.add(new TextFile("text1", "Janne är en hund och hundar gillar ketchup"));
		chosenDocuments.add(new TextFile("text2", "Anna äter falukorv det är väldigt gott. Anna har två katter som får smaka."));
		chosenDocuments.add(new TextFile("text3", "Janne gillar katter och han själv är en mus, katter, mus, katter"));
		chosenDocuments.add(new TextFile("text4", "Rebecca har en hund som är vän med en mus som är vän med en mus"));

		ArrayList<TextFileRatings> correctRatings = new ArrayList<>();
		correctRatings.add(new TextFileRatings(5, chosenDocuments.get(2)));
		correctRatings.add(new TextFileRatings(2, chosenDocuments.get(3)));
		correctRatings.add(new TextFileRatings(1, chosenDocuments.get(1)));
		correctRatings.add(new TextFileRatings(0, chosenDocuments.get(0)));

		ArrayList<TextFileRatings> testRating = DocumentHandler.createSortedTextFileRatingsArrayList(chosenDocuments, "katter", "mus");

		for (int i = 0; i < testRating.size(); i++)
		{
			assertEquals(correctRatings.get(i).getTextFile(), testRating.get(i).getTextFile(), "One of the documents at rating is not the same");
			assertEquals(correctRatings.get(i).getRating(), testRating.get(i).getRating(), "One of the rating is the documents are not the same");
		}
    }

	//Test by having 4 .txt files at C:\txtSearch
	@Test
	void loadAllMyTextFiles()
	{
		DocumentHandler.loadAllMyTextFiles();
		assertEquals(4, Session.getSession().getLoadedTextFiles().size(), "You do not have 4 txt files in folder C:\\txtSearch");
	}

	// Make sure Marcus historia2 do not exist when starting test.
	@Test
	void editOrCreateTextFile()
	{
		// first creates the file.
		assertTrue(DocumentHandler.editOrCreateTextFile("Marcus historia2", "123Banana o puff"));
		// Edit the created file
		assertTrue(DocumentHandler.editOrCreateTextFile("Marcus historia2", "Marcus the best"));
	}

	@Test
	void getContentInTextFileAt()
	{
		DocumentHandler.editOrCreateTextFile("Marcus historia2", "Marcus the best");
		System.out.println(DocumentHandler.getContentInTextFileAt(Constants.folderPath + "Marcus historia2.txt"));
		assertEquals("Marcus the best", DocumentHandler.getContentInTextFileAt(Constants.folderPath + "Marcus historia2.txt").trim(), "The content at Marcus histora2 is wrong");
	}

	@Test
	void getWordsFromString()
	{
		assertArrayEquals(new String[] {"hej", "jag", "e", "en", "katt"}, DocumentHandler.getWordsFromString("hej, jag e en , katt"), "Did not get matching words from string");
	}

}