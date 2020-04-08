package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Session;
import model.TextFile;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/FileSearchView.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
		System.out.println("Hej 2");

		DocumentHandler.loadAllFiles();
		/*TextFile textFile1 = new TextFile("Katten", new String[]{"hej", "hopp", "hej"});
		TextFile textFile2 = new TextFile("musen", new String[]{"hej", "hopp", "katt"});
		TextFile textFile3 = new TextFile("busen", new String[]{"hopp"});
		Session.getSession().addToLoadedTextFiles(textFile2);
		Session.getSession().addToLoadedTextFiles(textFile1);
		Session.getSession().addToLoadedTextFiles(textFile3);
*/

		System.out.println("Hello");
       DocumentHandler DH = new DocumentHandler();
       //DH.getContent("C:/txtSearch/makeitWork.txt");
       // DH.getContent("C:/txtSearch/text.txt");
       //DH.CreateFile("Katt", "Hej katt man katt");


		// Use this command below to launch javaFX View.
		launch(args);
    }
}
