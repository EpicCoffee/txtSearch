package controller;

import model.TextFile;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DocumentHandlerTest
{
	@Test
	void searchStrings()
	{
		TextFile myTextFile = new TextFile("Name", new String[]{"hej", "hopp", "katt", "mus", "Janne", "malle", "Kalle", "Ulle", "paka", "makka", "katt"});
		assertEquals(4, DocumentHandler.searchStrings(myTextFile, "hej", "katt", "Janne").size(), "I do not find the two words.");
	}
}