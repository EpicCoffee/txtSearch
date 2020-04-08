package model;

public class TextFile
{
	private String fileName;
	private String contentWords;

	public TextFile(String fileName, String contentWords)
	{
		this.fileName = fileName;
		this.contentWords = contentWords;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getContentWords()
	{
		return contentWords;
	}

	public void setContentWords(String contentWords)
	{
		this.contentWords = contentWords;
	}
}
