import controller.DocumentHandler;
import model.Session;
import model.TextFile;
import model.TextFileRatings;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    //This test checks if all of the methods that handles the text files, like creating, editing and loading.
    @Test
    void documentIntegrationTest(){
        DocumentHandler documentHandler = new DocumentHandler();

        //create
        assertTrue(documentHandler.editOrCreateTextFile("IntegrationTest", "hejhopp"));

        //edit
        assertTrue(documentHandler.editOrCreateTextFile("IntegrationTest", "hejhopp hejhopp"));

        //Load all documents, requires 4 documents in map
        documentHandler.loadAllMyTextFiles();
        assertEquals(4, Session.getSession().getLoadedTextFiles().size(), "You do not have 4 txt files in folder C:\\txtSearch");
    }

    //This test checks if the methods that handle sorting works together.
    @Test
    void sortIntegrationTest(){
        DocumentHandler documentHandler = new DocumentHandler();
        //sort alphabetically
        String[] unsorted = {"cat","monkey","elephant","antelope"};
        String[] sorted = {"antelope","cat","elephant","monkey"};
        assertArrayEquals(sorted, documentHandler.alphabeticSort(unsorted),"Array is not alphabetic");

        //Rating sort
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

        ArrayList<TextFileRatings> testRating = documentHandler.createSortedTextFileRatingsArrayList(chosenDocuments, "katter", "mus");

        for (int i = 0; i < testRating.size(); i++)
        {
            assertEquals(expectedTextFileRatings.get(i).getTextFile(), testRating.get(i).getTextFile(), "One of the documents at rating is not the same");
            assertEquals(expectedTextFileRatings.get(i).getRating(), testRating.get(i).getRating(), "One of the rating is the documents are not the same");
        }
    }

    //This test checks if the methods that handle text manipulation works together.
    @Test
    void textIntegrationTest(){
        DocumentHandler documentHandler = new DocumentHandler();

        //Get words out of string
        assertArrayEquals(new String[] {"hej", "jag", "e", "en", "katt"}, documentHandler.getWordsFromString("hej, jag e en , katt"), "Did not get matching words from string");

        //Search textfile
        TextFile myTextFile = new TextFile("Name", "hej hopp katt mus Janne malle Kalle Janne Ulle paka makka katt");
        assertEquals(4, DocumentHandler.searchTextFileContentWithWords(myTextFile, "hej", "katt", "paka").size(), "I do not find the words.");
    }
}
