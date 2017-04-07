package sample;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

/**
 * Created by tsotne on 4/7/17.
 */
public class MediaBar extends HBox {
    //time
    Slider t=new Slider();
    //volume
    Slider v=new Slider();

    Button playB=new Button("||");
    //volume label
    Label vol=new Label("Volume: ");
    MediaPlayer pl;

    public MediaBar(MediaPlayer pl){
        this.pl=pl;

        setAlignment(Pos.CENTER);
        setPadding(new Insets(5,10,5,10));

        v.setPrefWidth(70);
        v.setMinWidth(30);
        v.setValue(100);

        HBox.setHgrow(t, Priority.ALWAYS);
        playB.setPrefWidth(30);

        getChildren().add(playB);
        getChildren().add(t);
        getChildren().add(vol);
        getChildren().add(v);

        playB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Status status=pl.getStatus();

                if(status==Status.PLAYING){
                    if(pl.getCurrentTime().greaterThanOrEqualTo(pl.getTotalDuration())){
                        pl.seek(pl.getStartTime());
                        pl.play();
                    }
                    else {
                        pl.pause();
                        playB.setText(">");
                    }
                }

                if(status==Status.PAUSED||status==Status.HALTED||status==Status.STOPPED){
                    pl.play();
                    playB.setText("||");
                }
            }
        });

        pl.currentTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                updateValues();
            }
        });
        
        t.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(t.isPressed()){
                    pl.seek(pl.getMedia().getDuration().multiply(t.getValue()/100));
                }
            }
        });

        v.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(v.isPressed()){
                    pl.setVolume(v.getValue()/100);
                }
            }
        });
    }

    protected void updateValues(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                t.setValue(pl.getCurrentTime().toMillis()/pl.getTotalDuration().toMillis()*100);
            }
        });
    }
}
