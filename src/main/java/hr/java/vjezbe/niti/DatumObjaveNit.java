package hr.java.vjezbe.niti;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class DatumObjaveNit {

    Timeline objava = new Timeline(new KeyFrame(Duration.seconds(10), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Platform.runLater((Runnable) new DatumObjaveNit());
            objava.setCycleCount(Timeline.INDEFINITE);
            objava.play();
        }
    }));
}
