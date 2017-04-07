package sample;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.Media;

/**
 * Created by tsotne on 4/7/17.
 */
public class Player extends BorderPane{
    Media media;
    MediaPlayer pl;
    MediaView view;
    MediaBar bar;
    Pane mpane;
    public Player(String s) {

        media=new Media(s);
        pl=new MediaPlayer(media);
        view=new MediaView(pl);
        mpane=new Pane();

        mpane.getChildren().add(view);
        setCenter(mpane);

        bar=new MediaBar(pl);
        setBottom(bar);
        setStyle("-fx-background-color: #bfc2c7");
        pl.play();
    }
}
