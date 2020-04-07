package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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

	public void initializeAll()
	{

	}

	private void initializeAllLoadedFilesPane()
	{

	}
}