package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Session;
import model.TextFile;

import java.util.ArrayList;
import java.util.Arrays;

public class FileSearchController
{
	@FXML
	public TextArea textArea;
	public AnchorPane allLoadedFilesPane;
	public TextField searchInput, titleLabel;
	public AnchorPane searchResultPane, lobbyPane, editPane;

	private VBox loadedFilesVBox;
	private VBox loadedRankVBox;

	@FXML
	public void saveFile(ActionEvent actionEvent)
	{
		if (DocumentHandler.saveFile(titleLabel.getText(), textArea.getText()))
		{
			DocumentHandler.loadAllFiles();
			initializeAllLoadedFilesPane();
			goToLobby(null);
		}
		else
		{
			// file did not save
		}
	}

	@FXML
	public void goToLobby(ActionEvent actionEvent)
	{
		editPane.setVisible(false);
		lobbyPane.setVisible(true);
	}

	public void onClickSortByAlphabet(ActionEvent actionEvent)
	{
		String[] sortedWords = DocumentHandler.alphabeticSort(DocumentHandler.getWordsFromString(textArea.getText()));

		String fullSentence = Arrays.toString(sortedWords);
		textArea.setText(fullSentence.substring(1, fullSentence.length()-1).replace(",",""));
	}

	@FXML
	public void onClickSearchNow(ActionEvent actionEvent)
	{
		searchResultPane.getChildren().remove(loadedRankVBox);
		ArrayList<TextFileRatings> sortedTextfiles = new ArrayList<>(DocumentHandler.setRankingOnTextFile(Session.getSession().getChoosenDocuments(),searchInput.getText()));
		loadedRankVBox = new VBox();
		for(TextFileRatings st:sortedTextfiles){
			Label pointLabel = new Label(st.rating + " " + st.textFile.getFileName());
			pointLabel.setOnMouseClicked(event ->
			{
				titleLabel.setText(st.textFile.getFileName());
				textArea.setText(st.textFile.getContentWords());
				goToNewDocument(null);
			});
			loadedRankVBox.getChildren().add(pointLabel);
		}
		searchResultPane.getChildren().add(loadedRankVBox);
	}

	@FXML
	public void goToNewDocument(ActionEvent actionEvent)
	{
		editPane.setVisible(true);
		lobbyPane.setVisible(false);
	}

	public void initialize()
	{
		initializeAllLoadedFilesPane();
	}

	private void initializeAllLoadedFilesPane()
	{
		allLoadedFilesPane.getChildren().remove(loadedFilesVBox);
		loadedFilesVBox = new VBox();
		for (TextFile textfile : Session.getSession().getLoadedTextFiles())
		{
			Label titleLabel = new Label(textfile.getFileName());

			titleLabel.setOnMouseClicked(event -> {
				if (Session.getSession().getChoosenDocuments().contains(textfile))
				{
					titleLabel.setTextFill(Color.web("#000000"));
					Session.getSession().removeAtChoosenDocuments(textfile);
				}
				else
				{
					titleLabel.setTextFill(Color.web("#12b500"));
					Session.getSession().addToChoosenDocuments(textfile);
				}
			});
			loadedFilesVBox.getChildren().add(titleLabel);
		}
		allLoadedFilesPane.getChildren().add(loadedFilesVBox);
	}

}
