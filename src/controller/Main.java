package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	/**
	 * Starts the JavaFX view.
	 *
	 * @param primaryStage The main stage you want to start at.
	 */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/FileSearchView.fxml"));
        primaryStage.setTitle("Txt Search");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }

	/**
	 * Starts the program.
	 *
	 * @param args the arguments you want to use at startup.
	 */
    public static void main(String[] args) {
		// This command launch javaFX View.
		launch(args);
    }
}
