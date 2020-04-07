package controller;

import model.TextFile;

public class TextFileRatings implements Comparable<TextFileRatings> {
    int rating;
    TextFile textFile;

    public TextFileRatings(int score, TextFile textFile) {
        this.rating = score;
        this.textFile = textFile;
    }

    @Override
    public int compareTo(TextFileRatings o) {
        return Integer.compare(rating, o.rating);
    }
}
