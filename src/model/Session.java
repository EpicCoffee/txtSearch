package model;

import java.util.ArrayList;

/*
  This is a POJO holding all info needed at the program.
 */
public class Session
{
	private static Session session;
	private ArrayList<TextFile> loadedTextFiles;
	private ArrayList<TextFile> chosenDocuments;

	private Session()
	{
		loadedTextFiles = new ArrayList<>();
		chosenDocuments = new ArrayList<>();
	}
	/**
	 * Singleton of Session.
	 */
	public static Session getSession()
	{
		if (session == null)
		{
			session = new Session();
		}
		return session;
	}

	/*
	 Only getters and setters below.
	 */

	public ArrayList<TextFile> getLoadedTextFiles()
	{
		return loadedTextFiles;
	}

	public void addToLoadedTextFiles(TextFile loadedTextFile)
	{
		loadedTextFiles.add(loadedTextFile);
	}

	public void resetLoadedTextFiles()
	{
		loadedTextFiles = new ArrayList<>();
	}

	public ArrayList<TextFile> getChosenDocuments()
	{
		return chosenDocuments;
	}

	public void addToChosenDocuments(TextFile document)
	{
		this.chosenDocuments.add(document);
	}

	public void resetChosenDocuments()
	{
		chosenDocuments = new ArrayList<>();
	}

	public void removeAtChosenDocuments(TextFile document)
	{
		this.chosenDocuments.remove(document);
	}
}
