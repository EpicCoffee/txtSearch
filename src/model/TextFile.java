package model;

/*
  This is a POJO holding the info we need in the text files.
 */
public class TextFile
{
	private String fileName;
	private String contentWords;

	public TextFile(String fileName, String contentWords)
	{
		this.fileName = fileName;
		this.contentWords = contentWords;
	}

	/*
	 Only getters and setters below.
	 */

	public String getFileName()
	{
		return fileName;
	}

	public String getContentWords()
	{
		return contentWords;
	}

}
