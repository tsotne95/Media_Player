package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.io.File;
import java.net.MalformedURLException;

public class Main extends Application {
    Player pl;
    FileChooser fileChooser;

    @Override
    public void start(final Stage primaryStage) {
        MenuItem mi=new MenuItem("Open");
        Menu file=new Menu("File");
        MenuBar menu=new MenuBar();

        file.getItems().add(mi);
        menu.getMenus().add(file);

        fileChooser=new FileChooser();

        mi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pl.pl.pause();
                File file=fileChooser.showOpenDialog(primaryStage);
                if(file!=null)
                {
                    try {
                        pl =new Player(file.toURI().toURL().toExternalForm());

                        pl.setTop(menu);
                        Scene scene=new Scene(pl,720,540, Color.BLACK);

                        primaryStage.setScene(scene);
                        primaryStage.show();
                    }
                    catch (MalformedURLException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
        //default media path
        pl=new Player("file:///home/tsotne/vid.mp4");
        pl.setTop(menu);
        //with,height,color
        Scene scene=new Scene(pl,720,540, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
