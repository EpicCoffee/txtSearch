package model;

/*
  This is a comparable POJO to connect and order ratings with textFile.
 */
public class TextFileRatings implements Comparable<TextFileRatings>
{
    private int rating;
    private TextFile textFile;

    public TextFileRatings(int score, TextFile textFile)
	{
        this.rating = score;
        this.textFile = textFile;
    }

    // Using compareTo from implement Comparable, sorting larges rating first.
    @Override
    public int compareTo(TextFileRatings o)
	{
        return Integer.compare(o.rating, rating);
    }

    /*
	 Only getters and setters below.
	 */

	public int getRating()
	{
		return rating;
	}

	public TextFile getTextFile()
	{
		return textFile;
	}
}
