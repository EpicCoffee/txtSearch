package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Session;
import model.TextFile;

import java.util.ArrayList;
import java.util.Arrays;
import model.TextFileRatings;

public class FileSearchController
{
	@FXML
	public TextArea textArea;
	public AnchorPane allLoadedFilesPane;
	public TextField searchInput, titleLabel;
	public AnchorPane searchResultPane, lobbyPane, editPane;

	private VBox loadedFilesVBox;
	private VBox loadedRankVBox;


	/** Save or creates the text file needed with the name from the titleLabel text and the content from the textArea text.
	 *	Updates loaded files.
	 *  Going back to lobby.
	 *
	 * @param actionEvent info about the event triggered.
	 */
	@FXML
	public void saveFile(ActionEvent actionEvent)
	{
		if (DocumentHandler.editOrCreateTextFile(titleLabel.getText(), textArea.getText()))
		{
			Session.getSession().resetChosenDocuments();
			DocumentHandler.loadAllMyTextFiles();
			initializeAllLoadedFilesPane();
			goToLobby(null);
		}
	}

	/** Alphabetical order the words in the textArea.
	 *
	 * @param actionEvent info about the event triggered.
	 */
	public void onClickSortByAlphabet(ActionEvent actionEvent)
	{
		String[] sortedWords = DocumentHandler.alphabeticSort(DocumentHandler.getWordsFromString(textArea.getText().toLowerCase()));

		String fullSentence = Arrays.toString(sortedWords);
		textArea.setText(fullSentence.substring(1, fullSentence.length()-1).replace(",",""));
	}

	/** Updating loadedTankVBox with the search results.
	 *
	 * @param actionEvent info about the event triggered.
	 */
	@FXML
	public void onClickSearchNow(ActionEvent actionEvent)
	{
		searchResultPane.getChildren().remove(loadedRankVBox);
		ArrayList<TextFileRatings> sortedTextfiles = new ArrayList<>(DocumentHandler.createSortedTextFileRatingsArrayList(Session.getSession().getChosenDocuments(),DocumentHandler.getWordsFromString(searchInput.getText().toLowerCase())));
		loadedRankVBox = new VBox();
		for(TextFileRatings st:sortedTextfiles)
		{
			Label pointLabel = new Label(st.getRating() + " " + st.getTextFile().getFileName());
			pointLabel.setOnMouseClicked(event ->
			{
				titleLabel.setText(st.getTextFile().getFileName()); // cat, bird, kattunge, Millad
				textArea.setText(st.getTextFile().getContentWords());
				goToNewDocument(null);
			});
			loadedRankVBox.getChildren().add(pointLabel);
		}
		searchResultPane.getChildren().add(loadedRankVBox);
	}

	/** Showing editPane.
	 *
	 * @param actionEvent info about the event triggered.
	 */
	@FXML
	public void goToNewDocument(ActionEvent actionEvent)
	{
		editPane.setVisible(true);
		lobbyPane.setVisible(false);
		if (actionEvent != null)
		{
			titleLabel.setText("");
			textArea.setText("");
		}
	}

	/** Showing lobby.
	 *
	 * @param actionEvent info about the event triggered.
	 */
	@FXML
	public void goToLobby(ActionEvent actionEvent)
	{
		editPane.setVisible(false);
		lobbyPane.setVisible(true);
	}

	/**
	 * Called on JavaFX start.
	 */
	public void initialize()
	{
		DocumentHandler.loadAllMyTextFiles();
		initializeAllLoadedFilesPane();
	}

	/**
	 * Updating loadedFilesPane to match LoadedTextFiles in Session.
	 */
	private void initializeAllLoadedFilesPane()
	{
		allLoadedFilesPane.getChildren().remove(loadedFilesVBox);
		loadedFilesVBox = new VBox();
		for (TextFile textfile : Session.getSession().getLoadedTextFiles())
		{
			Label titleLabelInBox = new Label(textfile.getFileName());

			titleLabelInBox.setOnMouseClicked(event -> {

				if (event.getClickCount() == 2)
				{
					titleLabel.setText(textfile.getFileName());
					textArea.setText(textfile.getContentWords());
					goToNewDocument(null);
				}

				if (Session.getSession().getChosenDocuments().contains(textfile))
				{
					titleLabelInBox.setTextFill(Color.web("#000000"));
					Session.getSession().removeAtChosenDocuments(textfile);
				}
				else
				{
					titleLabelInBox.setTextFill(Color.web("#12b500"));
					Session.getSession().addToChosenDocuments(textfile);
				}
			});
			loadedFilesVBox.getChildren().add(titleLabelInBox);
		}
		allLoadedFilesPane.getChildren().add(loadedFilesVBox);
	}

}
