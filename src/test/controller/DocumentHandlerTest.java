package controller;

import model.TextFile;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DocumentHandlerTest
{

	private String[] unsorted = {"cat","monkey","elephant","antelope"};
	private String[] sorted = {"antelope","cat","elephant","monkey"};

	@Test
	void alphabeticSort() {
		DocumentHandler documentHandler = new DocumentHandler();
		assertEquals(sorted, DocumentHandler.alphabeticSort(unsorted),"Array is not alphabetic");
	}

	@Test
	void searchStrings()
	{
		TextFile myTextFile = new TextFile("Name", "hej hopp katt mus Janne malle Kalle Ulle paka makka katt");
		assertEquals(4, DocumentHandler.searchStrings(myTextFile, "hej", "katt", "Janne").size(), "I do not find the words.");
	}

	@Test
    void sortRanking(){

    }
    @Test
	void getContent(String filePath) {
        String testContent ="";

	}
	void CreateFile(){
		
	}

}