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

public class FileSearchController
{
	@FXML
	public TextArea textArea;
	public AnchorPane allLoadedFilesPane;
	public TextField searchInput, titleLabel;
	public AnchorPane searchResultPane, lobbyPane, editPane;

	@FXML
	public void saveFile(ActionEvent actionEvent)
	{
	}

	@FXML
	public void goToLobby(ActionEvent actionEvent)
	{
		searchResultPane.setVisible(false);
		lobbyPane.setVisible(true);
	}

	@FXML
	public void onClickSearchNow(ActionEvent actionEvent)
	{
	}

	@FXML
	public void goToNewDocument(ActionEvent actionEvent)
	{
		searchResultPane.setVisible(true);
		lobbyPane.setVisible(false);
	}

	public void initialize()
	{
		initializeAllLoadedFilesPane();
	}

	private void initializeAllLoadedFilesPane()
	{
		VBox vBox = new VBox();
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
			vBox.getChildren().add(titleLabel);
		}
		allLoadedFilesPane.getChildren().add(vBox);
	}
}
