package model;

import java.util.ArrayList;

public class Session
{
	private static Session session;
	private ArrayList<TextFile> loadedTextFiles;
	private TextFile currentDocument;
	private ArrayList<TextFile> choosenDocuments;

	private Session() {
		loadedTextFiles = new ArrayList<>();
		currentDocument = null;
		choosenDocuments = new ArrayList<>();
	}

	public static Session getSession()
	{
		if (session == null)
		{
			return new Session();
		}
		return session;
	}

	public ArrayList<TextFile> getLoadedTextFiles()
	{
		return loadedTextFiles;
	}

	public void setLoadedTextFiles(ArrayList<TextFile> loadedTextFiles)
	{
		this.loadedTextFiles = loadedTextFiles;
	}

	public void addToLoadedTextFiles(TextFile loadedTextFile)
	{
		this.loadedTextFiles.add(loadedTextFile);
	}

	public void removeAtLoadedTextFiles(TextFile loadedTextFile)
	{
		this.loadedTextFiles.remove(loadedTextFile);
	}

	public TextFile getCurrentDocument()
	{
		return currentDocument;
	}

	public void setCurrentDocument(TextFile currentDocument)
	{
		this.currentDocument = currentDocument;
	}

	public ArrayList<TextFile> getChoosenDocuments()
	{
		return choosenDocuments;
	}

	public void setChoosenDocuments(ArrayList<TextFile> choosenDocuments)
	{
		this.choosenDocuments = choosenDocuments;
	}

	public void addToChoosenDocuments(TextFile document)
	{
		this.choosenDocuments.add(document);
	}

	public void removeAtChoosenDocuments(TextFile document)
	{
		this.choosenDocuments.remove(document);
	}
}
