package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/FileSearchView.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
		System.out.println("Hello");
       DocumentHandler DH = new DocumentHandler();
       //DH.getContent("C:/txtSearch/makeitWork.txt");
       // DH.getContent("C:/txtSearch/text.txt");
       DH.CreateFile("Katt", "Hej katt man katt");





		// Use this command below to launch javaFX View.
		//launch(args);
    }
}
